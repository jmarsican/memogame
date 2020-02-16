package com.javiermarsicano.memogame.views.gameplay

import com.javiermarsicano.memogame.common.mvp.MVPPresenter
import com.javiermarsicano.memogame.domain.Card

interface GameplayPresenter: MVPPresenter<GameplayView> {
    fun onCardClicked(pos: Int)
    fun getCard(pos: Int): Card
}