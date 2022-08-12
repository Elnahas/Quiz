package eduq.com.quiz.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eduq.com.quiz.data.model.QuestionModel
import eduq.com.quiz.data.model.ResultModel
import eduq.com.quiz.data.repository.QuizQuestionsRepository
import eduq.com.quiz.utilits.Resource
import eduq.com.quiz.utilits.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class QuizQuestionsViewModel @Inject constructor(
    var repository: QuizQuestionsRepository
) : ViewModel() {


    val questionMutableLiveData: SingleLiveEvent<Resource<List<QuestionModel>>> = SingleLiveEvent()
    val sendResultMutableLiveData: SingleLiveEvent<Resource<String>> = SingleLiveEvent()

    @ExperimentalCoroutinesApi
    fun getQuizQuestions() = viewModelScope.launch {

        questionMutableLiveData.postValue(Resource.Loading())

        repository.getQuizQuestions().collect {

            questionMutableLiveData.postValue(Resource.Success(it))

        }

    }

//    @ExperimentalCoroutinesApi
//    fun sendResult(result: ResultModel?, materialId: String, lessonId: String, lessonModel: LessonModel) = viewModelScope.launch {
//
//        sendResultMutableLiveData.postValue(Resource.Loading())
//        repository.sendResult(result , materialId , lessonId , lessonModel)
//        sendResultMutableLiveData.postValue(Resource.Success())
//
//    }
}