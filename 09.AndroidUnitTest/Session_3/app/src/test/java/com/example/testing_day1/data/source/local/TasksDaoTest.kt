package com.example.testing_day1.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing_day1.data.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class TasksDaoTest {

    // Rule to allow LiveData testing in instant execution mode
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ToDoDatabase
    private lateinit var taskDao: TasksDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ToDoDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        // Get a reference to the DAO
        taskDao = database.taskDao()
    }

    @After
    fun tearDown() {
        // Close the database after the test completes
        database.close()
    }

    @Test
    fun getTaskByID_task1_returnsSameTask() = runTest {
        // Create task
        val task = Task(id = "1", title = "Dummy Task")

        // Insert the task into the database
        taskDao.insertTask(task)

        // get the task from the database by ID
        val result = taskDao.getTaskById(task.id)

        // Ensure that the result is not null
        assertThat(result, notNullValue())

        assertThat(result?.title, `is`(task.title))
    }

    @Test
    fun updateTask_AndGetById() = runTest {
        // Step 1: Insert new task
        val initialTask = Task(id = "1", title = "dummy Task", description = "dummy Description")
        taskDao.insertTask(initialTask)

        // Step 2: Modify the task details
        val updatedTask = Task(id = initialTask.id, title = "Modified Task", description = "Modified Description")

        // Step 3: Update the task in the database
        val modifiedTask = taskDao.updateTask(updatedTask)
        assertThat(modifiedTask, `is`(1)) // Ensure that one row was updated

        // Step 4: get the task by ID
        val result = taskDao.getTaskById(initialTask.id)

        // Step 5: Assert and Test
        assertThat(result, notNullValue())
        assertThat(result?.title, `is`("Modified Task"))
        assertThat(result?.description, `is`("Modified Description"))
    }

}
