package br.com.if91wallet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.if91wallet.service.TransactionService
import br.com.if91wallet.util.callback
import br.com.if91wallet.vo.CardVo
import br.com.if91wallet.vo.TransactionRequest
import br.com.if91wallet.vo.TransactionVo
import br.com.if91wallet.vo.UserVo

class TransactionRepository(private val transactionService: TransactionService) {

    private var _transaction = MutableLiveData<TransactionVo>()
    val transaction: LiveData<TransactionVo> = _transaction

    companion object {
        private const val TAG = "TransactionRepository"
    }

    fun pay(value: Float, card: CardVo, user: UserVo) {
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