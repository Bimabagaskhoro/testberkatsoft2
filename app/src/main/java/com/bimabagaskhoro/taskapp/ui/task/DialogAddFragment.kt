package com.bimabagaskhoro.taskapp.ui.task

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bimabagaskhoro.taskapp.R
import com.bimabagaskhoro.taskapp.data.Resource
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.domain.model.TaskModel
import com.bimabagaskhoro.taskapp.vm.DatasViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DialogAddFragment : DialogFragment() {
    private val viewModel: DatasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSave : Button = view.findViewById(R.id.btn_save_popup)
        val btnParticipant : Button = view.findViewById(R.id.btn_participant_dialog)
        val btnClose : ImageView = view.findViewById(R.id.btn_close_popup)
        val edtNk : EditText = view.findViewById(R.id.edt_nama_kegiatan_popup)
        val edtNp : EditText = view.findViewById(R.id.edt_nama_peserta_popup)
        val progressBar : ProgressBar = view.findViewById(R.id.progressbar_dialog)

        val calendarDay : TextView = view.findViewById(R.id.tv_result_calender)
        val timeStart : TextView = view.findViewById(R.id.tv_result_time_start)
        val timeEnd : TextView = view.findViewById(R.id.tv_result_time_end)

        val btnDay : ImageView = view.findViewById(R.id.btn_calender)
        val btnTimeStart : ImageView = view.findViewById(R.id.btn_time_start)
        val btnTimeEnd : ImageView = view.findViewById(R.id.btn_time_end)

        val c = Calendar.getInstance()
        val dpd = DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "dd-MM-yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            calendarDay.text = sdf.format(c.time)
        }

        btnDay.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(
                    it1, dpd,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        btnClose.setOnClickListener {
            dismiss()
        }

        btnTimeStart.setOnClickListener {
            val c = Calendar.getInstance()
            val tpd = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                c.set(Calendar.MINUTE, minute)

                timeStart.text = SimpleDateFormat("HH:mm").format(c.time)
            }
            TimePickerDialog(context, tpd, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }

        btnTimeEnd.setOnClickListener {
            val c = Calendar.getInstance()
            val tpd = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                c.set(Calendar.MINUTE, minute)

                timeEnd.text = SimpleDateFormat("HH:mm").format(c.time)
            }
            TimePickerDialog(context, tpd, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }

        btnParticipant.setOnClickListener {
            val name = edtNp.text.trim().toString()
            val data  = ParticipantModel(name, "")

            viewModel.postParticipant(data).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        viewModel.idValue(data.id)

                    }
                    is Resource.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

        }

        btnSave.setOnClickListener {
            val mParticipantId = viewModel.getDatas().toString().trim()
            val mNameTask = edtNk.text.toString().trim()
            val mTimeStart = timeStart.text.toString().trim()
            val mTimeEnd = timeEnd.text.toString().trim()
            val mDay = calendarDay.text.toString().trim()
            val data  = TaskModel(mNameTask,"peserta-3HfWEjw_lKWe904i",mTimeStart,mTimeEnd,mDay)
            viewModel.postTask(data).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        viewModel.clearData()

                    }
                    is Resource.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}