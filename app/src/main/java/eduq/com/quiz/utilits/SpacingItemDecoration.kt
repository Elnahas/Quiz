package elnahas.deliveroo.utilits

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State

class SpacingItemDecoration(
    context: Context,
    @DimenRes spacingId: Int
) : RecyclerView.ItemDecoration() {

    private val spacing: Int = context.resources.getDimensionPixelSize(spacingId)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        super.getItemOffsets(outRect, view, parent, state)
        val layoutManager = parent.layoutManager ?: return
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        outRect.apply {
            when (layoutManager) {

                is GridLayoutManager -> {
                    val spanCount = layoutManager.spanCount
                    val column = position % spanCount
                    left = spacing/2 //column * spacing / spanCount
                    right = spacing/2 //- (column + 1) * spacing / spanCount
                    if (position >= spanCount) {
                        top = spacing
                    }
                }
                is LinearLayoutManager -> {
                    if (layoutManager.orientation == RecyclerView.VERTICAL) {

                        if (position == 0)
                            top = spacing

                        bottom = spacing
                        right = spacing
                        left = spacing

                    } else {
                        //Here must chk lang
//                        if (position == 0) {
//                            right = spacing
//                        }
//                        else if (itemCount > 0 && position == itemCount - 1) {
//                            left = spacing

                        top = spacing
                        bottom = spacing
                        right = spacing
                        left = spacing

                        }
                    }
                }
            }
        }

}