package com.raks.pvcreator.domain.usecase.pv

import com.raks.pvcreator.domain.util.Encoder
import com.raks.pvcreator.domain.util.PvCreator

class GetBarcode {

    operator fun invoke(pvCreator: PvCreator): List<String> =
        Encoder.getBarcode(pvCreator)

}
