package eduq.com.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import eduq.com.quiz.R
import eduq.com.quiz.data.model.LessonModel
import kotlinx.android.synthetic.main.item_lesson.view.*

class LessonAdapter() : RecyclerView.Adapter<LessonAdapter.ViewHolder>() {


    val diffCallback = object : DiffUtil.ItemCallback<LessonModel>() {
        override fun areItemsTheSame(oldItem: LessonModel, newItem: LessonModel): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: LessonModel,
            newItem: LessonModel
        ): Boolean {

            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson ,parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val lessonModel = differ.currentList[position]

        holder.itemView.apply {

            txt_lesson_number.text = "Lesson ${position+1}"
            txt_lesson_name.text = lessonModel.lessonTitle


            setOnClickListener {
                onItemClickListener?.let { it(lessonModel, holder.itemView) }
            }

        }
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)


    private var onItemClickListener: ((LessonModel, View) -> Unit)? = null

    fun setOnItemClickListener(listener: (LessonModel, View) -> Unit) {
        onItemClickListener = listener
    }

}