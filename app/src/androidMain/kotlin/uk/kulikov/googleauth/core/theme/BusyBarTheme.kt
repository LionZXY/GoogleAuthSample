package uk.kulikov.googleauth.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Colors
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.animation.LocalStackAnimationProvider
import uk.kulikov.googleauth.core.theme.generated.BusyBarPallet
import uk.kulikov.googleauth.core.theme.generated.getDarkPallet
import uk.kulikov.googleauth.core.theme.generated.getLightPallet
import uk.kulikov.googleauth.core.theme.generated.toAnimatePallet

val LocalPallet = compositionLocalOf<BusyBarPallet> { error("No local pallet") }

@Composable
private fun getThemedPallet(isLight: Boolean): BusyBarPallet {
    return remember(isLight) {
        if (isLight) {
            getLightPallet()
        } else {
            getDarkPallet()
        }
    }.toAnimatePallet()
}

internal fun BusyBarPallet.toMaterialColors(isLight: Boolean) = Colors(
    primary = brand.primary,
    primaryVariant = brand.primary,
    secondary = brand.secondary,
    secondaryVariant = brand.secondary,
    background = background.primary,
    surface = background.secondary,
    error = danger.primary,
    onPrimary = onColor.white,
    onSecondary = onColor.black,
    onBackground = onColor.black,
    onSurface = onColor.black,
    onError = onColor.black,
    isLight = isLight
)

internal fun BusyBarPallet.toTextSelectionColors() = TextSelectionColors(
    handleColor = brand.primary,
    backgroundColor = background.primary
)

@Composable
fun BusyBarTheme(
    darkMode: Boolean,
    content: @Composable () -> Unit
) {
    val isLight = if (darkMode) {
        false
    } else isSystemInDarkTheme().not()
    BusyBarThemeInternal(
        isLight = isLight,
        content = content
    )
}


@Composable
fun BusyBarThemeInternal(
    isLight: Boolean = true,
    content: @Composable () -> Unit,
) {
    val pallet = getThemedPallet(
        isLight = isLight
    )
    val colors = pallet.toMaterialColors(isLight)
    val shapes = Shapes(medium = RoundedCornerShape(size = 10.dp))
    val fonts = getBusyBarFonts()

    MaterialTheme(
        shapes = shapes,
        colors = colors
    ) {
        CompositionLocalProvider(
            LocalPallet provides pallet,
            LocalBusyBarFonts provides fonts,
            LocalContentColor provides colors.contentColorFor(backgroundColor = colors.background),
            LocalTextSelectionColors provides pallet.toTextSelectionColors(),
            LocalStackAnimationProvider provides BusyBarAnimationProvider,
            content = content
        )
    }
}
