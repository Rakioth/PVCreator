package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.data.entity.toDomain
import com.raks.pvcreator.domain.repository.PvRepository

class GetCards(
    private val repository: PvRepository
) {
    suspend operator fun invoke() = repository.getCards().map { it.toDomain() }
}