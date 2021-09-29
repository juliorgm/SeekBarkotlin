package br.com.cuiadigital.seekbarkotlin.model

data class UserLimit(
    val max: Double,
    var maxDefinedBUser: Double,
    val min: Double,
    val available: Double,
    val percentageSpent: Double
)
