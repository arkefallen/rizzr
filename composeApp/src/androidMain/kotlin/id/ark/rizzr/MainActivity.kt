package id.ark.rizzr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import id.ark.rizzr.core.di.appModule
import id.ark.rizzr.core.di.uiModule
import id.ark.rizzr.ui.RizzrApp
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        startKoin {
            modules(appModule())
        }
        setContent {
            enableEdgeToEdge()
            RizzrApp()
        }
    }
}

@Preview
@Composable
private fun AppAndroidPreview() {
    RizzrApp()
}