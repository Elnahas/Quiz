package eduq.com.quiz.ui.questions

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.data.model.QuestionModel
import eduq.com.quiz.data.model.ResultModel
import eduq.com.quiz.data.model.UserModel
import eduq.com.quiz.other.Constants
import eduq.com.quiz.other.Constants.REF_RESULT
import eduq.com.quiz.ui.viewmodel.QuizQuestionsViewModel
import eduq.com.quiz.utilits.CustomLoading.Companion.hideProgressBar
import eduq.com.quiz.utilits.CustomLoading.Companion.showProgressBar
import eduq.com.quiz.utilits.Resource
import kotlinx.android.synthetic.main.fragment_quiz_questions.*
import javax.inject.Inject

@AndroidEntryPoint
class QuizQuestionsFragment : Fragment(R.layout.fragment_quiz_questions), View.OnClickListener {


    private var mCurrentPosition: Int = 1
    private var mQuestionList: List<QuestionModel>? = null
    private var mSelectedOptionPosition: Int = 0

    val viewModel: QuizQuestionsViewModel by viewModels()

    val arg  : QuizQuestionsFragmentArgs by navArgs()

    var correct: Int = 0
    var wrong: Int = 0
    var missed: Int = 0


    @Inject
    lateinit var sharedPref: SharedPreferences

    lateinit var userModel: UserModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Data User
        val data = sharedPref.getString(Constants.KEY_USER_MODEL_JSON, "")
        userModel = Gson()
            .fromJson<UserModel>(
                data,
                object : TypeToken<UserModel>() {}.type
            )

        viewModel.getQuizQuestions()


        viewModel.questionMutableLiveData.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {


                    val data = it.data

                    mQuestionList = data!!.shuffled()
                    //mQuestionList = mQuestionList!!.shuffled()
                    progressBar.max = mQuestionList!!.size
                    setQuestion()

                }

                is Resource.Error -> {

                }
            }

        })


        viewModel.sendResultMutableLiveData.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    showProgressBar(requireContext(), false)
                }

                is Resource.Success -> {

                    hideProgressBar()

                    val bundle = Bundle()
                    bundle.putInt("results_correct", correct)
                    bundle.putInt("results_wrong", missed)
                    bundle.putInt("results_missed", wrong)
                    bundle.putInt("total_questions", mQuestionList!!.size)
                    bundle.putBoolean("is_before", arg.isBefore)

                    findNavController().navigate(
                        R.id.action_quizQuestionsFragment_to_resultFragment,
                        bundle
                    )

                }

                is Resource.Error -> {

                }
            }

        })

        txtA.setOnClickListener(this)
        txtB.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {


        val question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionList!!.size) {
            btn_submit.text = getString(R.string.finish)
        } else {
            btn_submit.text = getString(R.string.submit)
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question.question
        txtA.text = question.optionOne
        txtB.text = question.optionTwo
    }

    private fun defaultOptionsView() {

        chkA.setImageResource(R.drawable.ic_check_off)
        chkB.setImageResource(R.drawable.ic_check_off)

    }

    override fun onClick(v: View?) {


        when (v?.id) {
            R.id.txtA -> {
                selectedOptionView(chkA, 1)
            }
            R.id.txtB -> {
                selectedOptionView(chkB, 2)
            }

            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {

                    if (btn_submit.text == getString(R.string.submit))
                        missed += 1

                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {


                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)

                    if (question!!.correctOption != mSelectedOptionPosition) {
                        wrong += 1
                    } else {
                        correct += 2
                    }

                    if (mCurrentPosition == mQuestionList!!.size) {
                        btn_submit.text = getString(R.string.finish)
                    } else {
                        btn_submit.text = getString(R.string.submit)
                    }

                    mSelectedOptionPosition = 0


                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {


                            showProgressBar(requireContext(), false)

                            //Send to Db Result
                            val result =
                                ResultModel()
                            result.createAt = System.currentTimeMillis().toString()
                            result.resultCorrect = correct
                            result.resultWrong = wrong
                            result.uid =  userModel.userName
                            result.before = arg.isBefore

                            FirebaseDatabase.getInstance()
                                .getReference(REF_RESULT)
                                .child(result.createAt.toString())
                                .setValue(result).addOnSuccessListener {

                                    hideProgressBar()

                                    val bundle = Bundle()

                                    bundle.putSerializable("resultModel", result)
                                    bundle.putInt("totalQ", mQuestionList!!.size)


                                    findNavController().navigate(
                                        R.id.action_quizQuestionsFragment_to_resultFragment,
                                        bundle
                                    )

                                }


                        }
                    }
                }

            }
        }


    }

    private fun selectedOptionView(check: ImageView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        check.setImageResource(R.drawable.ic_check_on)
    }
}