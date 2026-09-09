package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {

    lateinit var tvResultado : TextView
    lateinit var tvDiagnostico : TextView
    lateinit var btnRecalcular : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvResultado = findViewById(R.id.tvResultado)
        tvDiagnostico = findViewById(R.id.tvDiagnostico)
        btnRecalcular = findViewById(R.id.btnRecalcular)

        val imc = intent.getDoubleExtra("IMC",0.0)

        tvResultado.text = "%.2f".format(imc)


         if (imc <= 18.5) {
                tvDiagnostico.text = "Abaixo do peso"
            } else if (imc > 18.5 && imc <= 24.9) {
                tvDiagnostico.text = "Peso normal"
            } else if (imc > 24.9 && imc <= 29.9) {
                tvDiagnostico.text= "Sobre peso"

            } else if (imc > 29.9 && imc <= 34.9) {
                tvDiagnostico.text = "Obsidade grau 1"
            }
        btnRecalcular.setOnClickListener {
            val intent = Intent(this, DadosActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}