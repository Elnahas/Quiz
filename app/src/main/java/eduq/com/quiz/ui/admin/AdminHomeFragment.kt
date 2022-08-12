package eduq.com.quiz.ui.admin

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.adapters.UsersAdapter
import eduq.com.quiz.data.model.Role
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.other.Constants
import elnahas.deliveroo.utilits.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_admin_home.*
import javax.inject.Inject

@AndroidEntryPoint
class AdminHomeFragment : Fragment(R.layout.fragment_admin_home) {

    lateinit var usersAdapter : UsersAdapter


    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()

        FirebaseDatabase.getInstance().getReference(Constants.REF_USERS)
            .orderByChild("role")
            .equalTo(Role.STUDENT.name)
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    val listLesson = ArrayList<UserModel>()
                    snapshot.children.forEach {
                        val user = it.getValue(UserModel::class.java)
                        user!!.id = it.key

                        listLesson.add(user)
                    }

                    usersAdapter.differ.submitList(listLesson)


                }

            })

        usersAdapter.setOnItemClickListener { user, view ->

            val bundle = Bundle()
            bundle.putSerializable("userModel" , user)
            findNavController().navigate(R.id.action_adminHomeFragment_to_resultStudentsFragment  , bundle)
        }


        toolbar.setOnMenuItemClickListener {

            when (it.itemId) {
                R.id.menu_add -> {

                    findNavController().navigate(R.id.action_adminHomeFragment_to_registerFragment)


                }

                R.id.menu_logout -> {


                    signOut()
                }

            }


            true
        }
    }


    private fun setupRecyclerView() {
        usersAdapter = UsersAdapter()
        recycler_view_users.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(activity )
            addItemDecoration(SpacingItemDecoration(requireContext(), R.dimen.app_margin))
        }

    }

    private fun signOut() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Log out")
            .setMessage("Do you really want to exit the application?")
            .setPositiveButton("Yes") { dialog, _ ->

                sharedPref.edit().putBoolean(Constants.FIRST_TIME_TOGGLE, false).apply()
                findNavController().navigate(R.id.action_adminHomeFragment_to_signinFragment)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun navigateFirstTabWithClearStack() {
        val navController = findNavController()
        val navHostFragment: NavHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.signinFragment

        navController.graph = graph
    }

}