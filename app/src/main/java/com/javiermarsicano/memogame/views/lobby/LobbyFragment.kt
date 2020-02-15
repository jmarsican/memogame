package com.javiermarsicano.memogame.views.lobby

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.javiermarsicano.memogame.R
import kotlinx.android.synthetic.main.fragment_lobby.*


class LobbyFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lobby, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_level_0.setOnClickListener {
            Toast.makeText(context, "piece of cake", Toast.LENGTH_SHORT).show()
        }

        btn_level_1.setOnClickListener {
            Toast.makeText(context, "easy", Toast.LENGTH_SHORT).show()
        }

        btn_level_2.setOnClickListener {
            Toast.makeText(context, "medium", Toast.LENGTH_SHORT).show()
        }

        btn_level_3.setOnClickListener {
            Toast.makeText(context, "hard", Toast.LENGTH_SHORT).show()
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity.
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        const val TAG = "LOBBY"
        @JvmStatic
        fun newInstance() =
                LobbyFragment()
    }
}
