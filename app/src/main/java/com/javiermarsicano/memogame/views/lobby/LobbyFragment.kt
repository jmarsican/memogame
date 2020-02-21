package com.javiermarsicano.memogame.views.lobby

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.javiermarsicano.memogame.R
import kotlinx.android.synthetic.main.fragment_lobby.*

private const val ONE_SECOND_IN_MILLIS = 1000L
private const val STARTING_ANIMATIONS_TIME = ONE_SECOND_IN_MILLIS

class LobbyFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mediaPlayer = MediaPlayer.create(this.activity!!.applicationContext, R.raw.piglevelwin2).apply {
            setVolume(0.5f, 0.5f)
            start()
        }
        return inflater.inflate(R.layout.fragment_lobby, container, false)
    }

    override fun onStop() {
        mediaPlayer.stop()
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        YoYo.with(Techniques.ZoomInDown)
                .duration(STARTING_ANIMATIONS_TIME + ONE_SECOND_IN_MILLIS / 2)
                .playOn(lobby_hint)

        YoYo.with(Techniques.Landing)
                .duration(STARTING_ANIMATIONS_TIME)
                .playOn(btn_level_0)
        YoYo.with(Techniques.Landing)
                .duration(STARTING_ANIMATIONS_TIME + ONE_SECOND_IN_MILLIS)
                .playOn(btn_level_1)
        YoYo.with(Techniques.Landing)
                .duration(STARTING_ANIMATIONS_TIME + ONE_SECOND_IN_MILLIS * 2)
                .playOn(btn_level_2)
        YoYo.with(Techniques.Landing)
                .duration(STARTING_ANIMATIONS_TIME + ONE_SECOND_IN_MILLIS * 3)
                .playOn(btn_level_3)

        btn_level_0.setOnClickListener { onButtonPressed(it) }

        btn_level_1.setOnClickListener { onButtonPressed(it) }

        btn_level_2.setOnClickListener {  onButtonPressed(it) }

        btn_level_3.setOnClickListener { onButtonPressed(it) }
    }

    private fun onButtonPressed(view: View) {
        var height = 0
        var width = 0
        when (view) {
            btn_level_0 -> {
                height = 5
                width = 2
            }
            btn_level_1 -> {
                height = 3
                width = 4
            }
            btn_level_2 -> {
                height = 4
                width = 4
            }
            btn_level_3 -> {
                height = 4
                width = 5
            }
        }
        listener?.onFragmentInteraction(height, width)
        mediaPlayer.stop()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(height: Int, width: Int)
    }

    companion object {
        const val TAG = "LOBBY"
        @JvmStatic
        fun newInstance() = LobbyFragment()
    }
}
