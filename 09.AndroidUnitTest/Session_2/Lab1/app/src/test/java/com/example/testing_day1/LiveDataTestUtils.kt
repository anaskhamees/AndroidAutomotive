import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Extension function for `LiveData` that waits for a value to be set, and returns it.
 *
 * This method is particularly useful in unit tests where you need to await
 * a value to be emitted by `LiveData` within a given time frame. If no value is emitted
 * within the specified timeout, a `TimeoutException` is thrown.
 *
 * @param T The type of data held by the `LiveData`.
 * @param time The maximum time to wait for a `LiveData` value. Default is 2 units.
 * @param timeUnit The time unit for the `time` parameter. Default is `TimeUnit.SECONDS`.
 * @param afterObserve A lambda function to invoke after the observer has been registered.
 *        This is optional and allows custom actions to be performed after starting observation.
 *
 * @return The value of type `T` emitted by `LiveData` if set within the timeout period.
 * @throws TimeoutException if no value is set within the timeout period.
 *
 * @note This function should be used for testing purposes only.
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1) // block the test thread until , Live Data emits a  data

    // Observer that updates the data and counts down the latch
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown() // Releases the test thread
            this@getOrAwaitValue.removeObserver(this) // Removes the observer after receiving the data
        }
    }

    // Registers the observer to observe LiveData indefinitely
    this.observeForever(observer)

    try {
        afterObserve.invoke() // Executes the post-observe action

        // Waits for the LiveData to emit a value or times out
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.") // Throws if time limit is exceeded
        }

    } finally {
        this.removeObserver(observer) // Ensures observer is removed in case of failure or success
    }

    @Suppress("UNCHECKED_CAST")
    return data as T // Returns the emitted value
}
