package com.raks.pvcreator.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.raks.pvcreator.data.entity.*

@Dao
interface PvDao {

    @Query("SELECT * FROM cards")
    suspend fun getAllCards():            List<Card>

    @Query("SELECT * FROM items WHERE tid = :tid")
    suspend fun getAllItems(tid: Int):    List<Item>

    @Query("SELECT * FROM variants WHERE pid = :id OR eid = :id")
    suspend fun getAllVariants(id: Int):  List<Variant>

    @Query("SELECT * FROM wildcards WHERE pid = :id OR eid = :id")
    suspend fun getAllWildcards(id: Int): List<Wildcard>

    @Query("SELECT * FROM durations")
    suspend fun getAllDurations():        List<Duration>

}
