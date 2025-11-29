package id.ark.rizzr.core.di

import id.ark.rizzr.ui.viewmodel.WorkoutViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { WorkoutViewModel() }
}