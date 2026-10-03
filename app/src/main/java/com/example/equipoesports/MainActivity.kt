package com.example.equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.equipoesports.ui.theme.EquipoEsportsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Esto hace que la app se dibuje detrás de la barra de estado y de navegación (pantalla completa moderna)
        enableEdgeToEdge()

        // 2. Aquí es donde "conectamos" nuestro diseño visual hecho en Compose con la actividad
        setContent {
            // 3. Aplicamos el tema de colores y estilos que define tu proyecto
            EquipoEsportsTheme() {
                PantallaPrincipal() // Llamamos a nuestra función principal de diseño
            }
        }
    }
    @Composable
    fun PantallaPrincipal() {
        Column(
            modifier = Modifier
                .fillMaxSize()                           // Ocupa toda la pantalla (ancho y alto)
                .background(Color(0xFF121212))           // Pone un color de fondo oscuro (estilo gaming)
                .statusBarsPadding()                     // Evita que el contenido choque con la cámara o la hora del móvil
        ) {
            // TÍTULO
            Text(
                text = "TEAM TITANS",
                color = MaterialTheme.colorScheme.primary, // Usa el color principal de tu tema
                style = MaterialTheme.typography.headlineLarge, // Estilo de texto grande
                fontWeight = FontWeight.ExtraBold,         // Texto muy en negrita
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)   // Centra el texto horizontalmente dentro de la columna
                    .padding(top = 24.dp, bottom = 16.dp)  // Espaciado arriba y abajo
            )
            // JUGADOR 1
            Jugador(
                nombre = "AlexPro",
                rol = "Atacante",
                edad = 21,
                nivel = 87,
                imagen = R.drawable.jugador1
            )
            SeparadorJugadores()
            Jugador(
                nombre = "ShadowX",
                rol = "Defensor",
                edad = 24,
                nivel = 91,
                imagen = R.drawable.jugador2
            )
            SeparadorJugadores()

            // 3. JUGADOR: MartaGG
            Jugador(
                nombre = "MartaGG",
                rol = "Soporte",
                edad = 20,
                nivel = 84,
                imagen = R.drawable.jugador3
            )
            SeparadorJugadores()

            // 4. JUGADOR: Destroyer
            Jugador(
                nombre = "Destroyer",
                rol = "Atacante",
                edad = 23,
                nivel = 89,
                imagen = R.drawable.jugador4
            )
        }
        }
    }

    @Composable
    fun SeparadorJugadores(){
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), // Línea semitransparente con el color principal
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
    }
    @Composable
    fun Jugador(
        nombre: String,
        rol: String,
        edad: Int,
        nivel: Int,
        @DrawableRes imagen: Int

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = "Foto de $nombre",
                contentScale = ContentScale.Crop, // Recorta la imagen para que encaje bien en el círculo
                modifier = Modifier
                    .size(90.dp)                  // Tamaño de 90x90 dp
                    .clip(CircleShape)            // Recorta la forma en círculo
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = rol,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = "$edad años",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )

                Box(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .clip(RoundedCornerShape(8.dp))            // Bordes redondeados para la caja
                        .background(MaterialTheme.colorScheme.primary) // Color de fondo de la caja
                        .padding(horizontal = 10.dp, vertical = 4.dp) // Espacio interior de la caja
                ) {
                    Text(
                        text = "Nivel: $nivel",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
    @Preview(showBackground = true, backgroundColor =0xFF121212 )
    @Composable
    fun JugadorPreview() {
        EquipoEsportsTheme() {
            Jugador(
                nombre = "AlexPro",
                rol = "Atacante",
                edad = 21,
                nivel = 87,
                imagen = R.drawable.jugador1
            )
        }
    }