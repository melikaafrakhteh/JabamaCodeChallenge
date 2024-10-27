package com.jabama.challenge.authentication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jabama.challenge.authentication.navigation.JabamaAppNavigation
import com.jabama.common.AuthCoordinator
import com.jabama.designsystem.theme.JabamaTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthenticationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            authViewModel.onEvent(AuthenticationEvent.CheckAuthStatus)

            val state by authViewModel.state.collectAsStateWithLifecycle()

            JabamaTheme {
                JabamaAppNavigation(
                    isAuthenticated = state.isAuthenticated,
                    onNavigateToAuthUrl = ::launchAuthorizationUrl
                )
            }
        }
    }

    private fun launchAuthorizationUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun handleRedirectUri(uri: Uri?) {
        uri?.getQueryParameter(CODE_KEY)?.let { code ->
            lifecycleScope.launch {
                AuthCoordinator.emitAuthCode(code)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleRedirectUri(intent.data)
    }

    companion object {
        private const val CODE_KEY = "code"
    }
}


