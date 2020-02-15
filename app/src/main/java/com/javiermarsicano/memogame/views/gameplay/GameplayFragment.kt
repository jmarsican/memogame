package com.javiermarsicano.memogame.views.gameplay


import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javiermarsicano.memogame.R
import kotlinx.android.synthetic.main.card_front.*
import kotlinx.android.synthetic.main.fragment_gameplay.*
import kotlinx.android.synthetic.main.fragment_gameplay.card_cover

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GameplayFragment : Fragment() {
    private var height: String? = null
    private var width: String? = null

    private var mSetRightOut: AnimatorSet? = null
    private var mSetLeftIn: AnimatorSet? = null
    private var mIsBackVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            height = it.getString(ARG_PARAM1)
            width = it.getString(ARG_PARAM2)
        }

//        mSetRightOut = AnimatorInflater.loadAnimator(context, R.animator.out_animation) as AnimatorSet
//        mSetLeftIn = AnimatorInflater.loadAnimator(context, R.animator.in_animation) as AnimatorSet
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gameplay, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_view.setOnClickListener {

        }
    }

    fun flipCard(view: View) {
        if (!mIsBackVisible) {
            mSetRightOut?.setTarget(card_cover)
            mSetLeftIn?.setTarget(card_front)
            mSetRightOut?.start()
            mSetLeftIn?.start()
            mIsBackVisible = true
        } else {
            mSetRightOut?.setTarget(card_front)
            mSetLeftIn?.setTarget(card_cover)
            mSetRightOut?.start()
            mSetLeftIn?.start()
            mIsBackVisible = false
        }
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
