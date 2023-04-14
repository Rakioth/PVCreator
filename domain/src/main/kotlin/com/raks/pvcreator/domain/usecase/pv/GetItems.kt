package com.raks.pvcreator.domain.usecase.pv

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetItems(
    private val repository: PvRepository
) {

    suspend operator fun invoke(tid: Int): List<PickerOption> =
        repository.getItems(tid)

}
