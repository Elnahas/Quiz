package eduq.com.quiz.other

import android.annotation.SuppressLint
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import eduq.com.quiz.utilits.Resource
import eduq.com.quiz.utilits.SingleLiveEvent
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


fun createOrderNumber(): String {

    return StringBuilder().append(System.currentTimeMillis())
//            .append(abs(Random().nextInt()))
        .toString()
}


@SuppressLint("SimpleDateFormat")
internal var simpleDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm")

fun convertMillisecondsToDate(milliseconds: Long): String {
    val date = Date(milliseconds)
    return simpleDateFormat.format(date)
}

//For Change Language For number
//public static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
var decimalFormatDiscount: DecimalFormat =
    DecimalFormat("#.###", DecimalFormatSymbols(Locale.US))
var formatter: NumberFormat = decimalFormatDiscount

fun getDecimalNumber(num: Float): String? {
    return formatter.format(num)
}

fun getDecimalRate(num: Float): String? {
    return DecimalFormat("#.#", DecimalFormatSymbols(Locale.US)).format(num)
}

//This is as generic as it gets, you could use it on any Query, no need to retype it
suspend fun Query.await(): DataSnapshot = suspendCoroutine { cont ->
    addListenerForSingleValueEvent(object : ValueEventListener {

        override fun onCancelled(error: DatabaseError) {
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot != null) {
                cont.resume(snapshot)
            } else {
                cont.resumeWithException(Exception("Null data"))
            }
        }
    })
}

fun <T> SingleLiveEvent<Resource<MutableList<T>>>.removeItemAt(index: Int) {
    if (!this.value!!.data.isNullOrEmpty()) {
//            Timber.w("See Index : %s", index)
        val oldValue = this.value
        oldValue?.data!!.removeAt(index)
        this.value = oldValue
    } else {
        this.value!!.data = mutableListOf()
    }
}


fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}
