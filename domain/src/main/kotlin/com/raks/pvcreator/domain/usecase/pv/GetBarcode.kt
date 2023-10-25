package com.raks.pvcreator.domain.usecase.pv

import com.raks.pvcreator.domain.util.*

class GetBarcode {

    operator fun invoke(pvCreator: PvCreator): List<String> =
        Encoder.getBarcode(pvCreator)

}