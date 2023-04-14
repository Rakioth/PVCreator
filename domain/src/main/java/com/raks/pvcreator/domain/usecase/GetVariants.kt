package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.data.entity.toDomain
import com.raks.pvcreator.domain.repository.PvRepository

class GetVariants(
    private val repository: PvRepository
) {
    suspend operator fun invoke(id: Int) = repository.getVariants(id).map { it.toDomain() }
}