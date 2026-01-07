package com.example.zazudot

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.zazudot.model.Occasion
import com.example.zazudot.model.OccasionType
import com.example.zazudot.ui.timeline.OccasionAdapter
import java.time.LocalDate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val data = mutableListOf<Occasion>()
    private lateinit var adapter: OccasionAdapter

    private val addOccasionLauncher =
        registerForActivityResult(
            androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {
                val dataIntent = result.data ?: return@registerForActivityResult

                val name = dataIntent.getStringExtra("name") ?: return@registerForActivityResult
                val date = LocalDate.parse(dataIntent.getStringExtra("date")!!)
                val type = OccasionType.valueOf(
                    dataIntent.getStringExtra("type")!!
                )

                adapter.addOccasion(
                    Occasion(name, date, type)
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rv = findViewById<RecyclerView>(R.id.rvTimeline)
        rv.layoutManager = LinearLayoutManager(this)

        // ---- DUMMY DATA (for now) ----
        data.addAll(
            listOf(
                Occasion("Alice", LocalDate.now().plusDays(2), OccasionType.BIRTHDAY),
                Occasion("John & Mary", LocalDate.now().plusDays(1), OccasionType.WEDDING_ANNIVERSARY),
                Occasion("Bob", LocalDate.now().plusDays(5), OccasionType.BIRTHDAY)
            )
        )

        data.sortBy { it.date }



        adapter = OccasionAdapter(data)
        rv.adapter = adapter

        // ---- Home Icon functionality ----
        findViewById<MaterialToolbar>(R.id.topAppBar)
            .setNavigationOnClickListener {
                finish() // goes back to Zazu Home
            }
        // ---- WireFAB Click ----
        findViewById<FloatingActionButton>(R.id.fabAdd)
            .setOnClickListener {
                addOccasionLauncher.launch(
                    Intent(this, AddOccasionActivity::class.java)
                )
            }
    }
}