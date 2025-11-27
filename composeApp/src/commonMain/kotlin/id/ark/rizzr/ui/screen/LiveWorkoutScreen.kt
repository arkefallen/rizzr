package id.ark.rizzr.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.resources.compose.painterResource
import id.ark.rizzr.MR
import id.ark.rizzr.ui.LocalAppFont
import id.ark.rizzr.ui.components.ConfirmationDialog
import id.ark.rizzr.ui.components.PrimaryButton

class LiveWorkoutScreen : Screen {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    @Preview
    override fun Content() {
        val fontFamily = LocalAppFont.current
        val navigator = LocalNavigator.currentOrThrow
        var onBackPressed by remember { mutableStateOf(false) }
        var onFinishPressed by remember { mutableStateOf(false) }

        ConfirmationDialog(
            visibility = onFinishPressed,
            title = "Finish Workout",
            message = "Are you sure you want to finish this workout?",
            confirmText = "Yes, Finish",
            cancelText = "No, Continue",
            onConfirm = {
                onFinishPressed = false
                navigator.push(FinishWorkoutScreen())
            },
            onCancel = {
                onFinishPressed = false
            },
            fontFamily = fontFamily
        )

        ConfirmationDialog(
            visibility = onBackPressed,
            title = "Leave Workout",
            message = "Are you sure you want to leave this workout? Your progress will not be saved.",
            cancelText = "Yes, Leave",
            confirmText = "No, Stay",
            onCancel = {
                onBackPressed = false
                navigator.pop()
            },
            onConfirm = {
                onBackPressed = false
            },
            fontFamily = fontFamily
        )

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = "Back Icon",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "00:01:37",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontFamily = fontFamily
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .clickable {
                                    onBackPressed = true
                                }
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Live Workout",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(
                        onClick = {
                            onFinishPressed = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceBright
                        ),
                    ) {
                        Text(
                            "Finish",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            fontFamily = fontFamily,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(MR.images.confuse),
                        contentDescription = "Confuse Image",
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "No exercise added yet!",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = fontFamily
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    PrimaryButton(
                        onClick = {
                            navigator.push(ListExerciseScreen())
                        },
                        text = "Add Exercise",
                        fontFamily = fontFamily,
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "Add Icon",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            }
        }
    }
}