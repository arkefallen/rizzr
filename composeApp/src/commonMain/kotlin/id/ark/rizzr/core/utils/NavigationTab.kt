package id.ark.rizzr.core.utils
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import id.ark.rizzr.ui.LocalBottomBarVisibility
import id.ark.rizzr.ui.screen.HistoryScreen
import id.ark.rizzr.ui.screen.HomeScreen
import id.ark.rizzr.ui.screen.SettingsScreen

@Composable
fun Tab.TabContent(screen: Screen) {
    val bottomBarVisibility = LocalBottomBarVisibility.current
    Navigator(screen) { navigator ->
        bottomBarVisibility.invoke(navigator.size == 1)
        CurrentScreen()
    }
}

object HomeMenuItem : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Home"
            val icon = rememberVectorPainter(Icons.Filled.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        TabContent(HomeScreen())
    }
}

object HistoryMenuItem : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "History"
            val icon = rememberVectorPainter(Icons.Filled.DateRange)

            return TabOptions(
                index = 1u,
                title = title,
                icon = icon
            )
        }

    @Composable
    override fun Content() {
        TabContent(HistoryScreen())
    }
}

object SettingsMenuItem : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Settings"
            val icon = rememberVectorPainter(Icons.Filled.Settings)

            return TabOptions(
                index = 2u,
                title = title,
                icon = icon
            )
        }

    @Composable
    override fun Content() {
        TabContent(SettingsScreen())
    }
}

@Composable
fun RowScope.MenuNavigationItem(menuItem: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current.key == menuItem.key,
        onClick = { tabNavigator.current = menuItem },
        icon = {
            menuItem.options.icon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = menuItem.options.title
                )
            }
        },
        label = {
            Text(menuItem.options.title)
        }
    )
}
