import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.cleanupTestCoroutines
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher) // Set the main dispatcher to the TestCoroutineDispatcher before tests
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain() // Reset the dispatcher back to the original after tests
        cleanupTestCoroutines() // Clean up any coroutines that may still be running
    }
}
