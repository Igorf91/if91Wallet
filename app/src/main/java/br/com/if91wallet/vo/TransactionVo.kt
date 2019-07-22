package br.com.if91wallet.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TransactionVo (
    val id: Long,
    val timestamp: Long,
    val value: Double,
    val success: Boolean,
    val status: String,
    @SerializedName("destination_user") val destinationUser: UserVo
) : Parcelable

class ResponseTransaction(val transaction: TransactionVo)