package com.raks.pvcreator.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raks.pvcreator.domain.model.PickerOption

@Entity(tableName = "durations")
data class Duration(
    @PrimaryKey(autoGenerate = true)
    val id:   Int,
    val ref:  String,
    val code: String,
)

fun Duration.toDomain() = PickerOption(id, ref, code)
