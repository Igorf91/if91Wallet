package br.com.if91wallet.vo

data class CardVo(
    val cardNumber : Long,
    val name : String,
    val expirationDate : Int,
    val cvv : String
)