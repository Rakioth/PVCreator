package com.raks.pvcreator.domain.usecase.pv

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetWildcards(
    private val repository: PvRepository
) {

    suspend operator fun invoke(id: Int): List<PickerOption> =
        repository.getWildcards(id)
                  .toMutableList()
                  .apply { add(0, PickerOption.none()) }

}
