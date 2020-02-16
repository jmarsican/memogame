package com.javiermarsicano.memogame.views.gameplay

import com.javiermarsicano.memogame.R
import com.javiermarsicano.memogame.common.mvp.BaseMVPPresenter
import com.javiermarsicano.memogame.domain.Card

class GameplayPresenterImpl(width: Int, height: Int): BaseMVPPresenter<GameplayView>(), GameplayPresenter {

    private var cards = ArrayList<Card>()

    init {
        val pairsCount = width*height / 2
        for (i in 0 until pairsCount) {
            val image = if (i % 2 == 1) R.mipmap.memory_dragon_card_front else R.mipmap.memory_cow_front
            cards.add(Card(cards.size, image))
            cards.add(Card(cards.size, image))
        }
    }

    override fun getCard(pos: Int): Card {
        return cards[pos]
    }

    override fun onCardClicked(pos: Int) {

    }
}