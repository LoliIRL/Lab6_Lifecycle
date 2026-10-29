package com.example.artspaceapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ArtUiState(
    val currentStep: Int = 1,
    val imageRes: Int,
    val title: String,
    val artist: String
)

class ArtViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ArtUiState(1, R.drawable.image1, "Artwork 1", "Artist 1 (2023)"))
    val uiState: StateFlow<ArtUiState> = _uiState.asStateFlow()

    fun next() {
        _uiState.update {
            when(it.currentStep) {
                1 -> ArtUiState(2, R.drawable.image2, "Artwork 2", "Artist 2 (2024)")
                2 -> ArtUiState(3, R.drawable.image3, "Artwork 3", "Artist 3 (2025)")
                else -> it
            }
        }
    }

    fun previous() {
        _uiState.update {
            when(it.currentStep) {
                2 -> ArtUiState(1, R.drawable.image1, "Artwork 1", "Artist 1 (2023)")
                3 -> ArtUiState(2, R.drawable.image2, "Artwork 2", "Artist 2 (2024)")
                else -> it
            }
        }
    }
}