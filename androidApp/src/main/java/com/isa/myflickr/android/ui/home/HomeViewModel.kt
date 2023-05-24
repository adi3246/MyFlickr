package com.isa.myflickr.android.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isa.myflickr.android.common.SearchWidgetState
import com.isa.myflickr.domain.model.Photo
import com.isa.myflickr.domain.use_case.GetPhotosUseCase
import kotlinx.coroutines.launch

/**
 * Created by Isa Andi on 19/05/2023.
 */
class HomeViewModel(
    val getPhotosUseCase: GetPhotosUseCase
): ViewModel(){
    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun beginSearch() {
        val photos: List<Photo> = listOf()
        currentPage = 1
        uiState = uiState.copy(
            photos = photos
        )
        loadPhotos(forceReload = false, _searchTextState.value)
    }

    init {
        uiState = uiState.copy(
            refreshing = true
        )
        loadPhotos(forceReload = false, "Electrolux")
    }


    fun loadPhotos(forceReload: Boolean = false, name: String){
        var finalName = name
        if (uiState.loading) return
        if (forceReload || name == "") {
            currentPage = 1
            _searchTextState.value = "Electrolux"
            finalName = _searchTextState.value
        }
        if (currentPage == 0) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true
            )

            if (currentPage <= 3) {
                try {
                    val resultPhotos = getPhotosUseCase(page = currentPage, name = finalName)
                    val photos = if (currentPage == 1) resultPhotos else uiState.photos + resultPhotos

                    currentPage += 1
                    uiState = uiState.copy(
                        loading = false,
                        refreshing = false,
                        loadFinished = resultPhotos.isEmpty(),
                        photos = photos.distinct()
                    )

                }catch (error: Throwable){
                    uiState = uiState.copy(
                        loading = false,
                        refreshing = false,
                        loadFinished = true,
                        errorMessage = "Could not load movies: ${error.localizedMessage}"
                    )
                }
            } else {
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true
                )
            }
        }
    }
}