package com.raks.pvcreator.data.entity

import androidx.room.*

@Entity(
    tableName   = "wildcards",
    indices     = [
        Index(
            name  = "index_wildcards",
            value = ["pid", "eid"],
        ),
    ],
    foreignKeys = [
        ForeignKey(
            entity        = Item::class,
            childColumns  = ["pid"],
            parentColumns = ["id"],
        ),
        ForeignKey(
            entity        = Item::class,
            childColumns  = ["eid"],
            parentColumns = ["id"],
        ),
    ],
)
data class Wildcard(
    @PrimaryKey(autoGenerate = true)
    val id:   Int,
    val ref:  String,
    val code: String,
    @ColumnInfo(index = true)
    val pid:  Int,
    @ColumnInfo(index = true)
    val eid:  Int?,
)