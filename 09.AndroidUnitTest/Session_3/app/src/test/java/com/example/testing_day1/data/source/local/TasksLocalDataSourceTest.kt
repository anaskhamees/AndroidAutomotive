import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing_day1.data.Task
import com.example.testing_day1.data.Result.Success
import com.example.testing_day1.data.source.local.TasksDao
import com.example.testing_day1.data.source.local.TasksLocalDataSource
import com.example.testing_day1.data.source.local.ToDoDatabase
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
class TasksLocalDataSourceTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ToDoDatabase
    private lateinit var tasksDao: TasksDao
    private lateinit var tasksLocalDataSource: TasksLocalDataSource

    @Before
    fun setup() {
        // Initialize in-memory database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ToDoDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        // Get a reference to the DAO
        tasksDao = database.taskDao()

        // Initialize the local data source
        tasksLocalDataSource = TasksLocalDataSource(tasksDao)
    }

    @After
    fun tearDown() {
        // Close the database after each test
        database.close()
    }

    @Test
    fun saveTask_retrievesTask() = runTest {
        // Step 1: Create a task
        val task = Task(id = "1", title = "dummy Task", description = "dummy Description")

        // Step 2: Save the task
        tasksLocalDataSource.saveTask(task)

        // Step 3: Retrieve the task
        val result = tasksLocalDataSource.getTask(task.id)

        // Step 4: Test the task was restored correctly
        assertThat(result, `is`(Success(task)))
        val retrievedTask = (result as Success).data
        assertThat(retrievedTask.id, `is`("1"))
        assertThat(retrievedTask.title, `is`("dummy Task"))
        assertThat(retrievedTask.description, `is`("dummy Description"))
    }

    @Test
    fun completeTask_retrievedTask() = runTest {
        // Step 1: Create and save a task
        val task = Task(id = "2", title = "dummy Task", description = "dummy Description")
        tasksLocalDataSource.saveTask(task)

        // Step 2: make the task completed
        tasksLocalDataSource.completeTask(task)

        // Step 3: get the task
        val result = tasksLocalDataSource.getTask(task.id)

        // Step 4: ensure that the task is marked as completed
        assertThat(result, notNullValue())
        val retrievedTask = (result as Success).data
        assertThat(retrievedTask.id, `is`("2"))
        assertThat(retrievedTask.isCompleted, `is`(true))
    }
}
