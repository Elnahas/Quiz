package eduq.com.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import eduq.com.quiz.R
import eduq.com.quiz.data.model.UserModel
import kotlinx.android.synthetic.main.item_lesson.view.*

class UsersAdapter() : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    val diffCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: UserModel,
            newItem: UserModel
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

        val userModel = differ.currentList[position]

        holder.itemView.apply {

            txt_lesson_name.text = userModel.fullName


            setOnClickListener {
                onItemClickListener?.let { it(userModel, holder.itemView) }
            }

        }
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)


    private var onItemClickListener: ((UserModel, View) -> Unit)? = null

    fun setOnItemClickListener(listener: (UserModel, View) -> Unit) {
        onItemClickListener = listener
    }

}