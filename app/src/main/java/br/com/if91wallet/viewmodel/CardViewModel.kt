package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import br.com.if91wallet.repository.CardRepository
import br.com.if91wallet.vo.CardVo

class CardViewModel (private val cardRepository: CardRepository) : ViewModel() {

    fun save(card : CardVo) = cardRepository.save(card)
}