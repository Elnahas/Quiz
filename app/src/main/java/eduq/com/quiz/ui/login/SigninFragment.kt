package eduq.com.quiz.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.data.model.Role
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.other.Constants
import eduq.com.quiz.other.Constants.FIRST_TIME_TOGGLE
import eduq.com.quiz.other.Constants.REF_USERS
import eduq.com.quiz.ui.viewmodel.UserViewModel
import eduq.com.quiz.utilits.CustomLoading.Companion.hideProgressBar
import eduq.com.quiz.utilits.CustomLoading.Companion.showProgressBar
import kotlinx.android.synthetic.main.fragment_signin.*
import javax.inject.Inject

@AndroidEntryPoint
class SigninFragment : Fragment(R.layout.fragment_signin) {

    val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        btn_login.setOnClickListener {

            if (input_userName.text.toString() != null || input_password.text.toString() != null) {

                val userName = input_userName.text.toString()
                val password = input_password.text.toString()

                showProgressBar(requireContext(), false)

                FirebaseDatabase.getInstance().getReference(REF_USERS)
                    .orderByChild(Constants.FIELD_USER_NAME).equalTo(userName)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            if (snapshot != null && snapshot.exists()) {

                                var userModel: UserModel? = null

                                snapshot.children.forEach {

                                    userModel = it.getValue(UserModel::class.java)!!

                                }

                                if (password == userModel!!.password) {

                                    hideProgressBar()

                                    sharedPref.edit().putBoolean(FIRST_TIME_TOGGLE, true).putString(
                                        Constants.KEY_USER_MODEL_JSON,
                                        Gson().toJson(userModel)
                                    ).apply()

                                    if(userModel!!.role == Role.STUDENT)
                                    findNavController().navigate(R.id.action_signinFragment_to_homeFragment)
                                    else
                                    findNavController().navigate(R.id.action_signinFragment_to_adminHomeFragment)
                                } else {
                                    hideProgressBar()

                                    Toast.makeText(
                                        requireContext(),
                                        "Password is wrong",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                                //isUserExistsMutableLiveData.postValue(Resource.Success(user))


                            } else {

                                hideProgressBar()

                                Toast.makeText(
                                    requireContext(),
                                    "User is not found",
                                    Toast.LENGTH_LONG
                                ).show()
                                //isUserExistsMutableLiveData.postValue(Resource.Error(Constants.KEY_NOT_EXISTS))

                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            hideProgressBar()
                            Toast.makeText(
                                requireContext(),
                                "error ${error.message}",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    })

            }

        }
    }

}