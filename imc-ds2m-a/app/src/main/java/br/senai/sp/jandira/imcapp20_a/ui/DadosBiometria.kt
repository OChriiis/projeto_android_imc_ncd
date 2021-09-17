package br.senai.sp.jandira.imcapp20_a.ui

import BiometriaDao
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.model.Biometria
import kotlinx.android.synthetic.main.activity_dados_biometria.*
import kotlinx.android.synthetic.main.activity_novo_usuario.bt_gravar
import kotlinx.android.synthetic.main.activity_novo_usuario.et_data_nascimento
import java.util.*


class DadosBiometria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_biometria)

        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        et_data_pesagem.setOnClickListener {
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                    et_data_nascimento.setText("$_dia/${_mes + 1}/$_ano")

                    var diaZero = "$_dia"
                    var mesZero = "$_mes"
                    if (_dia < 10) {
                        diaZero = "0$_dia"
                    }
                    if (_mes < 9) {
                        mesZero = "0${_mes + 1}"
                    }

                }, ano, mes, dia
            )
            dpd.show()
        }

        val btnGravar: Button = findViewById(R.id.bt_gravar)
        val et_peso: EditText = findViewById(R.id.et_email)
        val spinnerNivelAtividade: Spinner = findViewById(R.id.spinner_nivel_atv)
        val et_data_pesagem: EditText = findViewById(R.id.et_data_pesagem)

        btnGravar.setOnClickListener {
            val biometria = Biometria(
                0,
                et_peso.text.toString().toDouble(),
                et_data_pesagem.text.toString().toInt(),
                spinnerNivelAtividade.toString()
            )

            val dao = BiometriaDao(this, biometria)
            dao.gravar()

            Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()


        }

    }
}