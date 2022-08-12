package eduq.com.quiz.ui.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import eduq.com.quiz.R
import eduq.com.quiz.other.Constants
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_symptoms.*
import kotlinx.android.synthetic.main.fragment_symptoms.toolbar
import android.widget.TextView




class SymptomsFragment : Fragment(R.layout.fragment_symptoms) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        btn_result.setOnClickListener {

            when {

                chk_2_tachycardia.isChecked && chk_4_shortness.isChecked && chk_5_chest.isChecked && chk_6_dizziness.isChecked -> {
                    showDialog("The patient need for ECG and cardiopulmonary resuscitation")
                }

                chk_2_tachycardia.isChecked && chk_4_shortness.isChecked && chk_5_chest.isChecked && chk_7_fainting.isChecked -> {
                    showDialog("The patient need for ECG, cardiopulmonary resuscitation and rest")
                }

                chk_2_tachycardia.isChecked && chk_4_shortness.isChecked && chk_5_chest.isChecked && chk_8_fatigue.isChecked -> {
                    showDialog("The patient need for ECG and Pacemaker device")
                }


                chk_1_palpitations.isChecked && chk_2_tachycardia.isChecked && chk_7_fainting.isChecked -> {
                    showDialog("The patient need for ECG and cardiopulmonary resuscitation")
                }

                chk_3_tachycardia.isChecked && chk_5_chest.isChecked && chk_6_dizziness.isChecked -> {
                    showDialog("The patient need for ECG and Pacemaker device")
                }

                chk_3_tachycardia.isChecked && chk_6_dizziness.isChecked && chk_7_fainting.isChecked -> {
                    showDialog("The patient need for ECG, Pacemaker device and cardiopulmonary resuscitation")
                }

                chk_2_tachycardia.isChecked && chk_4_shortness.isChecked && chk_5_chest.isChecked -> {
                    showDialog("The patient need for ECG, rest and Cardioversion device")
                }

                chk_1_palpitations.isChecked && chk_2_tachycardia.isChecked && chk_8_fatigue.isChecked -> {
                    showDialog("The patient need for ECG, Cardioversion device  and rest")
                }

                chk_1_palpitations.isChecked && chk_2_tachycardia.isChecked -> {
                    showDialog("The patient need for ECG and Cardioversion device")
                }

                chk_4_shortness.isChecked && chk_2_tachycardia.isChecked -> {
                    showDialog("The patient need for ECG and Cardioversion device")
                }

                chk_3_tachycardia.isChecked && chk_7_fainting.isChecked -> {
                    showDialog("The patient need for ECG, Pacemaker device and rest")
                }

                chk_3_tachycardia.isChecked && chk_8_fatigue.isChecked -> {
                    showDialog("The patient need for ECG, Pacemaker device and rest")
                }

                chk_1_palpitations.isChecked -> {
                    showDialog("The patient need for ECG and rest")
                }
                chk_2_tachycardia.isChecked -> {
                    showDialog("The patient need for ECG and cardioversion device")
                }
                chk_3_tachycardia.isChecked -> {
                    showDialog("The patient need for ECG and Pacemaker device")
                }
                chk_4_shortness.isChecked -> {
                    showDialog("The patient need for ECG and rest")
                }
                chk_5_chest.isChecked -> {
                    showDialog("The patient need for ECG and going to hospital")
                }
                chk_6_dizziness.isChecked -> {
                    showDialog("The patient need for ECG and rest")
                }
                chk_7_fainting.isChecked -> {
                    showDialog("The patient need for ECG and cardiopulmonary resuscitation")
                }
                chk_8_fatigue.isChecked -> {
                    showDialog("The patient need for ECG and rest")
                }

                else -> {
                    showDialog("")
                }
            }

        }
    }

    private fun showDialog(msg: String) {
        val dialog =  MaterialAlertDialogBuilder(requireContext())
            .setTitle("Result")
            .setMessage("${msg}")
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }.create()

        val textView = dialog.findViewById<TextView>(android.R.id.message)
        if (textView != null) {
            textView.textSize = 50f
        }

        dialog.show()
    }

}