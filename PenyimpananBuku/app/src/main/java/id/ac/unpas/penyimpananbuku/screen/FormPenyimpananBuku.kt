package id.ac.unpas.penyimpananbuku.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.penyimpananbuku.model.Buku
import id.ac.unpas.penyimpananbuku.persistences.bukuDao
import id.ac.unpas.penyimpananbuku.ui.theme.Purple700
import id.ac.unpas.penyimpananbuku.ui.theme.Teal200
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch

@Composable
fun FormPenyimpananBuku(bukuDao: bukuDao) {
    val scope = rememberCoroutineScope()
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val judul = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal_baca = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal_selesai = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Judul Buku") },
            value = judul.value,
            onValueChange = {
                judul.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Baca") },
            value = tanggal_baca.value,
            onValueChange = {
                tanggal_baca.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "dd-mm-yyyy") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Selesai") },
            value = tanggal_selesai.value,
            onValueChange = {
                tanggal_selesai.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "dd-mm-yyyy") }
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                val id = uuid4().toString()
                val item = Buku(id, nama.value.text, judul.value.text,
                    tanggal_baca.value.text,tanggal_selesai.value.text)
                scope.launch {
                    bukuDao.insertAll(item)
                }
                nama.value = TextFieldValue("")
                judul.value = TextFieldValue("")
                tanggal_baca.value = TextFieldValue("")
                tanggal_selesai.value = TextFieldValue("")

            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                nama.value = TextFieldValue("")
                judul.value = TextFieldValue("")
                tanggal_baca.value = TextFieldValue("")
                tanggal_selesai.value = TextFieldValue("")

            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}