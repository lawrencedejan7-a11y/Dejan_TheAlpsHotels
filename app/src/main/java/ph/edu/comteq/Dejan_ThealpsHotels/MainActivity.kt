package ph.edu.comteq.Dejan_ThealpsHotels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import ph.edu.comteq.Dejan_ThealpsHotels.ui.theme.Dejan_TheAlpsHotelsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dejan_TheAlpsHotelsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Homepage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Homepage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var hotels by remember { mutableStateOf(emptyList<Hotel>()) }

    LaunchedEffect(key1 = Unit) {
        val json = context.assets.open("hotels.json")
            .bufferedReader().use { it.readText() }
        val gson = Gson()
        val hotelsArray = gson.fromJson(json, Array<Hotel>::class.java)
        hotels = hotelsArray.toList()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "The Als Hotel", fontWeight = FontWeight.Bold)

            Image(
                painter = painterResource(id = R.drawable.france_national_flag),
                contentDescription = "French flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(35.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(35.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp)
        ) {
            items(hotels) { hotel ->
                Text(
                    text = hotel.hotel_name,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    Dejan_TheAlpsHotelsTheme {
        Homepage()
    }
}