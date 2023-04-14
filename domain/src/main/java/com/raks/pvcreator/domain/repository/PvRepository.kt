package com.raks.pvcreator.domain.repository

import com.raks.pvcreator.data.entity.*

interface PvRepository {

    suspend fun getCards():            List<Card>

    suspend fun getItems(tid: Int):    List<Item>

    suspend fun getVariants(id: Int):  List<Variant>

    suspend fun getWildcards(id: Int): List<Wildcard>

}