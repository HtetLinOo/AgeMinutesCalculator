package com.example.ageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndate = findViewById<Button>(R.id.btndatepicker)


        btndate.setOnClickListener { v ->

            clickDatePicker(v)
        }
    }

    fun clickDatePicker(v: View) {
        val myCalendar = Calendar.getInstance()
        val y = myCalendar.get(Calendar.YEAR)
        val m = myCalendar.get(Calendar.MONTH)
        val d = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selecteddayOfMonth ->

            val selectdatetv = findViewById<TextView>(R.id.selectedtv)
            val agemintv = findViewById<TextView>(R.id.agetv)

            val selectedDate = "$selecteddayOfMonth/${selectedmonth + 1}/$selectedyear"
            selectdatetv.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyyy", Locale.ENGLISH)
          val theDate = sdf.parse(selectedDate)
           val selectedDateInMinutes = theDate!!.time / 60000

         //   val theDate=sdf.format(selectedDate)
         //   val selectedDateInMinutes=theDate!!.get(Calendar.DAY_OF_WEEK)

         val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
           val currentInMinutes = currentdate!!.time / 60000


            val diffenttimeInMinutes = currentInMinutes - selectedDateInMinutes
            agemintv.setText(diffenttimeInMinutes.toString())


        },
                y, m, d)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}
