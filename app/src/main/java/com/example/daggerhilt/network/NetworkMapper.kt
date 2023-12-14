package com.example.daggerhilt.network

import com.example.daggerhilt.model.BLog
import com.example.daggerhilt.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor():EntityMapper<BlogEntry,BLog>{
    override fun mapFromEntity(entity: BlogEntry): BLog {
return BLog(
    id = entity.id,
    title = entity.title,
    body = entity.body,
    image = entity.image,
    category = entity.category
)
    }

    override fun mapToEntity(domainModel: BLog): BlogEntry {

        return BlogEntry(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities:List<BlogEntry>):List<BLog>{
        return entities.map { mapFromEntity(it) }
    }
}