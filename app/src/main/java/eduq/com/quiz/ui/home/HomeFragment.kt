package eduq.com.quiz.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.other.Constants
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    lateinit var userModel: UserModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Data User
        val data = sharedPref.getString(Constants.KEY_USER_MODEL_JSON, "")
        userModel = Gson()
            .fromJson<UserModel>(
                data,
                object : TypeToken<UserModel>() {}.type
            )

        btn_quiz_before.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isBefore", true)
            findNavController().navigate(R.id.action_homeFragment_to_quizQuestionsFragment, bundle)
        }
        btn_lessons.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_lessonFragment) }

        btn_quiz_after.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isBefore" , false)
            findNavController().navigate(R.id.action_homeFragment_to_quizQuestionsFragment , bundle) }

        img_log_out.setOnClickListener {
            signOut()
        }

        txt_fullName.text = userModel.fullName

        btn_symptoms.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_symptomsFragment)
        }
    }


    private fun signOut() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Log out")
            .setMessage("Do you really want to exit the application?")
            .setPositiveButton("Yes") { dialog, _ ->

                sharedPref.edit().putBoolean(Constants.FIRST_TIME_TOGGLE, false).apply()
                findNavController().navigate(R.id.action_homeFragment_to_signinFragment)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}