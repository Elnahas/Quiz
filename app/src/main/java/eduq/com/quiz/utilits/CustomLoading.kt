package eduq.com.quiz.utilits

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.Window
import eduq.com.quiz.R

class CustomLoading : Dialog {

    private var mProgressbar: CustomLoading? = null
    private var mOnDissmissListener: DialogInterface.OnDismissListener? = null

    private constructor(context: Context) : super(context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_dialog_loading)
        this.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    }

    constructor(context: Context, instance: Boolean?) : super(context) {
        mProgressbar = CustomLoading(context)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (mOnDissmissListener != null) {
            mOnDissmissListener!!.onDismiss(this)
        }
    }

    private fun setListener(listener: DialogInterface.OnDismissListener) {
        mOnDissmissListener = listener
    }

    fun showProgress(
        context: Context?,
        cancelable: Boolean,
        message: String?
    ) {
        if (mProgressbar != null && mProgressbar!!.isShowing) {
            mProgressbar!!.cancel()
        }
        mProgressbar!!.setCancelable(cancelable)
        mProgressbar!!.show()
    }

    fun showProgress(context: Context?, cancelable: Boolean) {
        if (mProgressbar != null && mProgressbar!!.isShowing) {
            mProgressbar!!.cancel()
        }
        mProgressbar!!.setCancelable(cancelable)
        mProgressbar!!.show()
    }

    companion object {
        private var mCustomProgressbar: CustomLoading? = null

        @JvmOverloads
        fun showProgressBar(
            context: Context,
            cancelable: Boolean,
            message: String? = null
        ) {
            if (mCustomProgressbar != null && mCustomProgressbar!!.isShowing) {
                mCustomProgressbar!!.cancel()
            }
            mCustomProgressbar = CustomLoading(context)
            mCustomProgressbar!!.setCancelable(cancelable)
            mCustomProgressbar!!.show()
        }

        fun showProgressBar(
            context: Context,
            listener: DialogInterface.OnDismissListener
        ) {
            if (mCustomProgressbar != null && mCustomProgressbar!!.isShowing) {
                mCustomProgressbar!!.cancel()
            }
            mCustomProgressbar = CustomLoading(context)
            mCustomProgressbar!!.setListener(listener)
            mCustomProgressbar!!.setCancelable(java.lang.Boolean.TRUE)
            mCustomProgressbar!!.show()
        }

        fun hideProgressBar() {
            if (mCustomProgressbar != null) {
                mCustomProgressbar!!.dismiss()
            }
        }

        fun showListViewBottomProgressBar(view: View) {
            if (mCustomProgressbar != null) {
                mCustomProgressbar!!.dismiss()
            }
            view.visibility = View.VISIBLE
        }

        fun hideListViewBottomProgressBar(view: View) {
            if (mCustomProgressbar != null) {
                mCustomProgressbar!!.dismiss()
            }
            view.visibility = View.GONE
        }
    }
}