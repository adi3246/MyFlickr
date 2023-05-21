package com.isa.myflickr.android.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isa.myflickr.android.data.Post
import kotlinx.coroutines.launch

/**
 * Created by Isa Andi on 19/05/2023.
 */
class DetailViewModel(
    post: Post
): ViewModel(){
    var uiState by mutableStateOf(DetailScreenState())

    init {
        loadMovie(post)
    }

    private fun loadMovie(post: Post){
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                uiState.copy(loading = false, post = post)
            }catch (error: Throwable){
                uiState.copy(
                    loading = false,
                    errorMessage = "Could not load the movie"
                )
            }
        }
    }
}