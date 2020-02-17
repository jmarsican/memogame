package com.javiermarsicano.memogame.views.gameplay

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.javiermarsicano.memogame.R
import com.javiermarsicano.memogame.common.mvp.BaseMVPFragment
import com.wajahatkarim3.easyflipview.EasyFlipView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GameplayFragment : BaseMVPFragment<GameplayView, GameplayPresenter>(), GameplayView {

    private var height: Int = 0
    private var width: Int = 0
    private var cards: ArrayList<EasyFlipView>? = null

    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var presenter: GameplayPresenter

    override fun getPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            height = it.getInt(ARG_PARAM1)
            width = it.getInt(ARG_PARAM2)
        }
        cards = ArrayList(width*height)

        presenter = GameplayPresenterImpl(width, height) //TODO it should use dependency injection with Dagger
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gameplay, container, false) as ViewGroup

        setupToolbar(view)

        castCards(inflater, view)

        mediaPlayer = MediaPlayer.create(this.activity!!.applicationContext, R.raw.splashscreenloop  ).apply {
            setVolume(0.5f, 0.5f)
            start()
        }

        return view
    }

    override fun onStop() {
        mediaPlayer.stop()
        super.onStop()
    }

    private fun setupToolbar(view: ViewGroup) {
        val toolbar = view.findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.mipmap.back_nav_button)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    private fun castCards(inflater: LayoutInflater, view: ViewGroup) {
        cards?.let {

            for (i in 0 until height) {
                val row = inflater.inflate(R.layout.row, view, false) as ViewGroup

                for (j in 0 until width) {
                    val cardLayout = inflater.inflate(R.layout.memocard, null)

                    val card = presenter.getCard(it.size) //get next card

                    val cover = cardLayout.findViewById<ImageView>(R.id.card_cover)
                    cover.setImageResource(card.image)

                    val flipView = cardLayout.findViewById<EasyFlipView>(R.id.cardFlipView)
                    cardLayout.setOnClickListener {
                        flipView.flipTheView()
                        onClick(flipView)
                    }

                    it.add(flipView)
                    flipView.tag = card.id
                    row.addView(cardLayout)
                }
                view.addView(row)
            }
        }
    }

    private fun onClick(view: View?) {
        presenter.onCardClicked(view?.tag as Int)
    }

    override fun flipCard(position: Int) {
        cards?.get(position)?.flipTheView()
    }

    override fun setMatchingCard(position: Int) {
        cards?.get(position)?.isFlipEnabled = false
    }

    companion object {
        const val TAG = "GAMEPLAY"

        /**
         * @param height number of cards per row.
         * @param width number of cards per column.
         * @return A new instance of GameplayFragment.
         */
        @JvmStatic
        fun newInstance(height: Int, width: Int) =
                GameplayFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, height)
                        putInt(ARG_PARAM2, width)
                    }
                }
    }
}
