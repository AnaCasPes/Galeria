package com.example.galeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeria.ui.theme.GaleriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        GaleriaPantalla()
                    }
                }
            }
        }
    }
}

data class Obra(
    val imagen: Int,
    val nombre: String,
    val autor: String,
    val anio: String
)

@Composable
fun GaleriaPantalla(){
        val obras = remember {
        listOf(
            Obra(R.drawable.img, "El grito", "Munch", "1893"),
            Obra(R.drawable.img_1, "Saturno devorando a su hijo", "Goya", "1820"),
            Obra(R.drawable.img_2, "La joven de la perla", "Vermeer", "1665"),
            Obra(R.drawable.img_3, "El Guernica", "Picasso", "1937"),
            Obra(R.drawable.img_4, "La persistencia de la memoria", "Dalí", "1931")
        )
    }

    var currentIndex by remember { mutableIntStateOf((0)) }
    val currentObra = obras[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text="Galería de arte",
            fontWeight = FontWeight.Bold,
            color= Color.Black,
            fontSize = 40.sp,
            textAlign =  TextAlign.Center

        )

        Spacer(modifier = Modifier.height(20.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .shadow(4.dp, RoundedCornerShape(4.dp)),


            color = Color.Black,
            shape = RoundedCornerShape(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(5.dp),

                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = currentObra.imagen),
                    contentDescription = currentObra.nombre,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color( 0xFFFFF3E0)),

        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = currentObra.nombre, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier=Modifier.padding(5.dp),
                    text = "${currentObra.autor} (${currentObra.anio})", fontSize = 20.sp, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) {
                        currentIndex--
                    } else {
                        currentIndex = obras.size - 1
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color( 0xffd84315),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xffe64a19),
                    disabledContentColor = Color.Gray
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Anterior")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = {
                    if (currentIndex < obras.size - 1) {
                        currentIndex++
                    } else {
                        currentIndex = 0
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color( 0xffd84315),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xffe64a19),
                    disabledContentColor = Color.Gray
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    GaleriaTheme {
        GaleriaPantalla()
    }
}