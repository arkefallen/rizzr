package id.ark.rizzr.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.resources.compose.painterResource
import id.ark.rizzr.MR
import id.ark.rizzr.ui.LocalAppFont
import id.ark.rizzr.ui.components.MiniStatisticItem
import id.ark.rizzr.ui.components.NewProgressItem
import id.ark.rizzr.ui.components.PrimaryButton

class FinishWorkoutScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val fontFamily = LocalAppFont.current
        val primaryColor = MaterialTheme.colorScheme.primary
        val backgroundColor = MaterialTheme.colorScheme.background

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        Brush.verticalGradient(
                            colors = listOf(primaryColor.copy(alpha = 0.15f), backgroundColor),
                            endY = size.height * 0.25f
                        )
                    )
                },
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    ,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(MR.images.trophy),
                        contentDescription = "Trophy Image",
                        modifier = Modifier
                            .size(128.dp)
                            .rotate(8f)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Workout Crushed!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontFamily = fontFamily,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MiniStatisticItem(
                        modifier = Modifier.weight(0.5f),
                        icon = painterResource(MR.images.time),
                        label = "Duration",
                        value = "1h 45m",
                        fontFamily = fontFamily
                    )
                    MiniStatisticItem(
                        modifier = Modifier.weight(0.5f),
                        icon = painterResource(MR.images.volume),
                        label = "Volume",
                        value = "2000 kg",
                        fontFamily = fontFamily
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Column {
                    NewProgressItem(
                        fontFamily = fontFamily,
                        icon = MR.images.trophy,
                        exercise = "Barbell Bench Press",
                        progress = "+2 reps",
                        progressName = "New Personal Record"
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    NewProgressItem(
                        fontFamily = fontFamily,
                        icon = MR.images.muscle,
                        exercise = "Squat",
                        progress = "100 kg",
                        progressName = "New One-Rep Max"
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 16.dp,
                            shape = RoundedCornerShape(16.dp),
                            spotColor = Color(color = 0xFF6F2ED5),
                            ambientColor = Color(color = 0xFF6425C5)
                        )
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF6F2ED5), Color(0xFF6425C5))
                            )
                        )
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column {
                        Row {
                            Image(
                                painter = painterResource(MR.images.lightning),
                                contentDescription = "AI Coach Icon",
                                modifier = Modifier
                                    .size(24.dp),
                                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primaryContainer)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "AI COACH RECOMMENDATION",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontFamily = fontFamily
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Your workout today is solid! Keep up the heavy hit!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = fontFamily
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            "Eat a balanced meal with protein-rich and stay hydrated to maximize recovery. Ensure to take a rest for the next 24 hours before your leg day session.",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = fontFamily
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Card {
                            Box(
                                modifier = Modifier
                                    .background(color = Color(0xFF5A20AD))
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                            ) {
                                Column {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column {
                                            Text(
                                                "NEXT SESSION",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                                fontFamily = fontFamily,
                                                textAlign = TextAlign.Center,
                                                overflow = TextOverflow.Ellipsis,
                                                maxLines = 1
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                "Leg Day",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp,
                                                color = MaterialTheme.colorScheme.primary,
                                                fontFamily = fontFamily,
                                            )
                                        }
                                        OutlinedButton(
                                            onClick = {
                                                // TODO: Add to calendar functionality
                                            },
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.Notifications,
                                                contentDescription = "Remind Me Icon",
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                "Remind Me",
                                                fontFamily = fontFamily,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp,
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(0.5f)
                                        ) {
                                            Text(
                                                "FOCUS AREAS",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                "Quads, Hamstrings, Glutes",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                            )
                                        }
                                        Column(
                                            modifier = Modifier.weight(0.5f)
                                        ) {
                                            Text(
                                                "DATE",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                "Tomorrow, 10 AM",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                            )
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                PrimaryButton(
                    text = "Back to Home",
                    onClick = {
                        navigator.popAll()
                    },
                    fontFamily = fontFamily,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface
                    ),
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = MaterialTheme.colorScheme.surface,
                        )
                    }
                )
            }
        }
    }
}