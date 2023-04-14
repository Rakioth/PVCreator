package com.raks.pvcreator.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName   = "items",
    foreignKeys = [
        ForeignKey(
            entity        = Card::class,
            childColumns  = ["tid"],
            parentColumns = ["id"],
        ),
    ],
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:   Int,
    val ref:  String,
    val code: String,
    @ColumnInfo(index = true)
    val tid:  Int,
)