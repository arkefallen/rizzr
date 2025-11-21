package id.ark.rizzr

import androidx.compose.runtime.Composable
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    @Composable
    override fun getContext(): Any {
        return UIApplication.sharedApplication
    }

}

actual fun getPlatform(): Platform = IOSPlatform()