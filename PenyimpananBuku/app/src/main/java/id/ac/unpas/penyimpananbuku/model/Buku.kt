package id.ac.unpas.penyimpananbuku.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Buku(
    @PrimaryKey val id: String,
    val nama: String,
    val judul: String,
    val tanggal_baca: String,
    val tanggal_selesai: String
)