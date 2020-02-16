package com.javiermarsicano.memogame.common.mvp

interface MVPView {
    fun showLoading()
    fun hideLoading()
    fun onError(resId: Int)
    fun onError(message: String?)
}