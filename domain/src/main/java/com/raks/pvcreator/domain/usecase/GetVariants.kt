package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetVariants(
    private val repository: PvRepository
) {
    suspend operator fun invoke(id: Int): List<PickerOption> {
        val variants = repository.getVariants(id)
        return listOf(PickerOption.none()) + variants
    }
}