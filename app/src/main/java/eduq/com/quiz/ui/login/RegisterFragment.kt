package eduq.com.quiz.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.data.model.Role
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.other.Constants
import eduq.com.quiz.other.Constants.REF_USERS
import eduq.com.quiz.ui.viewmodel.UserViewModel
import eduq.com.quiz.utilits.CustomLoading.Companion.hideProgressBar
import eduq.com.quiz.utilits.CustomLoading.Companion.showProgressBar
import eduq.com.quiz.utilits.Resource
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {



    var refUser = FirebaseDatabase.getInstance().getReference(REF_USERS)

    val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        viewModel.userMutableLiveData.observe(viewLifecycleOwner, Observer {

            when (it) {

                is Resource.Success -> {
                    hideProgressBar()

                    input_userName.setText("")
                    input_full_name.setText("")
                    input_password.setText("")

                    Toast.makeText(requireContext() , "User is add successful!" , Toast.LENGTH_LONG ).show()
                }


            }

        })

        viewModel.isUserExistsMutableLiveData.observe(viewLifecycleOwner,
            {

                when (it) {

                    is Resource.Success -> {
                        hideProgressBar()
                        Toast.makeText(requireContext() , "User is Exists!", Toast.LENGTH_LONG).show()
                    }

                    is Resource.Error -> {

                        when (it.message) {

                            Constants.KEY_NOT_EXISTS -> {

                                registerUser()
                            }
                            else -> {

                            }
                        }

                    }
                    is Resource.Loading -> {
                        showProgressBar(requireContext(), false)
                    }
                }
            })


        btn_register.setOnClickListener {

            viewModel.isUserExists(input_userName.text.toString().trim())

        }
    }

    private fun registerUser() {

        val full_name = input_full_name.text.toString().trim()
        val user_Name = input_userName.text.toString().trim()
        val _password = input_password.text.toString().trim()

        val userModel = UserModel()

        //set Data
        userModel.apply {

            fullName = full_name
            userName = user_Name
            password = _password
            createAt = ServerValue.TIMESTAMP
            role = Role.STUDENT

        }


        viewModel.registerNewUser(userModel)


    }

}