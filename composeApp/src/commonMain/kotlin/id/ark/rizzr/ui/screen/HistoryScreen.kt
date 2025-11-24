package id.ark.rizzr.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import id.ark.rizzr.ui.LocalAppFont
import id.ark.rizzr.ui.components.WorkoutItem

class HistoryScreen : Screen {
    @Composable
    override fun Content() {
        val fontFamily = LocalAppFont.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background), // Add background
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "History",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = fontFamily
                    )
                    IconButton(
                        onClick = { /* TODO: Implement filter action */ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Filter Icon",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            items(5) {
                WorkoutItem(fontFamily)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}