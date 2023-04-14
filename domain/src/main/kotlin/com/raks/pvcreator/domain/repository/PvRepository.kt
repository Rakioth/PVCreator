package com.raks.pvcreator.domain.repository

import com.raks.pvcreator.domain.model.PickerOption

interface PvRepository {

    suspend fun getCards():            List<PickerOption>

    suspend fun getItems(tid: Int):    List<PickerOption>

    suspend fun getVariants(id: Int):  List<PickerOption>

    suspend fun getWildcards(id: Int): List<PickerOption>

    suspend fun getDurations():        List<PickerOption>

}
