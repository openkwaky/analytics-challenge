package openkwaky.challenge.library.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import openkwaky.challenge.library.model.TagType

@Entity(tableName = "tag")
class TagEntity (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "additional_data") val additionalData: String?
)