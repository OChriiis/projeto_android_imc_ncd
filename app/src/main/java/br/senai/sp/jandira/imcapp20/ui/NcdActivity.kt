package br.senai.sp.jandira.imcapp20.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import br.senai.sp.jandira.imcapp20.R

class NcdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ncd)

        val btnCalcular: Button = findViewById(R.id.button_calcular_ncd)
        val edPeso: EditText = findViewById(R.id.ed_peso)
        val spinnerIdade: Spinner = findViewById(R.id.spinner_idade)
        val spinnerNvlAtv: Spinner = findViewById(R.id.spinner_nvl_atv)
        val radiogroup: RadioGroup = findViewById(R.id.radio_group)
        val radioFeminino: RadioButton = findViewById(R.id.Feminino)
        val radioMasculino: RadioButton = findViewById(R.id.Masculino)



        btnCalcular.setOnClickListener {
            if (edPeso.text.isEmpty()) {
                edPeso.error = "Este campo é obrigatório!"
            }

            if (edPeso.text.isNotEmpty()) {

                val peso: Double = edPeso.text.toString().toDouble()
                val NvlAtv = spinnerNvlAtv.selectedItemPosition
                val Idade = spinnerIdade.selectedItemPosition
                val intent = Intent(this, ResultadoNcdActivity::class.java)

                intent.putExtra("peso", peso)
                intent.putExtra("nivelAtividade", NvlAtv)
                intent.putExtra("faixaIdade", Idade)

                if (radioFeminino.isChecked) {
                    val genero = 'f'
                    intent.putExtra("genero", genero)

                    startActivity(intent)

                } else if (radioMasculino.isChecked) {
                    val genero = 'm'
                    intent.putExtra("genero", genero)

                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Escolha o seu sexo!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}