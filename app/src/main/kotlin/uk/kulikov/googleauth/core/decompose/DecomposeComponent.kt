package uk.kulikov.googleauth.core.decompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface DecomposeComponent {
    @Composable
    fun Render(modifier: Modifier)
}
