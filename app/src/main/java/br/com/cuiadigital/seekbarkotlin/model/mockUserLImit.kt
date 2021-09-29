package br.com.cuiadigital.seekbarkotlin.model

object mockUserLImit {
    fun getUserLimit(): UserLimit{
        return UserLimit(
            7000.0,
            5000.0,
            50.0,
            3000.0,
            2000.0,
        )
    }
}