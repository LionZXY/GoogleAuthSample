package uk.kulikov.googleauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import uk.kulikov.googleauth.core.theme.BusyBarTheme
import uk.kulikov.googleauth.root.RootDecomposeComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Always create the root component outside Compose on the main thread
        val root = RootDecomposeComponent(
            componentContext = defaultComponentContext(),
            context = this
        )

        setContent {
            BusyBarTheme(darkMode = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    root.Render(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}