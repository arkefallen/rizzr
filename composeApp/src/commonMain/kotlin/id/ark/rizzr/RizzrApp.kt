package id.ark.rizzr

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import id.ark.rizzr.core.theme.colorScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun RizzrApp() {

    val splineSansRegular = fontFamilyResource(MR.fonts.splinesans_regular)
    val splineSansMedium = fontFamilyResource(MR.fonts.splinesans_medium)

    val appTypography = Typography(
        // Display Styles
        displayLarge = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp,
        ),
        displayMedium = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),

        // Headline Styles
        headlineLarge = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),

        // Title Styles
        titleLarge = TextStyle(
            fontFamily = splineSansMedium,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = splineSansMedium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = splineSansMedium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),

        // Body Styles
        bodyLarge = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = splineSansRegular,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),

        // Label Styles
        labelLarge = TextStyle(
            fontFamily = splineSansMedium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = splineSansMedium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = splineSansMedium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography,
    ) {
        Surface {
            HomeScreen(splineSansMedium)
        }
    }
}
