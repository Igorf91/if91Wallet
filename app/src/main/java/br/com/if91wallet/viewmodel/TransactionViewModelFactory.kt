package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.if91wallet.repository.TransactionRepository

class TransactionViewModelFactory (private val repository: TransactionRepository)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(repository) as T
    }
}