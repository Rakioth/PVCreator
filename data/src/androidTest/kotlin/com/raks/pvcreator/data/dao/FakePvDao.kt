package com.raks.pvcreator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.raks.pvcreator.data.entity.*

@Dao
interface FakePvDao {

    @Insert
    suspend fun insertCard(card: Card)

    @Insert
    suspend fun insertItem(item: Item)

    @Insert
    suspend fun insertVariant(variant: Variant)

    @Insert
    suspend fun insertWildcard(wildcard: Wildcard)

    @Insert
    suspend fun insertDuration(duration: Duration)

}
