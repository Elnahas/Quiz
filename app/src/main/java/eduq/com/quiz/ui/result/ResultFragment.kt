package eduq.com.quiz.ui.result

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.data.model.UserModel
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject


@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    lateinit var userModel: UserModel

    val arg : ResultFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        results_correct_text.text = arg.resultModel.resultCorrect.toString()
        results_wrong_text.text = arg.resultModel.resultWrong.toString()

        //Calculate Progress
        val total: Long = arg.resultModel.resultCorrect!!.toLong() + arg.resultModel.resultWrong!!.toLong()
        val percent: Long = arg.resultModel.resultCorrect!!.toLong() * 100 / 40

        results_percent.text = "$percent%"
        results_progress.progress = percent.toInt()

        results_home_btn.setOnClickListener {

            if (arg.resultModel.resultCorrect!!.toLong() >= 30)
            {
                findNavController().navigate(R.id.action_resultFragment_to_homeFragment)

            }
            else
            {
                //Go to study
                findNavController().navigate(R.id.action_resultFragment_to_lessonFragment)
            }

        }

        if (arg.resultModel.resultCorrect!!.toLong() >= 30){
            results_home_btn.text = "Go To Home"
        }else{
            results_home_btn.text = "Go To Lessons"
        }

    }

}