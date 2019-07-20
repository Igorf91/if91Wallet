package br.com.if91wallet.repository

import br.com.if91wallet.vo.CardVo
import com.orhanobut.hawk.Hawk

class CardRepository {

    companion object {
        const val KEY = "myCard"
    }

    fun save(card : CardVo) = Hawk.put(KEY, card)

    fun getCard() : CardVo = Hawk.get(KEY)

    fun hasCard() = Hawk.contains(KEY)
}