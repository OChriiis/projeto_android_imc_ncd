package br.senai.sp.jandira.imcapp20

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_novo_usuario.*

class activity_novo_usuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        btn_gravar.setOnClickListener{
            //** Criar o sharedPreferences
            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

            val editor = dados.edit()
            editor.putString("nome", et_nome.text.toString())
            editor.putString("profissao", et_profissao.text.toString())
            editor.putInt("peso", et_peso.text.toString().toInt())
            editor.putInt("idade", et_idade.toString().toInt())
            editor.putString("e-mail", et_email.toString())
            editor.putString("senha", et_senha.toString())
            editor.apply()

            Toast.makeText(this, "Dados gravados com sucesso!", Toast.LENGTH_SHORT).show()

            finish()
        }

    }
}