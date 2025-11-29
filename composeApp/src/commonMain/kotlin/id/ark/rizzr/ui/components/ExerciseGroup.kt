package id.ark.rizzr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseGroup(
    title: String,
    exercises: List<String>,
    fontFamily: FontFamily,
    onExerciseSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                title,
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
        repeat(exercises.size) { index ->
            ExerciseItem(
                fontFamily = fontFamily,
                exercise = exercises[index],
                onSelect = {
                    onExerciseSelected(it)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}