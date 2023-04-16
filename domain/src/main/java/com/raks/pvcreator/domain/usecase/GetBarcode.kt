package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.util.Encoder
import com.raks.pvcreator.domain.util.PvCreator

class GetBarcode {
    operator fun invoke(pvCreator: PvCreator): String =
        Encoder.getBarcode(pvCreator)
}