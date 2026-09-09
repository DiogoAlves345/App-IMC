package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DadosActivity : AppCompatActivity() {

    lateinit var editAltura: EditText
    lateinit var editPeso: EditText
    lateinit var btnContinuar: Button
    lateinit var view1: View
    lateinit var view2: View
    lateinit var seekbarPeso: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editAltura = findViewById<EditText>(R.id.editAltura)
        editPeso = findViewById<EditText>(R.id.editPeso)
        btnContinuar = findViewById<Button>(R.id.btnContinuar)
        view1 = findViewById(R.id.view1)
        view2 = findViewById(R.id.view2)
        seekbarPeso= findViewById(R.id.seekPeso)


        seekbarPeso.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                editPeso.setText(progress.toString())// atualiza o campo de texto junto

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        atualizarEtapa(1)


        btnContinuar.setOnClickListener {
            atualizarEtapa(2)

            val altura = editAltura.text.toString().toDoubleOrNull()
            val peso = editPeso.text.toString().toDoubleOrNull()



            if (altura == null || altura <= 0) {
                editAltura.error = "Preencha o campo corretamente "
                editAltura.requestFocus()
                return@setOnClickListener
            } else if (peso == null || peso <= 0) {
                editPeso.error = "Preencha o campo corretamente "
                editPeso.requestFocus()
                return@setOnClickListener
            }

        // calculo imc

            val imc = peso / (altura * altura)


            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra("IMC", imc)
            startActivity(intent)


        }
    }
    //função passa etapa
    fun atualizarEtapa(etapaAtual: Int) {
        view1.setBackgroundResource(if (etapaAtual >= 1) R.drawable.pill_ativo else R.drawable.pill_inativo)
        view2.setBackgroundResource(if (etapaAtual >= 2) R.drawable.pill_ativo else R.drawable.pill_inativo)
    }

}