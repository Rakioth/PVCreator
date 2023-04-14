package com.raks.pvcreator.domain.usecase.pv

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetDurations(
    private val repository: PvRepository
) {

    suspend operator fun invoke(): List<PickerOption> =
        repository.getDurations()

}
