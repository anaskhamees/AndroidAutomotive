#include <iostream>
#include <thread>


// Function to produce a beep sound at specified intervals
void beep(unsigned int interval)
{
    while (true)  // Infinite loop
    {
        std::cout << '\a' << std::flush;  // Output beep character and flush the output buffer to ensure immediate sound
        std::this_thread::sleep_for(std::chrono::seconds(interval));  // Pause the thread for the specified interval (in seconds)
    }
}

// Function to prompt the user to press Enter to stop the beeping
void beepAction()
{
    while (true)  // Infinite loop
    {
        std::cout << "Press Enter to stop beeping";  // Prompt the user
        std::cin.ignore();  // Wait for the user to press Enter
        exit(0);  // Exit the program when Enter is pressed
    }
}

int main()
{
    std::thread t1(beep, 3);  // Start the beep thread with a 3-second interval
    std::thread t2(beepAction);  // Start the beepAction thread
    t1.join();  // Wait for the beep thread to finish (this won't happen normally due to the infinite loop)
    t2.join();  // Wait for the beepAction thread to finish (this will happen when Enter is pressed)

    return 0;  // Return 0 to indicate successful execution (although the program will exit in beepAction)
}

