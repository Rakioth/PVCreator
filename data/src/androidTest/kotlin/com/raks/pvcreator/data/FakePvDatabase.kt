package com.raks.pvcreator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raks.pvcreator.data.dao.FakePvDao
import com.raks.pvcreator.data.dao.PvDao
import com.raks.pvcreator.data.entity.*

@Database(
    entities     = [Card::class, Item::class, Variant::class, Wildcard::class, Duration::class],
    version      = 1,
    exportSchema = false,
)
abstract class FakePvDatabase : RoomDatabase() {

    abstract fun pvDao():     PvDao

    abstract fun fakePvDao(): FakePvDao

}
