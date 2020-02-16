package com.javiermarsicano.memogame.views.gameplay

import android.os.Handler
import com.javiermarsicano.memogame.common.mvp.BaseMVPPresenter
import com.javiermarsicano.memogame.domain.Card
import com.javiermarsicano.memogame.domain.Model

class GameplayPresenterImpl(width: Int, height: Int): BaseMVPPresenter<GameplayView>(), GameplayPresenter {

    private var cards = ArrayList<Card>()
    private var lastCardClicked: Card? = null
    private var firstClick: Boolean = true

    init {
        val coverImages = Model.values()

        val pairsCount = width*height / 2
        for (i in 0 until pairsCount) {
            cards.add(Card(cards.size, coverImages[i].id))
            cards.add(Card(cards.size, coverImages[i].id))
        }
    }

    override fun getCard(pos: Int): Card {
        return cards[pos]
    }

    override fun onCardClicked(id: Int) {
        if (!firstClick) {
            lastCardClicked?.let {
                if (lastCardClicked != cards[id]) {
                    val lastCardClickedId = lastCardClicked!!.id
                    Handler().postDelayed({
                        viewReference.get()?.flipCard(lastCardClickedId)
                        viewReference.get()?.flipCard(id)
                    },1000)
                } else {
                    viewReference.get()?.setMatchingCard(lastCardClicked!!.id)
                    viewReference.get()?.setMatchingCard(id)
                }
            }
        }
        lastCardClicked = cards[id]
        firstClick = !firstClick
    }
}