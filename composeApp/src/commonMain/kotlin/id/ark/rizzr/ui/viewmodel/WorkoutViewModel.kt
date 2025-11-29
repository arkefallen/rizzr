package id.ark.rizzr.ui.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// ScreenModel is basically a ViewModel for Voyager
class WorkoutViewModel : ScreenModel {
    private var _currentListExercises = MutableStateFlow<List<String>>(emptyList())
    val currentListExercise = _currentListExercises.asStateFlow()

    fun addExercise(exercise: String) {
        // Update StateFlow value by creating a new list with the added exercise
        _currentListExercises.value = _currentListExercises.value + exercise
    }

    fun clearExercises() {
        // Clear the list of exercises
        _currentListExercises.value = emptyList()
    }

    fun removeSelectedExercise(exercise: String) {
        // Update StateFlow value by creating a new list without the removed exercise
        _currentListExercises.value = _currentListExercises.value - exercise
    }
}