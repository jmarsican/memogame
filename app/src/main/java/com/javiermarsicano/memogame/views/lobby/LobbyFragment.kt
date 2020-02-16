package com.javiermarsicano.memogame.views.lobby

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javiermarsicano.memogame.R
import kotlinx.android.synthetic.main.fragment_lobby.*


class LobbyFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lobby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_level_0.setOnClickListener { onButtonPressed(it) }

        btn_level_1.setOnClickListener { onButtonPressed(it) }

        btn_level_2.setOnClickListener {  onButtonPressed(it) }

        btn_level_3.setOnClickListener { onButtonPressed(it) }
    }

    fun onButtonPressed(view: View) {
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
        fun newInstance() =
                LobbyFragment()
    }
}
