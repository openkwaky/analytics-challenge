package openkwaky.challenge.library.repository.database.transformer

import openkwaky.challenge.library.model.Tag
import openkwaky.challenge.library.model.TagType
import openkwaky.challenge.library.repository.database.entity.TagEntity
import javax.inject.Inject

class TagTransformer @Inject constructor() {
    fun transform(tag: Tag): TagEntity =
        with(tag) {
            TagEntity(
                type = type.name,
                additionalData = additionalData
            )
        }

    fun transform(tagEntity: TagEntity): Tag =
        with(tagEntity) {
            Tag(
                type = TagType.valueOf(type!!),
                additionalData = additionalData
            )
        }
}
