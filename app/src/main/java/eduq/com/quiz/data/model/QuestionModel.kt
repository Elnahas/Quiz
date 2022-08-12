package eduq.com.quiz.data.model

data class QuestionModel(

    val id: Int?=0,
    val question: String?="",
    val optionOne: String?="",
    val optionTwo: String?="",
    val correctOption: Int?=0

)

