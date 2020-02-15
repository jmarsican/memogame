package com.javiermarsicano.memogame.views.main

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.javiermarsicano.memogame.R
import com.javiermarsicano.memogame.common.mvp.BaseMVPActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<MainView, MainPresenter>(), MainView, LobbyFragment.OnFragmentInteractionListener {

    override fun getPresenter(): MainPresenter = MainPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = LobbyFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, LobbyFragment.TAG).commit()
        }

        setLoaderView(loaderView)

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
