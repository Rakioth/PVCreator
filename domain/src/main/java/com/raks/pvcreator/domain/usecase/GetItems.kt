package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetItems(
    private val repository: PvRepository
) {

    suspend operator fun invoke(tid: Int): List<PickerOption> =
        repository.getItems(tid)

}