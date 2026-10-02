package com.example.equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            // Aquí irán los elementos de nuestra pantalla en orden vertical (Column)
        }
    }
}