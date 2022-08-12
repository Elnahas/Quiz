package eduq.com.quiz.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.data.repository.UserRepository
import eduq.com.quiz.other.Constants.KEY_NOT_EXISTS
import eduq.com.quiz.utilits.Resource
import eduq.com.quiz.utilits.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(var repository: UserRepository): ViewModel() {

    val userMutableLiveData: SingleLiveEvent<Resource<UserModel>> = SingleLiveEvent()
    val isUserExistsMutableLiveData: SingleLiveEvent<Resource<UserModel>> = SingleLiveEvent()


    fun registerNewUser(userModel: UserModel) =

           viewModelScope.launch {

           userMutableLiveData.postValue(Resource.Loading())
           val userModel = repository.registerUser(userModel)
           userMutableLiveData.postValue(Resource.Success(userModel))

       }

    fun isUserExists(userName: String) =
        viewModelScope.launch {

            val result = repository.isUserExists(userName)

            if (result != null && result.exists()) {

                var user : UserModel?=null

                result.children.forEach{

                    user = it.getValue(UserModel::class.java)!!

                }

                isUserExistsMutableLiveData.postValue(Resource.Success(user))




            } else {

                isUserExistsMutableLiveData.postValue(Resource.Error(KEY_NOT_EXISTS))

            }
        }


    fun login(userName: String) = viewModelScope.launch {

    }

}