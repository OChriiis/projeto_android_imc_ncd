package br.senai.sp.jandira.imcapp20_a.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.utils.converterBase64ParaBitmap
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val caixaDeDialogo = AlertDialog.Builder(this)
        caixaDeDialogo.setTitle("Cadastro não finalizado!")
        caixaDeDialogo.setMessage("O seu cadastro ainda não foi finalizado. Deseja conclui-lo agora?")
        caixaDeDialogo.setPositiveButton("Sim") { dialogInterface: DialogInterface, i: Int ->
            abrirTelaBiometria()
        }
        caixaDeDialogo.setNegativeButton("Não") { dialogInterface: DialogInterface, i: Int ->
            finish()

        }
        caixaDeDialogo.show()

        preencherDashBoard()

        tv_logout.setOnClickListener {
            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putBoolean("lembrar", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun preencherDashBoard() {

        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        tv_profile_name.text = dados.getString("nome", "")
        tv_profile_occupation.text = dados.getString("profissao", "")
        tv_weight.text = dados.getInt("peso", 0).toString()
        tv_age.text = dados.getString("idade", "")

        val imagemBase64 = dados.getString("foto", "")
        val imagemBitmap = converterBase64ParaBitmap(imagemBase64)

        iv_profile.setImageBitmap(imagemBitmap)


        // *** Colocar foto do Github no ImageView
//        val url = "https://avatars.githubusercontent.com/u/77398244?s=400&u=d377a730eb54e25a0a206fe3133e4d8d9c8466ea&v=4"
//        Glide.with(this).load(url).into(iv_profile)

    }

    private fun abrirTelaBiometria() {
        val intent = Intent(this, DadosBiometria::class.java)
        startActivity(intent)
        finish()
    }


}