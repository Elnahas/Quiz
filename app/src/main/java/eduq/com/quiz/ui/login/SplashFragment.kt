package eduq.com.quiz.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.data.model.Role
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.other.Constants
import eduq.com.quiz.other.Constants.FIRST_TIME_TOGGLE
import eduq.com.quiz.ui.viewmodel.UserViewModel
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {


    val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        Handler().postDelayed({

            val FIRST_TIME_TOGGLE = sharedPref.getBoolean(FIRST_TIME_TOGGLE, false)

            if (!FIRST_TIME_TOGGLE) {

                findNavController().navigate(R.id.action_splashFragment_to_signinFragment)

            } else {

                //Data User
                val data = sharedPref.getString(Constants.KEY_USER_MODEL_JSON, "")
                val userModel = Gson()
                    .fromJson<UserModel>(
                        data,
                        object : TypeToken<UserModel>() {}.type
                    )

                goToHomeActivity(userModel)
            }

        }, 1000)


    }

    private fun goToHomeActivity(userModel: UserModel?) {

        if (userModel!!.role!! == Role.STUDENT)
            navigateFirstTabWithClearStack()
        else
            navigateToStudents()

    }

    fun navigateFirstTabWithClearStack() {

        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val navHostFragment: NavHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.homeFragment

        navController.graph = graph
    }

    fun navigateToStudents() {

        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val navHostFragment: NavHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.adminHomeFragment

        navController.graph = graph
    }
}