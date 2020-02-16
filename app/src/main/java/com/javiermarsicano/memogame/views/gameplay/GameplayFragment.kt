package com.javiermarsicano.memogame.views.gameplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.javiermarsicano.memogame.R
import com.javiermarsicano.memogame.common.mvp.BaseMVPFragment
import com.wajahatkarim3.easyflipview.EasyFlipView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GameplayFragment : BaseMVPFragment<GameplayView, GameplayPresenter>(), GameplayView {

    override fun getPresenter() = presenter

    private var height: Int = 0
    private var width: Int = 0
    private var cards: ArrayList<EasyFlipView>? = null

    private lateinit var presenter: GameplayPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            height = 4
            width = 4
        }
        cards = ArrayList(width*height)

        presenter = GameplayPresenterImpl(width, height) //TODO it should use dependency injection with Dagger
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gameplay, container, false) as ViewGroup

        cards?.let {
            for (i in 0 until height) {
                val row = inflater.inflate(R.layout.row, view, false) as ViewGroup

                for (j in 0 until width) {
                    val cardLayout = inflater.inflate(R.layout.memocard, null)

                    val card = presenter.getCard(it.size)
                    val cover = cardLayout.findViewById<ImageView>(R.id.card_cover)
                    val flipView = cardLayout.findViewById<EasyFlipView>(R.id.cardFlipView)
                    cardLayout.setOnClickListener {
                        flipView.flipTheView()
                        onClick(flipView)
                    }
                    cover.setImageResource(card.image)

                    it.add(flipView)
                    flipView.tag = card.id
                    row.addView(cardLayout)
                }
                view.addView(row)
            }
        }

        return view
    }

    override fun flipCard(position: Int) {
        cards?.get(position)?.flipTheView()
    }

    override fun setMatchingCard(position: Int) {
        cards?.get(position)?.isFlipEnabled = false
    }

    private fun onClick(view: View?) {
        presenter.onCardClicked(view?.tag as Int)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
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
