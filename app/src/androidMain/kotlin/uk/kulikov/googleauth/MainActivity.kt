package uk.kulikov.googleauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import uk.kulikov.googleauth.root.RootDecomposeComponent
import uk.kulikov.googleauth.ui.theme.GoogleAuthSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Always create the root component outside Compose on the main thread
        val root = RootDecomposeComponent(
            componentContext = defaultComponentContext(),
        )

        setContent {
            GoogleAuthSampleTheme {
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

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        GoogleAuthSampleTheme {
            Greeting("Android")
        }
    }
}