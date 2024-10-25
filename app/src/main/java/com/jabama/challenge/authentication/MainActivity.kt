package com.jabama.challenge.authentication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jabama.challenge.authentication.navigation.JabamaAppNavigation
import com.jabama.designsystem.theme.JabamaTheme
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthenticationViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            authViewModel.onEvent(AuthenticationEvent.CheckAuthStatus)

            val state by authViewModel.state.collectAsStateWithLifecycle()

            JabamaTheme {
                JabamaAppNavigation(isAuthenticated = state.isAuthenticated)
            }
        }
    }
}


