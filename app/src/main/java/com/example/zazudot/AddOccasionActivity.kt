package com.example.zazudot

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.zazudot.model.OccasionType
import com.google.android.material.appbar.MaterialToolbar
import java.time.LocalDate

class AddOccasionActivity : AppCompatActivity() {

    private var selectedDate: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_occasion)

        // ---- Top bar back button ----
        findViewById<MaterialToolbar>(R.id.topAppBar)
            .setNavigationOnClickListener { finish() }


        // ---- Spinner setup ----
        val spinner = findViewById<Spinner>(R.id.spinnerType)
        val labels = OccasionType.values().map { it.label }
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            labels
        )

        // ---- Date picker ----
        val btnPickDate = findViewById<Button>(R.id.btnPickDate)
        btnPickDate.setOnClickListener {
            val today = LocalDate.now()
            DatePickerDialog(
                this,
                { _, y, m, d ->
                    selectedDate = LocalDate.of(y, m + 1, d)
                    btnPickDate.text = selectedDate.toString()
                },
                today.year,
                today.monthValue - 1,
                today.dayOfMonth
            ).show()
        }

        // ---- Save button ----
        findViewById<Button>(R.id.btnSave).setOnClickListener {

            val name = findViewById<EditText>(R.id.etName)
                .text.toString().trim()

            if (name.isEmpty() || selectedDate == null) {
                Toast.makeText(
                    this,
                    "Please enter name and date",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val type =
                OccasionType.values()[spinner.selectedItemPosition]

            val result = Intent().apply {
                putExtra("name", name)
                putExtra("date", selectedDate.toString())
                putExtra("type", type.name)
            }

            setResult(RESULT_OK, result)
            finish()
        }
    }
}

