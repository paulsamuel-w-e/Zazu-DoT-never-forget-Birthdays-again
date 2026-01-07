package com.example.zazudot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val btnViewTimeline = findViewById<Button>(R.id.btnViewTimeline)

        // ---- FAKE UPCOMING EVENT (for now) ----
        val name = "Alice"
        val occasion = "Birthday" // or "Wedding Anniversary"
        val eventDate = LocalDate.now().plusDays(0) // try 0, 1, 2, 5

        val today = LocalDate.now()
        val daysLeft = ChronoUnit.DAYS.between(today, eventDate)

        tvMessage.text = when {
            daysLeft == 0L -> "🎉 Today is $name’s $occasion!"
            daysLeft == 1L -> "⏰ $name’s $occasion is tomorrow"
            daysLeft > 1L -> "📅 $name’s $occasion in $daysLeft days"
            else -> "Nothing today, relax 😊"
        }

        btnViewTimeline.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
