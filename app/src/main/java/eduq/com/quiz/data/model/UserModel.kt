package eduq.com.quiz.data.model

import java.io.Serializable


data class UserModel(
    var id: String? = null,
    var userName: String? = null,
    var fullName: String? = null,
    var password: String? = null,
    var createAt: Any? = null,
    var role : Role? = null
) : Serializable