package com.plataformas.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plataformas.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Layout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Layout(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") };
    var imageUrl by remember { mutableStateOf("") };
    var elementsList = remember { mutableStateListOf<Item>() };
    fun add() {
        if(name.isNotEmpty() && imageUrl.isNotEmpty()) {
            elementsList.add(Item(name, imageUrl));
            name = "";
            imageUrl = "";
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyForm(name, { name = it }, imageUrl, { imageUrl = it }, ::add)
        ElementList(elements = elementsList)
    }

}

@Composable
fun ElementList(elements: List<Item>){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(elements) { element ->
            ElementItem(element)
        }
    }
}

@Composable
fun ElementItem(item: Item){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(modifier = Modifier
            .padding(4.dp),
            text = item.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier.
            size(50.dp),
            contentDescription = "nose"
        )
    }
}

@Composable
fun MyForm(
    name: String,
    onNameChange: (String) -> Unit,
    imageUrl:String,
    onImageUrlChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Nombre") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            TextField(
                value = imageUrl,
                onValueChange = onImageUrlChange,
                label = { Text("Url de imagen") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        }
        Button(
            onClick = onAddClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Agregar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4Theme {
        Layout()
    }
}

public data class Item(val name: String, val imageUrl: String)