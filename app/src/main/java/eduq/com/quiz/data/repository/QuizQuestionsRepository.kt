package eduq.com.quiz.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import eduq.com.quiz.data.model.QuestionModel
import eduq.com.quiz.data.model.ResultModel
import eduq.com.quiz.other.Constants.REF_QUESTIONS
import eduq.com.quiz.other.Constants.REF_RESULT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuizQuestionsRepository @Inject constructor() {

    val refQuestions = FirebaseDatabase.getInstance().getReference(REF_QUESTIONS)
    val refResult = FirebaseDatabase.getInstance().getReference(REF_RESULT)

    @ExperimentalCoroutinesApi
    suspend fun getQuizQuestions() = callbackFlow<List<QuestionModel>> {

        val eventListener = refQuestions.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                this@callbackFlow.close()
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                snapshot?.let {
                    val tempList = ArrayList<QuestionModel>()

                    snapshot.children.forEach { snapshot ->
                        val data = snapshot.getValue(QuestionModel::class.java)
                        tempList.add(data!!)
                    }
                    this@callbackFlow.trySendBlocking(tempList)
                }
            }
        })
        awaitClose {
            refQuestions.removeEventListener(eventListener)
        }
    }


//    suspend fun sendResult(result: ResultModel?, materialId: String, lessonId: String, lessonModel: LessonModel) =
//
//        run {
//
//            val updateData : HashMap<String , Any> = HashMap()
//
//
//
//            val refUseLesson =
//                refMaterialStudy.child(materialId).child(REF_LESSONS).child(lessonId).child("usersUsage").child(uid)
//
//
//            val update: MutableMap<String, Any> = HashMap()
//
//            val usersUsage = lessonModel.usersUsage[uid]
//
//            if (usersUsage != null) {
//                update["usageCount"] = usersUsage.usageCount!! + 1
//            }
//            else{
//                update["usageCount"] = 1
//            }
//
//
//            refUseLesson.updateChildren(update).await()
//
//            refResult.child(result!!.createAt!!).setValue(result)
//
//        }
}