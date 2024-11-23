package com.example.testing_day1.statistics

import com.example.testing_day1.data.Task
import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class StatisticsUtilsTest{
    @Test
    fun getActiveAndCompletedStats_noCompleted_100ActiveAnd0Completed(){

        /* Inputs for the Function */
        val tasks = listOf<Task>(
            Task("Task1"),
            Task("Task2")
        )

        /* The function to be test in the production */
        val result = getActiveAndCompletedStats(tasks)

        // Assertion
        assertEquals(0f,result.completedTasksPercent)
        //hamcrest assertion
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveCompletedStats_2Completed3Active_60Active40Completed(){
        val tasks = listOf<Task>(
            Task("Task1", isCompleted = true),
            Task("Task2", isCompleted = true),
            Task("Task3"),
            Task("Task4"),
            Task("Task5")
        )

        val result = getActiveAndCompletedStats(tasks)


        assertEquals(40f,result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
        assertThat(result.completedTasksPercent, `is`(40f))
        assertThat(result.activeTasksPercent,`is`(60f))
    }

    @Test
    fun getActiveCompletedStats_emptyList_0ActiveAnd0Completed() {
        val tasks = emptyList<Task>()
        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.activeTasksPercent)
        assertEquals(0f, result.completedTasksPercent)

        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveCompletedStats_nullList_0ActiveAnd0Completed() {
        val tasks: List<Task>? = null

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.activeTasksPercent)
        assertEquals(0f, result.completedTasksPercent)

        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

}