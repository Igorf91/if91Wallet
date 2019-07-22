package br.com.if91wallet.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserVo (
    val id: Long,
    val name: String,
    val img: String,
    val username: String
) : Parcelable