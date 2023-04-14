package com.raks.pvcreator.domain.usecase.pv

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetVariants(
    private val repository: PvRepository
) {

    suspend operator fun invoke(id: Int): List<PickerOption> =
        repository.getVariants(id)
                  .toMutableList()
                  .apply { add(0, PickerOption.none()) }

}
