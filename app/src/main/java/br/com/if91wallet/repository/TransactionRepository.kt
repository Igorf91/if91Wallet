package br.com.if91wallet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.if91wallet.service.TransactionService
import br.com.if91wallet.util.RetrofitFactory
import br.com.if91wallet.util.callback
import br.com.if91wallet.vo.CardVo
import br.com.if91wallet.vo.Transaction
import br.com.if91wallet.vo.TransactionRequest
import br.com.if91wallet.vo.UserVo

class TransactionRepository {

    private val transactionService: TransactionService by lazy {
        RetrofitFactory().getRetrofit().create(TransactionService::class.java)
    }
    private var _transaction = MutableLiveData<Transaction>()
    val transaction: LiveData<Transaction> = _transaction

    companion object {
        private const val TAG = "TransactionRepository"
    }

    fun pay(value: Double, card: CardVo, user: UserVo) {
        val request = TransactionRequest(value, card, user)

        transactionService.pay(request).enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { _transaction.postValue(it.transaction) }
            }
            throwable?.let {
                Log.e(TAG, it.message)
            }
        })
    }

}