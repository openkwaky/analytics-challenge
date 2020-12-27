package openkwaky.challenge.library.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import openkwaky.challenge.library.repository.database.entity.TagEntity

@Dao
interface TagDao {
    @Query("SELECT * FROM tag")
    fun getAll() : List<TagEntity>

    @Query("DELETE FROM tag")
    fun deleteAll()

    @Insert
    fun add(tag: TagEntity)

    @Delete
    fun delete(tag: TagEntity)
}
