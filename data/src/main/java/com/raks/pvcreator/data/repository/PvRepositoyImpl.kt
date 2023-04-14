package com.raks.pvcreator.data.repository

import com.raks.pvcreator.data.dao.PvDao
import com.raks.pvcreator.domain.repository.PvRepository

class PvRepositoyImpl(
    private val dao: PvDao
) : PvRepository {

    override suspend fun getCards() =
        dao.getAllCards()

    override suspend fun getItems(tid: Int) =
        dao.getAllItems(tid)

    override suspend fun getVariants(id: Int) =
        dao.getAllVariants(id)

    override suspend fun getWildcards(id: Int) =
        dao.getAllWildcards(id)

}