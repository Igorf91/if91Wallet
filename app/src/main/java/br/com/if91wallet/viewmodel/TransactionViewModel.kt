package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import br.com.if91wallet.repository.TransactionRepository
import br.com.if91wallet.vo.CardVo
import br.com.if91wallet.vo.UserVo

class TransactionViewModel (private val repository: TransactionRepository) : ViewModel() {

    fun getTransaction() = repository.transaction
    fun pay(value: Double, card: CardVo, user: UserVo) = repository.pay(value, card, user)
}