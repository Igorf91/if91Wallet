package br.com.if91wallet.vo

import com.google.gson.annotations.SerializedName

class TransactionRequest (
    @SerializedName("card_number") val cardNumber: Long,
    val cvv: Int,
    val value: Float,
    @SerializedName("expiry_date") val expirationDate: String,
    @SerializedName("destination_user_id") val destinationUserId: Long
) {
    constructor(value: Float, cardVo: CardVo, userVo: UserVo)
            : this(cardVo.cardNumber, cardVo.cvv, value, cardVo.expirationDate, userVo.id)
}