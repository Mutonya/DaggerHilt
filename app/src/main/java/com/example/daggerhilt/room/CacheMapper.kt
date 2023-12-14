package com.example.daggerhilt.room

import com.example.daggerhilt.model.BLog
import com.example.daggerhilt.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor():EntityMapper<BlogEntityCache,BLog>{
    override fun mapFromEntity(entity: BlogEntityCache): BLog {
        return BLog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )

    }

    override fun mapToEntity(domainModel: BLog): BlogEntityCache {
        return BlogEntityCache(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }
    fun mapFromEntityList(entities:List<BlogEntityCache>):List<BLog>{
        return entities.map { mapFromEntity(it) }
    }
}