package com.raks.pvcreator.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raks.pvcreator.domain.model.PickerOption

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id:  Int,
    val ref: String,
)

fun Card.toDomain() = PickerOption(id, ref, null)
