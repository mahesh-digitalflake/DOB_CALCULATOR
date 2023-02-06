package com.example.dob_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView?=null
    private var tvAgeInMinute:TextView?=null
    private var tvAgeInMonths:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker:Button=findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinute=findViewById(R.id.tvAgeInMinute)
        tvAgeInMonths=findViewById(R.id.tvAgeInMonths)
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }
    }

    fun clickDatePicker()
    {
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,{
            view,selectedyear,selectedmonth,selectedDayOfMonth->
            //Toast.makeText(this,"btnDatePicker Pressed",Toast.LENGTH_LONG).show()

            val selectedDate="$selectedDayOfMonth/${selectedmonth+1}/$selectedyear"
            tvSelectedDate?.text = selectedDate

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)

            //Selected Date
            val selectedDateInMinute =theDate.time/60000

            //Value of Current Date
            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes=currentDate.time/60000

            val differenceInMinute=currentDateInMinutes-selectedDateInMinute

            tvAgeInMinute?.text= differenceInMinute.toString()

            //Selected Date in Months
            val selectedDateInMonths=selectedDateInMinute/43800

            val currentDateInMonths=currentDateInMinutes/43800

            val differenceInMonths=currentDateInMonths-selectedDateInMonths
                tvAgeInMonths?.text=differenceInMonths.toString()

        },year,month,day).show()


    }
}