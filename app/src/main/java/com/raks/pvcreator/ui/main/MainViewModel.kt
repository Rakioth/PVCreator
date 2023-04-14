package com.raks.pvcreator.ui.main

import androidx.lifecycle.ViewModel
import com.raks.pvcreator.domain.usecase.PvUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pvUseCases: PvUseCases
) : ViewModel() {
}