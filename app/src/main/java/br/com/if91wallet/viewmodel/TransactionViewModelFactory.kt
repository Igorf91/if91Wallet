package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.if91wallet.repository.CardRepository
import br.com.if91wallet.repository.TransactionRepository
import br.com.if91wallet.service.TransactionService
import br.com.if91wallet.util.RetrofitFactory
import br.com.if91wallet.vo.UserVo

class TransactionViewModelFactory(private val user: UserVo) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val transactionService =
            RetrofitFactory().getRetrofit().create(TransactionService::class.java)

        return TransactionViewModel(
            TransactionRepository(transactionService),
            CardRepository(),
            user
        ) as T
    }
}