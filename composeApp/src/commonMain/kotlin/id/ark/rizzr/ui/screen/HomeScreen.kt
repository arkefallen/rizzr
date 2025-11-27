package id.ark.rizzr.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.resources.compose.painterResource
import id.ark.rizzr.MR
import id.ark.rizzr.ui.LocalAppFont
import id.ark.rizzr.ui.components.PrimaryButton
import id.ark.rizzr.ui.components.WorkoutItem
import org.jetbrains.compose.ui.tooling.preview.Preview

class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    override fun Content() {
        val fontFamily = LocalAppFont.current
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Welcome",
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Are you ready for workout today?",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                        Icon(
                            imageVector = Icons.Rounded.Face,
                            contentDescription = "AI Coach Icon",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "AI COACH INSIGHT",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = fontFamily
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Upper body is fatigued.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = fontFamily
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Based on your last workout, you need 24 hour more rest. Today is good for leg day !",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = fontFamily
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text("Let's Go !", fontFamily = fontFamily, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Latest Activity",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(16.dp))
            WorkoutItem(fontFamily)
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryButton(
                onClick = {
                    navigator.push(LiveWorkoutScreen())
                },
                text = "Start Workout",
                fontFamily = fontFamily,
                icon = {
                    Image(
                        painter = painterResource(MR.images.lightning),
                        contentDescription = "Add Icon",
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                    )
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Quick Start",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Feeling lost or bored with your exercises? Try these workout",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                        .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(size = 16.dp))
                                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(MR.images.dumbbell),
                                    contentDescription = "Add Icon",
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                                )
                            }
                            Column {
                                Text(
                                    "Full Body Blast",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontFamily = fontFamily
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "45 mins • Intermediate",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    fontFamily = fontFamily
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = "Go Quick Exercise",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                        .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(size = 16.dp))
                                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(MR.images.dumbbell),
                                    contentDescription = "Add Icon",
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                                )
                            }
                            Column {
                                Text(
                                    "Push Day",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontFamily = fontFamily
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "60 mins • Beginner",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    fontFamily = fontFamily
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = "Go Quick Exercise",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                        .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(size = 16.dp))
                                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(MR.images.dumbbell),
                                    contentDescription = "Add Icon",
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                                )
                            }
                            Column {
                                Text(
                                    "Pull Day",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontFamily = fontFamily
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "50 mins • Beginner",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    fontFamily = fontFamily
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = "Go Quick Exercise",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                        .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(size = 16.dp))
                                    .background(color = MaterialTheme.colorScheme.errorContainer)
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(MR.images.dumbbell),
                                    contentDescription = "Add Icon",
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                                )
                            }
                            Column {
                                Text(
                                    "Legs Destruction",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontFamily = fontFamily
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "90 mins • Veteran",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    fontFamily = fontFamily
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = "Go Quick Exercise",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}