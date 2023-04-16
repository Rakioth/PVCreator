package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetWildcards(
    private val repository: PvRepository
) {
    suspend operator fun invoke(id: Int): List<PickerOption> {
        val wildcards = repository.getWildcards(id)
        return listOf(PickerOption.none()) + wildcards
    }
}