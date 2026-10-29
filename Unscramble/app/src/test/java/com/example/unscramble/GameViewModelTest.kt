package com.example.unscramble

import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    // 1. Успешный путь (Правильное слово)
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdated() {
        val currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        val updatedUiState = viewModel.uiState.value
        assertFalse(updatedUiState.isGuessedWordWrong)
        assertEquals(SCORE_INCREASE, updatedUiState.score)
    }

    // 2. Путь ошибки (Неправильное слово)
    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        viewModel.updateUserGuess("wrong_word")
        viewModel.checkUserGuess()
        val updatedUiState = viewModel.uiState.value
        assertTrue(updatedUiState.isGuessedWordWrong)
        assertEquals(0, updatedUiState.score)
    }

    // 3. Граничный случай (Инициализация)
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val initialUiState = viewModel.uiState.value
        assertFalse(initialUiState.isGameOver)
        assertEquals(1, initialUiState.currentWordCount)
        assertEquals(0, initialUiState.score)
    }

    // 4. Пропуск слова
    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        viewModel.skipWord()
        val updatedUiState = viewModel.uiState.value
        assertEquals(0, updatedUiState.score)
        assertEquals(2, updatedUiState.currentWordCount)
    }

    // 5. Конец игры (Угаданы все 10 слов) - для 100% покрытия
    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()

            currentGameUiState = viewModel.uiState.value
            if (!currentGameUiState.isGameOver) {
                correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            }
        }

        assertTrue(currentGameUiState.isGameOver)
        assertEquals(expectedScore, currentGameUiState.score)
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
    }
    @Test
    fun gameViewModel_ResetGame_StateCleared() {
        viewModel.resetGame()
        val resetState = viewModel.uiState.value
        assertEquals(1, resetState.currentWordCount)
        assertEquals(0, resetState.score)
        assertFalse(resetState.isGameOver)
    }
}