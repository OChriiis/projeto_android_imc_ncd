package br.senai.sp.jandira.imcapp20_a.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import br.senai.sp.jandira.imcapp20_a.R


class DadosBiometria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_biometria)

        val btnGravar: Button = findViewById(R.id.bt_gravar)
        val etPeso: EditText = findViewById(R.id.et_email)
        val spinnerNivelAtividade: Spinner = findViewById(R.id.spinner_nivel_atv)
    }

}