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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        enableEdgeToEdge()
        setContent {
            EquipoEsportsTheme {
                PantallaPrincipal()
            }
        }
    }
}


data class Jugador(
    val id: Int,
    val nombre: String,
    val nickname: String,
    val rol: String
)

val listaJugadores = mutableStateListOf(
    Jugador(1, "Lee Sang-hyeok", "Faker", "Mid"),
    Jugador(2, "Choi Woo-je", "Zeus", "Top"),
    Jugador(3, "Mun Hyeon-jun", "Oner", "Jungla"),
    Jugador(4, "Lee Min-hyeong", "Gumayusi", "ADC"),
    Jugador(5, "Ryu Min-seok", "Keria", "Support")
)

@Composable
fun PantallaPrincipal() {
    var nombreInput by remember { mutableStateOf("") }
    var nicknameInput by remember { mutableStateOf("") }
    var rolInput by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .statusBarsPadding()
            .padding(16.dp)
    ) {

        Text(
            text = "TEAM TITANS",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp, bottom = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            OutlinedTextField(
                value = nombreInput,
                onValueChange = { nombreInput = it },
                label = { Text("Nombre del jugador") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = nicknameInput,
                onValueChange = { nicknameInput = it },
                label = { Text("Nickname") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = rolInput,
                onValueChange = { rolInput = it },
                label = { Text("Rol (ej. Mid, Top...)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (errorMsg.isNotEmpty()) {
                Text(
                    text = errorMsg,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (nombreInput.isBlank() || nicknameInput.isBlank() || rolInput.isBlank()) {
                        errorMsg = "Por favor, rellena todos los campos."
                    } else {
                        errorMsg = ""

                        val nuevoId = if (listaJugadores.isEmpty()) 1 else listaJugadores.maxOf { it.id } + 1

                        listaJugadores.add(
                            Jugador(
                                id = nuevoId,
                                nombre = nombreInput,
                                nickname = nicknameInput,
                                rol = rolInput
                            )
                        )

                        nombreInput = ""
                        nicknameInput = ""
                        rolInput = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir jugador")
            }
        }

        HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))
        ListaJugadores(jugadores = listaJugadores)
    }
}

@Composable
fun ListaJugadores(jugadores: List<Jugador>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = jugadores,
            key = { jugador -> jugador.id }
        ) { jugador ->
            JugadorItem(jugador = jugador)
            SeparadorJugadores()
        }
    }
}

@Composable
fun JugadorItem(jugador: Jugador) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.jugador1),
            contentDescription = "Foto de ${jugador.nombre}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = jugador.nombre,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = jugador.nickname,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = jugador.rol,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun SeparadorJugadores() {
    HorizontalDivider(
        thickness = 2.dp,
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun JugadorPreview() {
    EquipoEsportsTheme {
        PantallaPrincipal()
    }
}