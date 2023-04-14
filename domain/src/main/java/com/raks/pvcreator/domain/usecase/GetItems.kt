package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.data.entity.toDomain
import com.raks.pvcreator.domain.repository.PvRepository

class GetItems(
    private val repository: PvRepository
) {
    suspend operator fun invoke(tid: Int) = repository.getItems(tid).map { it.toDomain() }
}