package eduq.com.quiz.data.model

import java.io.Serializable

data class LessonModel(
    var id:String?=null,
    var lessonNumber:String?=null,
    var lessonTitle:String?=null,
    var videoUrl:String?=null
) : Serializable