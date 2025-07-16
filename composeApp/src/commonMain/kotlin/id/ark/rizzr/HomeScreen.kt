package id.ark.rizzr

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Scaffold {
        Column {
            Text("Welcome back,")
            Text("John Doe")
            Text("Are you ready to workout today?")
            Button(onClick = { /*TODO*/ }) {
                Text("Yes")
            }
        }
    }
}