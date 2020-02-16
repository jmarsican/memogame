package com.javiermarsicano.memogame.views.gameplay

import com.javiermarsicano.memogame.common.mvp.MVPView

interface GameplayView: MVPView {
    fun flipCard(position: Int)
    fun setMatchingCard(position: Int)
}