package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import br.com.if91wallet.repository.CardRepository
import br.com.if91wallet.repository.TransactionRepository
import br.com.if91wallet.vo.UserVo

class TransactionViewModel(
    private val transactionRepository: TransactionRepository,
    cardRepository: CardRepository,
    val user: UserVo
) : ViewModel() {

    val card = cardRepository.getCard()

    fun getCardCvv() = card.getLast4()

    fun getTransaction() = transactionRepository.transaction

    fun pay(value: Float) = transactionRepository.pay(value, card, user)
}