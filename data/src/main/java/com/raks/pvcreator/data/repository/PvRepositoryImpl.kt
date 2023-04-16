package com.raks.pvcreator.data.repository

import com.raks.pvcreator.data.dao.PvDao
import com.raks.pvcreator.data.entity.toDomain
import com.raks.pvcreator.domain.repository.PvRepository

class PvRepositoryImpl(
    private val dao: PvDao
) : PvRepository {

    override suspend fun getCards() =
        dao.getAllCards().map { it.toDomain() }

    override suspend fun getItems(tid: Int) =
        dao.getAllItems(tid).map { it.toDomain() }

    override suspend fun getVariants(id: Int) =
        dao.getAllVariants(id).map { it.toDomain() }

    override suspend fun getWildcards(id: Int) =
        dao.getAllWildcards(id).map { it.toDomain() }

}