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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    // Estados para el formulario de añadir
    var nombreInput by remember { mutableStateOf("") }
    var nicknameInput by remember { mutableStateOf("") }
    var rolInput by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    // Estados para controlar el diálogo de Eliminar
    var jugadorAEliminar by remember { mutableStateOf<Jugador?>(null) }

    // Estados para controlar el diálogo de Editar
    var jugadorAEditar by remember { mutableStateOf<Jugador?>(null) }
    var editNombre by remember { mutableStateOf("") }
    var editNickname by remember { mutableStateOf("") }
    var editRol by remember { mutableStateOf("") }
    var editErrorMsg by remember { mutableStateOf("") }

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

        // Formulario de Inserción (Fase 3)
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

        ListaJugadores(
            jugadores = listaJugadores,
            onDelete = { jugador -> jugadorAEliminar = jugador },
            onEdit = { jugador ->
                jugadorAEditar = jugador
                editNombre = jugador.nombre
                editNickname = jugador.nickname
                editRol = jugador.rol
                editErrorMsg = ""
            }
        )
    }

    if (jugadorAEliminar != null) {
        AlertDialog(
            onDismissRequest = { jugadorAEliminar = null },
            title = { Text(text = "Eliminar jugador") },
            text = { Text(text = "¿Estás seguro de que deseas eliminar a ${jugadorAEliminar?.nickname}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        jugadorAEliminar?.let { listaJugadores.remove(it) }
                        jugadorAEliminar = null
                    }
                ) {
                    Text(text = "Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { jugadorAEliminar = null }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    if (jugadorAEditar != null) {
        AlertDialog(
            onDismissRequest = { jugadorAEditar = null },
            title = { Text(text = "Editar Jugador") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editNombre,
                        onValueChange = { editNombre = it },
                        label = { Text("Nombre") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editNickname,
                        onValueChange = { editNickname = it },
                        label = { Text("Nickname") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editRol,
                        onValueChange = { editRol = it },
                        label = { Text("Rol") },
                        singleLine = true
                    )
                    if (editErrorMsg.isNotEmpty()) {
                        Text(
                            text = editErrorMsg,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (editNombre.isBlank() || editNickname.isBlank() || editRol.isBlank()) {
                            editErrorMsg = "Los campos no pueden estar vacíos."
                        } else {
                            // Buscamos el índice para actualizar conservando su ID original
                            val index = listaJugadores.indexOfFirst { it.id == jugadorAEditar?.id }
                            if (index != -1) {
                                listaJugadores[index] = Jugador(
                                    id = jugadorAEditar!!.id,
                                    nombre = editNombre,
                                    nickname = editNickname,
                                    rol = editRol
                                )
                            }
                            jugadorAEditar = null
                        }
                    }
                ) {
                    Text(text = "Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { jugadorAEditar = null }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}

@Composable
fun ListaJugadores(
    jugadores: List<Jugador>,
    onDelete: (Jugador) -> Unit,
    onEdit: (Jugador) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = jugadores,
            key = { jugador -> jugador.id }
        ) { jugador ->
            JugadorItem(
                jugador = jugador,
                onDeleteClick = { onDelete(jugador) },
                onEditClick = { onEdit(jugador) }
            )
            SeparadorJugadores()
        }
    }
}

@Composable
fun JugadorItem(
    jugador: Jugador,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.jugador1),
            contentDescription = "Foto de ${jugador.nombre}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = jugador.nickname,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = jugador.nombre,
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = "Rol: ${jugador.rol}",
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Button(
                onClick = onEditClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Text("Editar", fontSize = 11.sp)
            }

            Button(
                onClick = onDeleteClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f))
            ) {
                Text("Eliminar", color = Color.White, fontSize = 11.sp)
            }
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