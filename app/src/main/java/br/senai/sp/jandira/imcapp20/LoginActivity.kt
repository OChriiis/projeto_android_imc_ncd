package br.senai.sp.jandira.imcapp20

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_Login_Activity.*

class LoginActivity : AppCompatActivity() {

    lateinit var editUser: EditText
    lateinit var editPassword: EditText
    lateinit var btnLogin: Button
    lateinit var tvMensagemErro: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_Login_Activity)

        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
        val lembrar = dados.getBoolean("lembrar", false)

        if (lembrar == true){
            abrirDashBoard()
        }

        editUser = findViewById(R.id.ed_user)
        editPassword = findViewById(R.id.ed_password)
        btnLogin = findViewById(R.id.btn_login)
        tvMensagemErro = findViewById(R.id.tv_mensagem_de_erro)

        btnLogin.setOnClickListener {
            login()
        }

    }

    private fun abrirDashBoard() {
        val intent = Intent( this, dash_board::class.java)
        startActivity(intent)
    }

    private fun login() {

        var user = editUser.text.toString()
        var pass =  editPassword.text.toString()

        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        val userPreferences = dados.getString("email", "Não encontrado")
        val passPreferences = dados.getString("senha", "Não encpntrado")

        if (user == userPreferences && pass == passPreferences){

            //gravar o lembrar no SharedPreferences

            val editor = dados.edit()
            editor.putBoolean("lembrar", check_lembrar.isChecked)
            editor.apply()
            abrirDashBoard()
        } else {
            tvMensagemErro.text = "Usuário ou senha incorreto"
        }
    }


}