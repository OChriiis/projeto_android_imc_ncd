package br.senai.sp.jandira.imcapp20_a.model

import java.time.LocalDate

data class Biometria (
    var id: Int = 0,
    var peso: Double = 0.0,
    var nivelAtividade: Int = 0,
    var dataPesagem: String)
