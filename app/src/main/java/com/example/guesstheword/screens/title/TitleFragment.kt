package com.example.guesstheword.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import kotlinx.android.synthetic.main.fragment_title.*

class TitleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        play_game_button.setOnClickListener {
            this.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TitleFragment()
    }
}