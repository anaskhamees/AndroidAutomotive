package com.example.testing_day1.taskdetail

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.example.testing_day1.Event
import com.example.testing_day1.data.Result
import com.example.testing_day1.data.Result.Success
import com.example.testing_day1.data.Task
import com.example.testing_day1.data.source.DefaultTasksRepository
import com.example.testing_day1.R
import kotlinx.coroutines.launch

/**
 * ViewModel for the Details screen.
 */
class TaskDetailViewModel(application: Application) : AndroidViewModel(application) {

    // Note, for testing and architecture purposes, it's bad practice to construct the repository
    // here. We'll show you how to fix this during the codelab
    private val tasksRepository = DefaultTasksRepository.getRepository(application)

    private val _taskId = MutableLiveData<String>()

    private val _task = _taskId.switchMap { taskId ->
        tasksRepository.observeTask(taskId).map { computeResult(it) }
    }
    val task: LiveData<Task?> = _task

    val isDataAvailable: LiveData<Boolean> = _task.map { it != null }

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _editTaskEvent = MutableLiveData<Event<Unit>>()
    val editTaskEvent: LiveData<Event<Unit>> = _editTaskEvent

    private val _deleteTaskEvent = MutableLiveData<Event<Unit>>()
    val deleteTaskEvent: LiveData<Event<Unit>> = _deleteTaskEvent

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    // This LiveData depends on another so we can use a transformation.
    val completed: LiveData<Boolean> = _task.map { input: Task? ->
        input?.isCompleted ?: false
    }

    fun deleteTask() = viewModelScope.launch {
        _taskId.value?.let {
            tasksRepository.deleteTask(it)
            _deleteTaskEvent.value = Event(Unit)
        }
    }

    fun editTask() {
        _editTaskEvent.value = Event(Unit)
    }

    fun setCompleted(completed: Boolean) = viewModelScope.launch {
        val task = _task.value ?: return@launch
        if (completed) {
            tasksRepository.completeTask(task)
            showSnackbarMessage(R.string.task_marked_complete)
        } else {
            tasksRepository.activateTask(task)
            showSnackbarMessage(R.string.task_marked_active)
        }
    }

    fun start(taskId: String) {
        // If we're already loading or already loaded, return (might be a config change)
        if (_dataLoading.value == true || taskId == _taskId.value) {
            return
        }
        // Trigger the load
        _taskId.value = taskId
    }

    private fun computeResult(taskResult: Result<Task>): Task? {
        return if (taskResult is Success) {
            taskResult.data
        } else {
            showSnackbarMessage(R.string.loading_tasks_error)
            null
        }
    }


    fun refresh() {
        // Refresh the repository and the task will be updated automatically.
        _task.value?.let {
            _dataLoading.value = true
            viewModelScope.launch {
                tasksRepository.refreshTask(it.id)
                _dataLoading.value = false
            }
        }
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        _snackbarText.value = Event(message)
    }
}
