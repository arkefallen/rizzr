package id.ark.rizzr.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.ark.rizzr.ui.LocalAppFont

class ListExerciseScreen : Screen {
    @Composable
    override fun Content() {
        val fontFamily = LocalAppFont.current
        val navigator = LocalNavigator.currentOrThrow
        var searchQuery by remember { mutableStateOf("") }
        var selectedCategory by remember { mutableStateOf("All") }

        val exerciseCategories = listOf("All", "Chest", "Back", "Arms", "Legs", "Cardio")

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            stickyHeader {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Choose Exercise",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontFamily = fontFamily
                    )
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)

                        .heightIn(min = 48.dp)
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(top = 20.dp, bottom = 16.dp),
                    placeholder = {
                        Text(
                            text = "Search exercise",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    },
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedTextColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = MaterialTheme.shapes.medium,
                    prefix = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Icon",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(bottom = 12.dp),
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    exerciseCategories.forEach { category ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = if (selectedCategory == category) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceContainerLowest)
                                .padding(8.dp)
                                .clickable {
                                    selectedCategory = category
                                },

                            ) {
                            Text(
                                text = category,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                fontFamily = fontFamily,
                                color = if (selectedCategory == category) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(10) {
                Column(
                    modifier = Modifier
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            "Chest",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        HorizontalDivider(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f),
                            color = DividerDefaults.color.copy(alpha = 0.3f),
                            thickness = 1.dp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainerLow,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Push Up",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                                        shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = "Go Icon",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .rotate(270f)
                                )
                            }
                        }
                    }
                }
            }


        }
    }
}