package com.isa.myflickr.android

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import com.isa.myflickr.android.common.AppBar
import com.isa.myflickr.android.common.Detail
import com.isa.myflickr.android.common.Home
import com.isa.myflickr.android.common.SearchWidgetState
import com.isa.myflickr.android.common.appDestinations
import com.isa.myflickr.android.data.Post
import com.isa.myflickr.android.ui.detail.DetailScreen
import com.isa.myflickr.android.ui.detail.DetailViewModel
import com.isa.myflickr.android.ui.home.HomeScreen
import com.isa.myflickr.android.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Created by Isa Andi on 19/05/2023.
 */
@Preview
@Composable
fun PhotoApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark){
        MaterialTheme.colors.primaryVariant
    }else {
        Color.Transparent
    }
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = appDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    }?: Home
    val homeViewModel: HomeViewModel = koinViewModel()
    val searchWidgetState by homeViewModel.searchWidgetState
    val searchTextState by homeViewModel.searchTextState

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            /*AppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen,
            ) {
                navController.navigateUp()
            }*/
            AppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen,
                onNavigateBack = {
                    navController.navigateUp()
                },
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    homeViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    homeViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("Searched Text", it)
                    homeViewModel.beginSearch()
                },
                onSearchTriggered = {
                    homeViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
    ) {innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs
        ){
            composable(Home.routeWithArgs){
                HomeScreen(
                    uiState = homeViewModel.uiState,
                    loadNextPhotos = {
                        homeViewModel.loadMovies(forceReload = it, homeViewModel.searchTextState.value)
                    },
                    navigateToDetail = {
                        val post = Post(it.title, it.urlM).toString()
                        navController.navigate(
                            "${Detail.route}/$post"
                        )
                    }
                )
            }

            composable(Detail.routeWithArgs, arguments = Detail.arguments){ it ->
                homeViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                // val photoUrl = it.arguments?.getString("photoUrl") ?: ""
                val post = it.arguments?.getString("post")?.let { Gson().fromJson(it, Post::class.java) }
                val detailViewModel: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(post) }
                )

                DetailScreen(uiState = detailViewModel.uiState)
            }
        }

    }
}