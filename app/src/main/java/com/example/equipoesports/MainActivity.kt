package com.example.equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
}