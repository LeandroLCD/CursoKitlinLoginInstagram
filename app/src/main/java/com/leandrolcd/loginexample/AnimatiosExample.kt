package com.leandrolcd.loginexample

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ColorAmimations() {
    var isColor by rememberSaveable {
        mutableStateOf(false)
    }
    var isVisible by rememberSaveable {
        mutableStateOf(true)
    }
    val aniColor by animateColorAsState(
        targetValue = if (isColor) Color.Red else Color.Magenta,
        animationSpec = tween(2000),
        finishedListener = {isVisible = false}

    )
    if(isVisible){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(aniColor)
            .clickable { isColor = !isColor })

    }
}