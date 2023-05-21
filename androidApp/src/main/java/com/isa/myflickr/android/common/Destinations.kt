package com.isa.myflickr.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.isa.myflickr.android.data.ProfileArgType

/**
 * Created by Isa Andi on 19/05/2023.
 */
interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Home: Destination{
    override val title: String
        get() = "Photos"

    override val route: String
        get() = "home"

    override val routeWithArgs: String
        get() = route
}

object Detail: Destination{
    override val title: String
        get() = "Photo details"

    override val route: String
        get() = "detail"

    override val routeWithArgs: String
        get() = "$route/{post}"

    val arguments = listOf(
        navArgument(name = "post"){type = ProfileArgType() }
    )
}

val appDestinations = listOf(Home, Detail)