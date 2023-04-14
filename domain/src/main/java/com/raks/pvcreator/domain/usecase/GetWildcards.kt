package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.data.entity.toDomain
import com.raks.pvcreator.domain.repository.PvRepository

class GetWildcards(
    private val repository: PvRepository
) {
    suspend operator fun invoke(id: Int) = repository.getWildcards(id).map { it.toDomain() }
}