package id.ac.unpas.penyimpananbuku.persistences

import id.ac.unpas.penyimpananbuku.model.Buku
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface bukuDao {
    @Query("SELECT * FROM Buku")
    fun loadAll(): LiveData<List<Buku>>

    @Query("SELECT * FROM Buku WHERE id = :id")
    fun find(id: String): Buku?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Buku)

    @Delete
    fun delete(item: Buku)
}