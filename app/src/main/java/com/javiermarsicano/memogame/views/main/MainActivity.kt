package com.javiermarsicano.memogame.views.main

import android.os.Bundle
import com.javiermarsicano.memogame.R
import com.javiermarsicano.memogame.common.mvp.BaseMVPActivity
import com.javiermarsicano.memogame.views.gameplay.GameplayFragment
import com.javiermarsicano.memogame.views.lobby.LobbyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<MainView, MainPresenter>(), MainView, LobbyFragment.OnFragmentInteractionListener {

    override fun getPresenter(): MainPresenter = MainPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = LobbyFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, LobbyFragment.TAG)
                    .commit()
        }

        setLoaderView(loaderView)

    }

    override fun onFragmentInteraction(height: Int, width: Int) {
        val fragment = GameplayFragment.newInstance(height, width)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, GameplayFragment.TAG)
                .commit()
    }


}
