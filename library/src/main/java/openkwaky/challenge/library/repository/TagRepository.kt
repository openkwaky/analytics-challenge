package openkwaky.challenge.library.repository

import openkwaky.challenge.library.model.Tag
import openkwaky.challenge.library.repository.database.LibraryDatabase
import openkwaky.challenge.library.repository.database.transformer.TagTransformer
import openkwaky.challenge.library.repository.datasource.AnalyticsApi
import javax.inject.Inject

class TagRepository @Inject constructor(
    private val tagTransformer: TagTransformer,
    private val database: LibraryDatabase,
    private val api: AnalyticsApi
) {

    fun addTag(tag: Tag) {
        database.tagDao().add(tagTransformer.transform(tag))
    }

    fun sendTags() {
        database.tagDao().getAll().forEach {
            if (api.sendTag(tagTransformer.transform(it))) {
                database.tagDao().delete(it)
            }
        }
    }
}