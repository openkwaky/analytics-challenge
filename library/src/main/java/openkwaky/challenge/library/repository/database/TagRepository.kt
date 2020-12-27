package openkwaky.challenge.library.repository.database

import openkwaky.challenge.library.model.Tag
import openkwaky.challenge.library.repository.database.transformer.TagTransformer
import openkwaky.challenge.library.repository.datasource.AnalyticsApi

class TagRepository constructor(
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