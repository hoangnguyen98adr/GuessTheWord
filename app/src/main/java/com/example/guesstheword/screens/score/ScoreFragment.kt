package com.example.guesstheword.screens.score

import android.R.attr.defaultValue
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guesstheword.R
import kotlinx.android.synthetic.main.fragment_game.*


class ScoreFragment : Fragment() {
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            score = bundle.getInt("score", 5)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        score_text.text = score.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScoreFragment()
    }
}