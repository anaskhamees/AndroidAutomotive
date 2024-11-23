

package com.example.testing_day1.statistics

import com.example.testing_day1.data.Task

/**
 * Function that does some trivial computation. Used to showcase unit tests.
 */
internal fun getActiveAndCompletedStats(tasks: List<Task>?): StatsResult {

    val totalTasks = tasks!!.size
    val numberOfActiveTasks = tasks.count { it.isActive }
    val activePercent = 100 * numberOfActiveTasks / totalTasks
    val completePercent = 100 * (totalTasks - numberOfActiveTasks) / totalTasks

    return StatsResult(
        activeTasksPercent = activePercent.toFloat(),
        completedTasksPercent = completePercent.toFloat()
    )
}

data class StatsResult(val activeTasksPercent: Float, val completedTasksPercent: Float)
