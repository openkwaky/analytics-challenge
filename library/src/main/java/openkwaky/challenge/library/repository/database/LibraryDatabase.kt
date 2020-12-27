package openkwaky.challenge.library.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import openkwaky.challenge.library.repository.database.dao.TagDao
import openkwaky.challenge.library.repository.database.entity.TagEntity

@Database(entities = [TagEntity::class], version = 1)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun tagDao() : TagDao
}
