package br.com.if91wallet.vo

import com.google.gson.annotations.SerializedName

class Transaction (
    val id: Long,
    val timestamp: Long,
    val value: Double,
    val success: Boolean,
    val status: String,
    @SerializedName("destination_user") val destinationUser: UserVo
)

class ResponseTransaction(val transaction: Transaction)