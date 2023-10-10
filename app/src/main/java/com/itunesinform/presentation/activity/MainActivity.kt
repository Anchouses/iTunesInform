package com.itunesinform.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import com.itunesinform.presentation.navigation.Navigation
import com.itunesinform.presentation.theme.GreyBackground
import com.itunesinform.presentation.theme.ITunesInformTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITunesInformTheme(
                darkTheme = true,
                dynamicColor = true
            ) {
                Column(modifier = Modifier
                    .background(color = GreyBackground)) {
                    Navigation()
                }
            }
        }
    }
}


