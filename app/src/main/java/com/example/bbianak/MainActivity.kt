package com.example.bbianak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bbianak.R

class MainActivity : AppCompatActivity() {
    private lateinit var namaAnakEditText: EditText
    private lateinit var tahunEditText: EditText
    private lateinit var bulanEditText: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        namaAnakEditText = findViewById(R.id.namaAnakEditText)
        tahunEditText = findViewById(R.id.tahunEditText)
        bulanEditText = findViewById(R.id.bulanEditText)
        resultTextView = findViewById(R.id.resultTextView)
    }

    fun hitungBbIdeal(view: View) {
        val nama = namaAnakEditText.text.toString().trim()
        val tahun = tahunEditText.text.toString().toIntOrNull() ?: 0
        val bulan = bulanEditText.text.toString().toIntOrNull() ?: 0

        if (nama.isNotEmpty()) {
            val umurDalamBulan = tahun * 12 + bulan

            if (tahun <= 6 && umurDalamBulan < 84 && bulan <= 12) {
                val bbIdeal = calculateBbIdeal(tahun, bulan)
                resultTextView.text = "$nama \nUsia $tahun tahun, $bulan bulan \nBerat Badan Idealnya adalah $bbIdeal kg"
            } else {
                Toast.makeText(this, "Hanya Bisa Menghitung Anak Di Umur 7 Tahun Kebawah!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Isi Nama Anak!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun calculateBbIdeal(tahun: Int, bulan: Int): Double {
        val ageInMonths = tahun * 12 + bulan

//        return ageInMonths * 0.5

        return when {
            ageInMonths < 24 -> ageInMonths * 0.5
            ageInMonths < 84 -> ageInMonths * 0.4
            else -> throw IllegalArgumentException("Umur anak melebihi 5 tahun")
        }
    }


}