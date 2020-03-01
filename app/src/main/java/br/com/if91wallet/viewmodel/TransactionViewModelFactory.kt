package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.if91wallet.repository.TransactionRepository
import br.com.if91wallet.vo.UserVo

class TransactionViewModelFactory (
    private val repository: TransactionRepository,
    private val user: UserVo
)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(repository, user) as T
    }
}