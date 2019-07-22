package br.com.if91wallet.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardVo(
    val cardNumber : Long,
    val name : String,
    val expirationDate : String,
    val cvv : Int
) : Parcelable