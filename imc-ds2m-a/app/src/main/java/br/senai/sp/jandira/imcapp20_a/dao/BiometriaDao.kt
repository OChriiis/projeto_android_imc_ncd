import android.content.Context
import br.senai.sp.jandira.imcapp20_a.dao.ImcDataBase
import br.senai.sp.jandira.imcapp20_a.model.Biometria

class BiometriaDao(val context: Context, val biometria: Biometria) {

    val dbHelper = ImcDataBase.getDatabase(context)
}