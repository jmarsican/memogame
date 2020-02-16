package com.javiermarsicano.memogame.common.mvp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseMVPFragment<in V : MVPView, P : MVPPresenter<V>> : Fragment(), MVPView {

    private var activity: BaseMVPActivity<V, P>? = null

    protected abstract fun getPresenter(): P

    private var fragmentResultSubscription: Disposable? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPresenter().onBindView(this as V)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseMVPActivity<*, *>) {
            activity = context as BaseMVPActivity<V, P>?
        }
    }

    override fun onDestroy() {
        removeFragmentResultSubscription()

        getPresenter().onDestroy()
        super.onDestroy()
    }

    private fun removeFragmentResultSubscription() {
        if (fragmentResultSubscription?.isDisposed == false) {
            fragmentResultSubscription?.dispose()
        }
    }

    override fun showLoading() {
        if (activity != null) activity!!.showLoading()
    }

    override fun hideLoading() {
        if (activity != null) activity!!.hideLoading()
    }

    override fun onError(resId: Int) {
        if (activity != null) activity!!.onError(resId)
    }

    override fun onError(message: String?) {
        if (activity != null) activity!!.onError(message)
    }

    /**
     * Adds this [Disposable] to [compositeDisposable] to insure that it will be disposed when
     * the view is destroyed avoiding any memory leaks.
     */
    protected fun Disposable.bindToLifecycle() = apply { compositeDisposable.add(this) }


}
