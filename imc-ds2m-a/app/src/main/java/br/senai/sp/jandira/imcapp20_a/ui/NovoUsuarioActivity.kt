package br.senai.sp.jandira.imcapp20_a.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import kotlinx.android.synthetic.main.activity_novo_usuario.*
import java.util.*


const val CODE_IMAGE = 100

class NovoUsuarioActivity : AppCompatActivity() {

    var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        // Criar um calendário
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes  = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

//        Detectar o click no texto "Trocar foto"
        tv_trocar_foto.setOnClickListener{
            abrirGaleria()
        }

        //Abrir um DatePikcerDialog
        et_nivel_atividade.setOnClickListener{
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{view, _ano, _mes, _dia ->
                et_nivel_atividade.setText("$_dia/${_mes + 1}/$_ano")

                    var diaZero = "$_dia"
                    var mesZero = "$_mes"
                    if (_dia < 10){
                        diaZero = "0$_dia"
                    }
                    if (_mes < 9){
                        mesZero = "0${_mes + 1}"
                    }

                }, ano, mes, dia)
            dpd.show()
        }


        bt_gravar.setOnClickListener {


            // *** Criar o sharedPreferences
//            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
//
//            val editor = dados.edit()
//            editor.putString("nome", et_nome.text.toString())
//            editor.putString("profissao", et_profissao.text.toString())
//            editor.putInt("peso", et_peso.text.toString().toInt())
//            editor.putInt("idade", et_data_nascimento.text.toString().toInt())
//            editor.putString("email", et_email.text.toString())
//            editor.putString("senha", et_senha.text.toString())
//            editor.apply()

            //Gravar no banco de dados sqlite
            val usuario = Usuario(0,
                et_peso.text.toString(),
                et_senha.text.toString(),
                et_nome.text.toString(),
                et_profissao.text.toString(),
                et_altura.text.toString().toDouble(),
                et_nivel_atividade.text.toString(),
                'M',
            imageBitmap)

            val dao = UsuarioDao(this, usuario)
            dao.gravar()

            Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()



            finish()

        }

    }

    private fun abrirGaleria() {

        // Chamando a galeria de imagens

        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Definindo qual o tipo de conteúdo deverá ser obtido

        intent.type = "image/*"

        // Iniciar a Activity, mas nesse caso nós queremos que essa activity retorne algo pra gnt, a imagem

        startActivityForResult(
            Intent.createChooser(
                intent,
                "Escolha uma foto"
            ),
            CODE_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == -1){

            //recuperar a imagem no stream
            val stream = contentResolver.openInputStream(data!!.data!!)

            //Trnaformar Stream num BitMap

            imageBitmap = BitmapFactory.decodeStream(stream)

            //Colocar imagem no ImageView
            img_profile.setImageBitmap(imageBitmap)

        }
        else {
            Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_LONG).show()
        }
    }

}