package com.example.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.screens.game.GameViewModel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private var string = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("message", "word")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
            string = savedInstanceState.getString("message").toString()
            Toast.makeText(activity, string, Toast.LENGTH_LONG).show();
        }
        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skip_button.setOnClickListener {
            onSkip()
        }
        correct_button.setOnClickListener {
            onCorrect()
        }
        end_game_button.setOnClickListener {
            onEndGame()
        }
        updateWordText()
        updateScoreText()
    }

    private fun onSkip() {
        viewModel.onSkip()
        updateWordText()
        updateScoreText()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
        updateScoreText()
        updateWordText()
    }

    private fun updateWordText() {
        word_text.text = viewModel.word
    }

    private fun updateScoreText() {
        score_text.text = viewModel.score.toString()
    }

    private fun onEndGame() {
        gameFinished()
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("score" to viewModel.score)
        this.findNavController()
            .navigate(R.id.action_gameFragment_to_scoreFragment, bundle)
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameFragment()
    }
}