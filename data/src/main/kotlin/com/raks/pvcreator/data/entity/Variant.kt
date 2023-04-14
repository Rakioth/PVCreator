package com.raks.pvcreator.data.entity

import androidx.room.*
import com.raks.pvcreator.domain.model.PickerOption

@Entity(
    tableName   = "variants",
    indices     = [
        Index(
            name  = "index_variants",
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
data class Variant(
    @PrimaryKey(autoGenerate = true)
    val id:   Int,
    val ref:  String,
    val code: String,
    @ColumnInfo(index = true)
    val pid:  Int,
    @ColumnInfo(index = true)
    val eid:  Int?,
)

fun Variant.toDomain() = PickerOption(id, ref, code)
