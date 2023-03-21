package id.ac.unpas.penyimpananbuku.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.penyimpananbuku.model.Buku

@Database(entities = [Buku::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bukuDao(): bukuDao
}