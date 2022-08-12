package eduq.com.quiz.data.repository

import android.net.Uri
import com.google.firebase.database.*
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.other.Constants.FIELD_PHONE
import eduq.com.quiz.other.Constants.FIELD_USER_NAME
import eduq.com.quiz.other.Constants.REF_USERS
import eduq.com.quiz.other.await
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor() {

    var refUser = FirebaseDatabase.getInstance().getReference(REF_USERS)

    suspend fun registerUser(userModel: UserModel) =
        run {



            refUser.push()
                .setValue(userModel)
                .addOnSuccessListener { }
                .addOnFailureListener {
                }.await()


            userModel
        }


    suspend fun isUserExists(userName :String): DataSnapshot =run{

        refUser.orderByChild(FIELD_USER_NAME).equalTo(userName).await()

    }

}