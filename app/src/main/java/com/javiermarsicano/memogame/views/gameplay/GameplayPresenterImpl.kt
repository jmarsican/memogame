package com.javiermarsicano.memogame.views.gameplay

import com.javiermarsicano.memogame.common.mvp.BaseMVPPresenter
import com.javiermarsicano.memogame.domain.Card
import com.javiermarsicano.memogame.domain.Model
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GameplayPresenterImpl(width: Int, height: Int): BaseMVPPresenter<GameplayView>(), GameplayPresenter {

    private var cards = ArrayList<Card>()
    private var lastCardClicked: Card? = null
    private var firstClick: Boolean = true
    val timer = Observable.timer(1, TimeUnit.SECONDS, Schedulers.io())

    init {
        val total = width*height
        val coverImages = mutableListOf<Int>()
        val images = Model.values()
        for (i in 0 until total/2) {
            coverImages.add(images[i].id)
            coverImages.add(images[i].id)
        }
        coverImages.shuffle()

        for (i in 0 until total) {
            cards.add(Card(cards.size, coverImages[i]))
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
                    timer.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                viewReference.get()?.flipCard(lastCardClickedId)
                                viewReference.get()?.flipCard(id)
                            }
                            .bindToLifecycle()
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