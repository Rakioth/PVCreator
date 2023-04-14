package com.raks.pvcreator.data.repository

import com.raks.pvcreator.data.dao.PvDao
import com.raks.pvcreator.data.entity.toDomain
import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class PvRepositoryImpl(
    private val dao: PvDao
) : PvRepository {

    override suspend fun getCards():            List<PickerOption> =
        dao.getAllCards().map { it.toDomain() }

    override suspend fun getItems(tid: Int):    List<PickerOption> =
        dao.getAllItems(tid).map { it.toDomain() }

    override suspend fun getVariants(id: Int):  List<PickerOption> =
        dao.getAllVariants(id).map { it.toDomain() }

    override suspend fun getWildcards(id: Int): List<PickerOption> =
        dao.getAllWildcards(id).map { it.toDomain() }

    override suspend fun getDurations():        List<PickerOption> =
        dao.getAllDurations().map { it.toDomain() }

}
