package com.example.testing_day1.data.source

import com.example.testing_day1.data.Result
import com.example.testing_day1.data.Task
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test


class DefaultTasksRepositoryTest{


    @Test
    fun getTasks_FalseUpdate_returnLocal() = runTest {

        //given
        val task1 = Task("task1")
        val task2 = Task("task2")
        val task3 = Task("task3")
        val task4 = Task("task4")

        val remoteTasks = listOf( task1,task2)

        val localTasks = listOf(task3,task4)

        val fakeLocalDataSource = FakeDataSource(localTasks.toMutableList())
        val fakeRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())

        val repo = DefaultTasksRepository(fakeRemoteDataSource,fakeLocalDataSource)

        //when
        val result = repo.getTasks(forceUpdate = false) as Result.Success

        //then
        assertThat(result.data, IsEqual(localTasks))

    }


}