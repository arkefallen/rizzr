package id.ark.rizzr

import androidx.compose.runtime.Composable

interface Platform {
    val name: String

    @Composable
    fun getContext() : Any
}

expect fun getPlatform(): Platform