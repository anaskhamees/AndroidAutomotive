/**
 * @file TasksViewModelTest.kt
 * @brief Unit tests for the TasksViewModel class.
 *
 * This file contains a set of unit tests for the TasksViewModel class, testing its behavior
 * when adding new tasks and applying task filters (all, active, completed).
 *
 * The tests leverage AndroidX testing libraries, Robolectric, and Hamcrest for assertions.
 */

package com.example.testing_day1.tasks

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import getOrAwaitValue
import com.example.testing_day1.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * @brief Configures the test to run without a manifest and sets up the JUnit4 test runner.
 */
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)


class TasksViewModelTest {

    /**
     * @test addNewTask_newTaskEventIsNotNull
     * @brief Tests that adding a new task triggers the newTaskEvent correctly.
     *
     * This test verifies that when a new task is added through the TasksViewModel,
     * the newTaskEvent emits a non-null value, indicating that the event was triggered.
     */
    @Test
    fun addNewTask_newTaskEventIsNotNull() {
        // Given: an instance of TasksViewModel
        val app = ApplicationProvider.getApplicationContext() as Application
        val tasksViewModel = TasksViewModel(app)

        // When: a new task is added
        tasksViewModel.addNewTask()

        // Then: the newTaskEvent is emitted with a non-null value
        val result = tasksViewModel.newTaskEvent.getOrAwaitValue()

        // Asserting that result is not null
        assertThat(result, not(nullValue()))
    }

    /**
     * @test setFiltering_allTasks_setsAllTasksFilter
     * @brief Tests that setting the filter to ALL_TASKS updates the UI components appropriately.
     *
     * This test checks if setting the filter to ALL_TASKS updates the corresponding labels,
     * icons, and visibility attributes in the view model.
     */
    @Test
    fun setFiltering_allTasks_setsAllTasksFilter() {
        // Given: an instance of TasksViewModel
        val app = ApplicationProvider.getApplicationContext() as Application
        val tasksViewModel = TasksViewModel(app)

        // When: the filter is set to ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then: the filtering label, no task label, icon, and visibility are updated
        assertThat(tasksViewModel.currentFilteringLabel.getOrAwaitValue(), `is`(R.string.label_all))
        assertThat(tasksViewModel.noTasksLabel.getOrAwaitValue(), `is`(R.string.no_tasks_all))
        assertThat(tasksViewModel.noTaskIconRes.getOrAwaitValue(), `is`(R.drawable.logo_no_fill))
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

    /**
     * @test setFiltering_activeTasks_setsActiveTasksFilter
     * @brief Tests that setting the filter to ACTIVE_TASKS updates the UI components appropriately.
     *
     * This test checks if setting the filter to ACTIVE_TASKS updates the corresponding labels,
     * icons, and visibility attributes in the view model.
     */
    @Test
    fun setFiltering_activeTasks_setsActiveTasksFilter() {
        // Given: an instance of TasksViewModel
        val app = ApplicationProvider.getApplicationContext() as Application
        val tasksViewModel = TasksViewModel(app)

        // When: the filter is set to ACTIVE_TASKS  (production code action)
        tasksViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS)

        // Then: the filtering label, no task label, icon, and visibility are updated
        assertThat(tasksViewModel.currentFilteringLabel.getOrAwaitValue(), `is`(R.string.label_active))
        assertThat(tasksViewModel.noTasksLabel.getOrAwaitValue(), `is`(R.string.no_tasks_active))
        assertThat(tasksViewModel.noTaskIconRes.getOrAwaitValue(), `is`(R.drawable.ic_check_circle_96dp))
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(false))
    }

    /**
     * @test setFiltering_completedTasks_setsCompletedTasksFilter
     * @brief Tests that setting the filter to COMPLETED_TASKS updates the UI components appropriately.
     *
     * This test checks if setting the filter to COMPLETED_TASKS updates the corresponding labels,
     * icons, and visibility attributes in the view model.
     */
    @Test
    fun setFiltering_completedTasks_setsCompletedTasksFilter() {
        // Given: an instance of TasksViewModel
        val app = ApplicationProvider.getApplicationContext() as Application
        val tasksViewModel = TasksViewModel(app)

        // When: the filter is set to COMPLETED_TASKS
        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)

        // Then: the filtering label, no task label, icon, and visibility are updated
        assertThat(tasksViewModel.currentFilteringLabel.getOrAwaitValue(), `is`(R.string.label_completed))
        assertThat(tasksViewModel.noTasksLabel.getOrAwaitValue(), `is`(R.string.no_tasks_completed))
        assertThat(tasksViewModel.noTaskIconRes.getOrAwaitValue(), `is`(R.drawable.ic_verified_user_96dp))
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(false))
    }
}
