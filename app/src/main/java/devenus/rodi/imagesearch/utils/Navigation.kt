package devenus.rodi.imagesearch.utils

import androidx.navigation.navOptions
import devenus.rodi.imagesearch.R

val options = navOptions {
    anim {
        enter = R.anim.nav_default_enter_anim
        exit = R.anim.nav_default_exit_anim
        popEnter = R.anim.nav_default_pop_enter_anim
        popExit = R.anim.nav_default_pop_exit_anim
    }
}
