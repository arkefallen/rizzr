package id.ark.rizzr.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.resources.compose.painterResource
import id.ark.rizzr.MR
import id.ark.rizzr.ui.LocalAppFont
import id.ark.rizzr.ui.components.ConfirmationDialog
import id.ark.rizzr.ui.viewmodel.WorkoutViewModel

class LiveWorkoutScreen : Screen {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    @Preview
    override fun Content() {
        val fontFamily = LocalAppFont.current
        val navigator = LocalNavigator.currentOrThrow
        val workoutScreenModel = navigator.rememberNavigatorScreenModel { WorkoutViewModel() }

        var onBackPressed by remember { mutableStateOf(false) }
        var onFinishPressed by remember { mutableStateOf(false) }
        var onSetDialogPressed by remember { mutableStateOf(false) }
        var tempSet by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
        var indexSet by remember { mutableIntStateOf(0) }
        var weight by remember { mutableStateOf("") }
        var reps by remember { mutableStateOf("") }
        val setsExercise by workoutScreenModel.currentListExercise.collectAsState()

        ConfirmationDialog(
            visibility = onFinishPressed,
            title = "Finish Workout",
            message = "Are you sure you want to finish this workout?",
            confirmText = "Yes, Finish",
            cancelText = "No, Continue",
            onConfirm = {
                onFinishPressed = false
                workoutScreenModel.clearExercises()
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
                workoutScreenModel.clearExercises()
                onBackPressed = false
                navigator.pop()
            },
            onConfirm = {
                onBackPressed = false
            },
            fontFamily = fontFamily
        )

        if (onSetDialogPressed) {
            Dialog(onDismissRequest = {
                onSetDialogPressed = false
            }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp),
                    ) {
                        Text(
                            text = "Add Set",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                            lineHeight = 32.sp,
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Weight (kg)",
                                fontFamily = fontFamily,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        val currentWeight = weight.toDoubleOrNull() ?: 0.0
                                        if (currentWeight > 0.0) {
                                            weight = (currentWeight - 0.25).toString()
                                        }
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Increase Set Icon",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    )
                                }
                                TextField(
                                    value = weight,
                                    onValueChange = { value ->
                                        weight = value
                                    },
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .weight(0.5f),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    colors = TextFieldDefaults.colors(
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
                                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                                    ),
                                    singleLine = true,
                                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                IconButton(
                                    onClick = {
                                        val currentWeight = weight.toDoubleOrNull() ?: 0.0
                                        weight = (currentWeight + 0.25).toString()
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Increase Set Icon",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Reps",
                                fontFamily = fontFamily,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        val currentReps = reps.toIntOrNull() ?: 0
                                        if (currentReps > 0) {
                                            reps = (currentReps - 1).toString()
                                        }
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Increase Set Icon",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    )
                                }
                                TextField(
                                    value = reps,
                                    onValueChange = { value ->
                                        reps = value
                                    },
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .weight(0.5f),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    colors = TextFieldDefaults.colors(
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
                                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                                    ),
                                    singleLine = true,
                                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                IconButton(
                                    onClick = {
                                        val currentReps = reps.toIntOrNull() ?: 0
                                        reps = (currentReps + 1).toString()
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Increase Set Icon",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            OutlinedButton(
                                onClick = {
                                    onSetDialogPressed = false
                                },
                            ) {
                                Text(
                                    "Cancel",
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                )

                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Button(
                                onClick = {
                                    indexSet += 1
                                    tempSet = tempSet + listOf(
                                        mapOf(
                                            "sets" to indexSet.toString(),
                                            "weight" to weight,
                                            "reps" to reps,
                                        )
                                    )
                                    Log.d("DEBUK", "Temp Set $indexSet : $tempSet")
                                    onSetDialogPressed = false
                                },
                            ) {
                                Text(
                                    "Save",
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navigator.push(ListExerciseScreen())
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Back Icon",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Add Exercise",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontFamily = fontFamily
                        )
                    }
                }
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
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
                                containerColor = MaterialTheme.colorScheme.primary,
                            ),
                        ) {
                            Text(
                                "Finish",
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontFamily = fontFamily,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(size = 16.dp))
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainerLow.copy(
                                    alpha = 0.5f
                                )
                            )
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Time Elapsed",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontFamily = fontFamily
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.PlayArrow,
                                    contentDescription = "Resume/Pause Icon",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    "00:01:37",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontFamily = fontFamily
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                if (setsExercise.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        setsExercise.toList().forEach { exercise ->
                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(size = 16.dp))
                                    .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            exercise,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 18.sp,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontFamily = fontFamily
                                        )
                                        Box(
                                            modifier = Modifier
                                                .clip(shape = RoundedCornerShape(size = 16.dp))
                                                .background(color = MaterialTheme.colorScheme.errorContainer)
                                                .padding(8.dp)
                                                .clickable {
                                                    workoutScreenModel.removeSelectedExercise(
                                                        exercise
                                                    )
                                                }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.Delete,
                                                contentDescription = "Delete Icon",
                                                tint = MaterialTheme.colorScheme.onErrorContainer,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    HorizontalDivider(
                                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Box(modifier = Modifier.weight(1f)) {
                                            Text(
                                                "Sets",
                                                fontSize = 16.sp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontFamily = fontFamily,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                        Box(modifier = Modifier.weight(1f)) {
                                            Text(
                                                "Weight",
                                                fontSize = 16.sp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontFamily = fontFamily,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                        Box(modifier = Modifier.weight(1f)) {
                                            Text(
                                                "Reps",
                                                fontSize = 16.sp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontFamily = fontFamily,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    if (tempSet.isNotEmpty()) {
                                        tempSet.forEach { set ->
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .background(
                                                        color = MaterialTheme.colorScheme.surfaceContainerLowest.copy(
                                                            alpha = 0.3f
                                                        )
                                                    )
                                                    .padding(vertical = 8.dp)

                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Text(
                                                        set["sets"] ?: "-",
                                                        fontSize = 16.sp,
                                                        color = MaterialTheme.colorScheme.onSurface,
                                                        fontFamily = fontFamily,
                                                        textAlign = TextAlign.Center,
                                                        modifier = Modifier.fillMaxWidth().weight(1f)
                                                    )
                                                    Text(
                                                        "${set["weight"] ?: "-"} kg",
                                                        fontSize = 16.sp,
                                                        color = MaterialTheme.colorScheme.onSurface,
                                                        fontFamily = fontFamily,
                                                        textAlign = TextAlign.Center,
                                                        modifier = Modifier.fillMaxWidth().weight(1f)
                                                    )
                                                    Text(
                                                        set["reps"] ?: "-",
                                                        fontSize = 16.sp,
                                                        color = MaterialTheme.colorScheme.onSurface,
                                                        fontFamily = fontFamily,
                                                        textAlign = TextAlign.Center,
                                                        modifier = Modifier.fillMaxWidth().weight(1f)
                                                    )
                                                }
                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            onSetDialogPressed = true
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Update Log", fontFamily = fontFamily)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        Spacer(modifier = Modifier.height(96.dp))
                    }
                } else {
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
                    }
                }
            }
        }
    }
}