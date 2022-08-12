package eduq.com.quiz.ui.lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eduq.com.quiz.R
import eduq.com.quiz.adapters.LessonAdapter
import eduq.com.quiz.data.model.LessonModel
import elnahas.deliveroo.utilits.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_lesson.*
import android.content.ActivityNotFoundException

import android.content.Intent
import android.net.Uri


@AndroidEntryPoint
class LessonFragment : Fragment(R.layout.fragment_lesson) {

    lateinit var lessonAdapter : LessonAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val listLesson = ArrayList<LessonModel>()

//        listLesson.add(LessonModel("1" , "1" , "Lesson 1" , ""))
        listLesson.add(LessonModel("1" , "1" , "Heart Disease Causes, Symptoms and Treatment Options" , "k54qJkM29SQ"))
        listLesson.add(LessonModel("2" , "2" , "Heart Attack Warming Signs" , "rwZj5aJacLc"))
        listLesson.add(LessonModel("3" , "3" , "What happens during a heart attack" , "3Ld5U0Dpme4"))
        listLesson.add(LessonModel("4" , "4" , "About Your Heart Attack Nucleus Health" , "JqpfuSbY4Hs"))
        listLesson.add(LessonModel("5" , "5" , "How to Treat a Heart Attack First Aid" , "PZ3wD8xkHrU"))

        lessonAdapter.differ.submitList(listLesson)


        lessonAdapter.setOnItemClickListener { lessonModel, view ->

//            val bundle = Bundle()
//            bundle.putSerializable("lessonModel" , lessonModel)
//            findNavController().navigate(R.id.action_homeFragment_to_lessonFragment , bundle)
            watchYoutubeVideo(lessonModel.videoUrl!!)

        }

        img_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        lessonAdapter = LessonAdapter()
        recycler_view.apply {
            adapter = lessonAdapter
            layoutManager = LinearLayoutManager(activity )
            addItemDecoration(SpacingItemDecoration(requireContext(), R.dimen.app_margin))
        }

    }


    fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }
}