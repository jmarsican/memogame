package com.javiermarsicano.memogame.views.gameplay


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javiermarsicano.memogame.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GameplayFragment : Fragment() {
    private var height: Int = 0
    private var width: Int = 0
    private var ids: ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            height = 4
            width = 4
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gameplay, container, false) as ViewGroup


        ids = ArrayList(width*height)

        val matrix = Array(width) {IntArray(height)}
        for (i in 0 until height) {
            val row = inflater.inflate(R.layout.row, view, false) as ViewGroup
            for (j in 0 until width) {
                val card = inflater.inflate(R.layout.memocard, null)
                val id = View.generateViewId()
                card.id = id
                matrix[j][i] = id
                ids?.add(id)
                row.addView(card)
            }
            view.addView(row)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    companion object {
        /**
         *
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
