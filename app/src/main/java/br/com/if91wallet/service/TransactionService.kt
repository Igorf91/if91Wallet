package br.com.if91wallet.service

import br.com.if91wallet.vo.ResponseTransaction
import br.com.if91wallet.vo.TransactionRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionService {

    @POST("transaction")
    fun pay(@Body request : TransactionRequest): Call<ResponseTransaction>
}