package eduq.com.quiz.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import eduq.com.quiz.R
import eduq.com.quiz.adapters.ResultAdapter
import eduq.com.quiz.data.model.ResultModel
import eduq.com.quiz.other.Constants
import elnahas.deliveroo.utilits.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_result_students.*

class ResultStudentsFragment : Fragment(R.layout.fragment_result_students) {

    lateinit var resultAdapter : ResultAdapter

    val args : ResultStudentsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()

        FirebaseDatabase.getInstance().getReference(Constants.REF_RESULT)
            .orderByChild("uid")
            .equalTo(args.userModel.userName!!)
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    val resultModel = ArrayList<ResultModel>()
                    snapshot.children.forEach {
                        val result = it.getValue(ResultModel::class.java)
                        result!!.id = it.key

                        resultModel.add(result)
                    }

                    resultAdapter.differ.submitList(resultModel)


                }

            })

        img_back.setOnClickListener {
            findNavController().navigateUp()
        }

        txt_title.text = args.userModel.fullName

    }

    private fun setupRecyclerView() {
        resultAdapter = ResultAdapter()
        recycler_view.apply {
            adapter = resultAdapter
            layoutManager = LinearLayoutManager(activity )
            addItemDecoration(SpacingItemDecoration(requireContext(), R.dimen.app_margin))
        }

    }

}