package br.com.if91wallet.vo

data class CardVo(
    val cardNumber : Int,
    val name : String,
    val expirationDate : Int,
    val cvv : String
)