package id.ark.rizzr.ui

import id.ark.rizzr.core.utils.HomeMenuItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dev.icerock.moko.resources.compose.fontFamilyResource
import id.ark.rizzr.MR
import id.ark.rizzr.core.theme.colorScheme
import id.ark.rizzr.core.utils.HistoryMenuItem
import id.ark.rizzr.core.utils.MenuNavigationItem
import id.ark.rizzr.core.utils.SettingsMenuItem
import org.jetbrains.compose.ui.tooling.preview.Preview

val LocalBottomBarVisibility = compositionLocalOf<(Boolean) -> Unit> { {} }
val LocalAppFont = compositionLocalOf { FontFamily() }

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

    var showBottomBar by remember { mutableStateOf(true) }

    CompositionLocalProvider(LocalAppFont provides fontFamilyResource(MR.fonts.splinesans_regular)) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = appTypography,
        ) {
            TabNavigator(HomeMenuItem) {
                CompositionLocalProvider(LocalBottomBarVisibility provides { showBottomBar = it }) {
                    Scaffold(
                        bottomBar = {
                            if (showBottomBar) {
                                NavigationBar {
                                    MenuNavigationItem(HomeMenuItem)
                                    MenuNavigationItem(HistoryMenuItem)
                                    MenuNavigationItem(SettingsMenuItem)
                                }
                            }
                        }
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            CurrentTab()
                        }
                    }
                }
            }
        }
    }

}
