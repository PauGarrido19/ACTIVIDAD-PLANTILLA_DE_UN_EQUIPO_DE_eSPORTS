package com.example.equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        }
    }
}