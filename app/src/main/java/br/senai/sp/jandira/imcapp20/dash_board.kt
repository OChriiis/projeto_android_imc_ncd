package br.senai.sp.jandira.imcapp20

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_dash_board.*

class dash_board : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        preencherDashBoard()

            tv_logout.setOnClickListener{
                val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
                val editor = dados.edit()
                editor.putBoolean("lembrar", false)
                editor.apply()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

    }

    private fun preencherDashBoard(){
        val dados = getSharedPreferences("dados", Context.MODE_PRIVATE)

        tv_profile_name.text = dados.getString("nome", "")
        tv_profile_occupation.text = dados.getString("profissao", "")
        tv_weight.text = dados.getInt("peso", 0).toString()
        tv_age.text = dados.getInt("idade", 0).toString()

        //colocar foto github via url da foto
        val url = "https://avatars.githubusercontent.com/u/77398244?v=4"
        Glide.with(this).load(url).into(iv_profile)
    }
}