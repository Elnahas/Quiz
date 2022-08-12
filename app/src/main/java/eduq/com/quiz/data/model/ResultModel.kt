package eduq.com.quiz.data.model

import java.io.Serializable

data class ResultModel(
    var id:String?=null,
    var createAt:String?=null,
    var uid:String?=null,
    var resultCorrect:Int?=null,
    var resultWrong:Int?=null,
    var before:Boolean?=null,
) : Serializable