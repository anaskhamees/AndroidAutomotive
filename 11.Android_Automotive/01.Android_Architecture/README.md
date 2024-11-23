# Android Automotive 



## 1. Android Vs Linux

Android is built on the Linux kernel, but it differs significantly from the standard Linux operating system. Here’s an overview of key differences between Android and Linux, especially in the context of automotive applications:

### 1.1. **Kernel Differences:**

- **Linux Kernel:** The standard Linux kernel is a monolithic kernel that is used in a wide variety of systems, including servers, desktops, and embedded devices. It is highly versatile and configurable, but not specifically optimized for mobile devices.

- **Android Kernel:** Android uses the Linux kernel as its foundation but has been modified and optimized for mobile and touchscreen devices. The changes made to the Linux kernel focus on power efficiency, real-time task management, and handling specific hardware components in mobile devices.

  | Feature                | Linux Kernel                                                 | Android Kernel                                               |
  | ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | **Kernel Type**        | [Monolithic kernel](https://www.geeksforgeeks.org/monolithic-kernel-and-key-differences-from-microkernel/) | Modified Linux kernel                                        |
  | **Optimization Focus** | General-purpose computing (servers, desktops)                | Optimized for mobile and touchscreen devices                 |
  | **Power Management**   | Basic power management                                       | Advanced power-saving features, deep sleep                   |
  | **Real-Time Support**  | Optional (via PREEMPT-RT patches)                            | Built-in real-time task management                           |
  | **Hardware Support**   | Generic drivers for a wide range of devices                  | Specific drivers for mobile hardware (touchscreen, sensors, camera) |
  | **Scheduler**          | General-purpose scheduler                                    | Modified scheduler optimized for mobile use                  |
  | **IPC System**         | System V IPC (generic)                                       | Binder IPC (optimized for mobile)                            |
  | **Security**           | Basic security features                                      | SELinux, additional cryptographic modules                    |

### 1.2. **C Libraries:**

- **Linux:** Linux uses **glibc**, the GNU C library, which is designed to be a highly flexible and feature-rich library.
- **Android:** Android uses a custom C library called **Bionic**. Bionic is optimized to be lighter and faster, which is crucial for the resource-constrained environments of mobile devices.

### 1.3. **Scheduler Differences:**

- **Linux Scheduler:** Linux uses the Completely Fair Scheduler (CFS), which provides fair scheduling of tasks but does not focus on real-time requirements.

  - #### **Example:**

    - **Task A:** A long-running program like a video rendering software that performs CPU-heavy computations.
    - **Task B:** A terminal that waits for user input and executes commands (interactive).

    Even if **Task B** is waiting for user input, CFS will give both tasks fair CPU time based on their waiting time. If **Task A** has been running longer, it will keep consuming the CPU, and **Task B** will have to wait its turn, causing some delay. While the system as a whole remains fair, **Task B** may experience noticeable lag because the scheduler doesn’t prioritize responsiveness.

- **Android Scheduler:** While Android uses the same Linux scheduler, it has been tuned for mobile devices, prioritizing responsiveness over fairness in some cases. Android also uses a low-latency mode for real-time interaction.

  - - #### **Example:**

      - **Task A:** A background service performing a network sync in the background.
      - **Task B:** A UI thread waiting for a user touch event to respond instantly.

      In this case, **Task B** (the UI thread) needs to respond to user interactions with minimal delay, so Android will prioritize **Task B** over **Task A** even if **Task A** has been running longer. The scheduler will give **Task B** more CPU time to ensure the UI remains responsive, and **Task A** may be temporarily paused or given lower priority. Additionally, Android’s low-latency mode may be used to ensure quick and smooth handling of real-time interactions like touch events or audio processing, where any delay would be noticeable to the user.

### 1.4. **Inter-Process Communication (IPC):**

- **Linux:** Linux uses traditional IPC mechanisms, such as System V IPC or POSIX message queues.
- **Android:** Android uses its own **Binder IPC** system, which is designed to be more efficient and suitable for high-performance, inter-process communication between apps and system services. Binder is tailored for mobile devices' limited resources and is more secure and reliable for Android's needs.

### 1.1.4.**Linux IPC Mechanisms:**

In a **Linux system**, traditional IPC mechanisms are used to facilitate communication between processes. These mechanisms have been around for decades and are designed for general-purpose systems. Linux provides several options for IPC:

1. **System V IPC**:
   This is one of the older IPC mechanisms that includes:
   - **Message Queues**: Allow processes to send and receive messages.
   - **Semaphores**: Used for synchronization between processes to control access to shared resources.
   - **Shared Memory**: A region of memory that can be mapped into the address space of multiple processes, allowing them to share data directly.
   - **Pipes**: A unidirectional communication channel between processes, typically used for simple data flow.
2. **POSIX IPC**:
   This is a more modern version of IPC that conforms to POSIX (Portable Operating System Interface) standards:
   - **POSIX Message Queues**: A more robust and flexible alternative to System V message queues.
   - **POSIX Shared Memory**: Allows multiple processes to share a region of memory in a POSIX-compliant way.
   - **Semaphores and Mutexes**: For process synchronization.
3. **Sockets**:
   A widely-used IPC method that enables communication between processes over networks or within the same system (local communication through Unix domain sockets).

------

**Example of Linux IPC:**

Imagine two processes:

- **Process A** that writes data to a shared memory segment.
- **Process B** that reads the data from the shared memory segment.

With **POSIX Shared Memory**, both processes can access the same memory region directly, without needing to go through the kernel for each communication. This allows fast, direct data transfer, but the programmer needs to handle synchronization to ensure that no data corruption occurs (e.g., using semaphores).

------

### **Android IPC - Binder:**

On **Android**, traditional IPC mechanisms are not used. Instead, Android uses a custom-designed **Binder IPC** system, which is more optimized for the mobile environment. Binder is tailored to meet the specific needs of mobile devices, such as low power consumption, security, efficiency, and interaction between apps and system services.

#### **Binder IPC Overview:**

- **Binder** is a high-performance, object-oriented IPC mechanism that enables communication between processes on the same device or between different devices.
- It uses a **client-server** model, where one process acts as a client (requesting services) and another process acts as a server (providing services).
- It is designed to be lightweight, fast, and secure, making it ideal for mobile systems with limited resources.

In Android, **Binder** facilitates communication between:

- **Apps**: Apps can interact with each other and access system-level services.
- **Apps and System Services**: Android’s system services (like the Camera, Location, or Audio Services) communicate with apps using Binder.

#### **How Binder Works:**

- **Client-Server Model**: A client process sends a request (like a method call) to a server process. The server executes the request and sends back the result.

- **Parceling (**Marshaling**) and Unparceling  (**De-Marshaling**)**: Binder communication involves “parceling” data, which means packaging it in a special format to send across process boundaries. This data is then “unparcelled” by the receiving process.

  >### **Parceling**:
  >
  >**Parceling** is the process of serializing (or packaging) data into a specific format so that it can be sent between processes. This process is necessary because processes in an operating system run in separate memory spaces, and they can’t directly access each other’s memory. For data to be transferred, it needs to be “flattened” into a format that can be easily passed across process boundaries.
  >
  >#### **Why Parceling is Needed**:
  >
  >- Data that needs to be transferred between processes cannot be shared directly due to memory isolation.
  >- Therefore, data is converted into a "parcel" format, which is essentially a serialized byte stream that represents the data in a compact, transferable form.
  >- The **Binder** system uses **parcels** to transmit this data across processes efficiently.
  >
  >#### **How Parceling Works**:
  >
  >1. **Serialization**: The data (such as integers, strings, arrays, or even complex objects) is converted into a flat byte stream.
  >2. **Packaging**: The serialized data is then packaged into a **Parcel object**, which is a special structure that can be transferred between processes.
  >3. **Transfer**: The **Parcel** is sent over to the target process, where it will be "unparcelled" (i.e., deserialized) for use.
  >
  >#### **Example**:
  >
  >If you're sending a simple string (e.g., `"Hello, World!"`) from one process to another, **parceling** will take the string and convert it into a byte stream that can be transferred across the process boundary.
  >
  >-----------------
  >
  >### **Unparceling**:
  >
  >**Unparceling** is the reverse of parceling. It refers to the process of taking the byte stream (the parcel) and deserializing it back into its original data structure in the receiving process’s memory.
  >
  >#### **Why Unparceling is Needed**:
  >
  >- After the data has been transferred across process boundaries, the receiving process must reconstruct the original data from the byte stream in the parcel.
  >- This ensures that the receiving process can use the data as it was originally intended.
  >
  >#### **How Unparceling Works**:
  >
  >1. **Deserialization**: The receiving process takes the byte stream and reconstructs the original data from it.
  >2. **Restoration**: The data (e.g., integers, strings, arrays, or objects) is then restored into its original form so that the receiving process can work with it.
  >
  >#### **Example**:
  >
  >If the receiving process gets the parcel containing `"Hello, World!"`, **unparceling** will convert the byte stream back into the string `"Hello, World!"` that can be used directly in the code.

  >-------------

- **Thread and Transaction Pool**: Binder uses threads and transaction pools to manage and dispatch requests, ensuring efficient, low-latency communication.

  >**1. Binder Threads**
  >
  >**Binder threads** are the threads responsible for handling the actual communication between the client (requester) and server (provider) in the Binder IPC system.
  >
  >**How Binder Threads Work:**
  >
  >- **Server Side**:
  >  - Each **Binder service** (a service implemented in a system or app) listens for requests from clients. These requests are sent through the Binder interface.
  >  - A **Binder thread** is assigned to each request that comes into the server, allowing the server to handle multiple requests concurrently.
  >  - When a request is made, it is received by the **Binder driver** in the kernel. The kernel then allocates a thread from the thread pool to handle the request. This thread performs the work required to process the request and sends a response back to the client.
  >- **Client Side**:
  >  - On the client side, the **Binder thread** is responsible for sending the request, waiting for a response, and processing it. This can be done in the same thread or a different thread depending on how the client is designed.
  >
  >**Why Threads are Important:**
  >
  >- **Concurrency**: Binder IPC often involves multiple requests at once, so multiple threads allow the server to handle many requests concurrently.
  >
  >- **Non-blocking**: By using different threads, each request can be processed independently. This means the server doesn't have to wait for one request to finish before processing the next one.
  >
  >- **Low Latency**: Using dedicated threads to handle requests ensures that requests are processed quickly, reducing the time it takes for communication between processes.
  >
  >  -------------------------------------------------------------------------------
  >
  >**2. Transaction Pool**
  >
  >The **transaction pool** is a mechanism used by Binder to manage and efficiently handle **transactions** (requests and responses) between clients and services.
  >
  >**What is a Transaction?**
  >
  >A **transaction** refers to a unit of communication between a client and a service. For example, when a client sends a request to a service to retrieve some data, that request along with its response is considered a transaction.
  >
  >**How the Transaction Pool Works:**
  >
  >- **Transaction Queues**: Transactions are placed in a queue (the transaction pool) when they are received by the Binder driver. The pool ensures that each transaction is processed in an orderly manner.
  >- **Transaction Management**:
  >  - **Transaction Queuing**: Requests from multiple clients are queued up for processing. The kernel or server may use a **transaction pool** to keep track of these requests and handle them one by one or concurrently depending on the system's load.
  >  - **Transaction Reuse**: The **transaction pool** allows the system to reuse existing resources for handling transactions, which reduces the overhead of creating and destroying resources for each request. This is especially useful for handling a large number of requests efficiently.
  >- **Transaction Timeout**: If a request takes too long to process, it can be marked as a timeout, and the client may need to handle this situation (e.g., by retrying the request).
  >
  >**Why the Transaction Pool is Important:**
  >
  >- **Efficiency**: The transaction pool helps manage the flow of requests, ensuring that resources (like threads and memory) are not wasted. By reusing transaction slots and pooling resources, Binder can handle large volumes of requests with minimal overhead.
  >- **Orderly Processing**: By queuing transactions and assigning threads to handle them, the transaction pool ensures that requests are handled in an organized way, reducing the chances of race conditions or collisions.
  >- **Load Balancing**: The transaction pool can help balance the load by distributing requests efficiently across available threads. This reduces the chance of overloading a single thread, ensuring smoother performance.
  >
  >-----------------

- **Reference Counting and Memory Management**: Binder handles object references across processes, making it easier to manage shared resources without needing complex memory management techniques.

  >**Reference counting** is a memory management technique where the system keeps track of how many references (or pointers) there are to an object. When the number of references to an object drops to zero (i.e., no process or thread is using it anymore), the object is safely deleted, freeing the memory it occupied.
  >
  >**Binder** uses reference counting to automatically manage memory for objects that are shared between different processes. When no process is using an object anymore (the reference count is 0), Binder will clean it up for you.
  >
  >

------

**Binder IPC Example:**

Let’s say an app wants to use the **Camera Service** on an Android device.

1. The app (client) sends a request to the **Camera Service** (server) using the **Binder** IPC system.
2. The Camera Service processes the request (e.g., opening the camera or taking a picture).
3. The Camera Service sends back the result, such as an image or a success notification, using Binder.

This happens very efficiently, with minimal overhead, and without the need for the app or service to directly manage low-level system details.

####  Differences between Linux IPC and Android Binder:

1. **Purpose and Design**:
   - **Linux IPC**: General-purpose, flexible, and well-suited for desktop/server environments where resources are abundant.
   - **Android Binder**: Optimized for mobile devices, focusing on performance, security, and low power consumption.
2. **Performance**:
   - **Linux IPC**: May involve more overhead due to the variety of mechanisms, such as message queues, pipes, or shared memory.
   - **Android Binder**: Extremely fast and efficient for mobile use cases, minimizing CPU and memory usage.
3. **Security**:
   - **Linux IPC**: While secure, it typically requires explicit synchronization to avoid race conditions and data corruption.
   - **Android Binder**: More secure as it has built-in mechanisms for access control and permission management, ensuring only authorized processes can communicate with system services.
4. **Suitability for Mobile**:
   - **Linux IPC**: Not optimized for resource-constrained environments.
   - **Android Binder**: Specifically designed to be lightweight and efficient on devices with limited CPU, memory, and battery life.

### 1.5. **Hardware Abstraction Layer (HAL):**

- **Linux:** In standard Linux, hardware access is usually done directly via drivers.
- **Android:** Android has a **Hardware Abstraction Layer (HAL)**, which abstracts the underlying hardware from the Android framework. This allows developers to write apps without worrying about the hardware specifics, making the system more flexible and scalable across different devices.

### 1.6. **Memory Management:**

- **Linux:** Linux handles memory management via traditional mechanisms like **Virtual Memory** and **OOM (Out of Memory) Killer**.
- **Android:** Android adds the **Low Memory Killer**, which kills background apps when memory is low to ensure foreground tasks continue running smoothly, tailored to mobile device resource constraints.

### 1.7. **User Interface and Target Audience:**

- **Linux:** Linux is used in a broad spectrum of environments, from servers to desktops, and even embedded systems. It provides a traditional command-line interface (CLI), but also supports graphical interfaces with desktop environments like GNOME, KDE, etc.
- **Android:** Android is designed specifically for mobile devices with a touch interface. It’s heavily optimized for user interaction on smartphones, tablets, and, more recently, automotive infotainment systems. The interface is highly graphical, offering rich apps with gestures and touch support.

### 1.8. **Target Use Cases:**

- **Linux:** Linux is used in personal computers, servers, mainframes, embedded systems, and supercomputers. It's highly versatile, and its core design focuses on stability, security, and multitasking.
- **Android:** Android, on the other hand, is used primarily for mobile devices, with a focus on power efficiency, resource management, and ease of use. Android is heavily optimized for touch-based interactions and running apps that are downloaded from app stores.

### 1.9. **Security:**

- **Linux:** Linux has a strong security model based on traditional UNIX permissions and security modules like SELinux. However, its security can be complex to configure and is mostly set up by system administrators.
- **Android:** Android builds on Linux's security, but it has its own set of features, like **app sandboxing** (isolating apps from one another), and **Google Play Protect**, which helps keep devices secure by scanning apps for malicious activity. It also uses **cryptographic features** to protect sensitive data and ensure app integrity.

### Why Use Android in Automotive Instead of Linux?

1. **Touch Interface and User Experience:** Android provides an intuitive user interface designed for touch interactions, which is ideal for automotive infotainment systems. It integrates well with modern touchscreens, offering a rich experience with apps, voice recognition, and gestures.
2. **App Ecosystem:** Android has access to a vast repository of applications from the **Google Play Store**, which makes it easier to build and integrate third-party applications for automotive infotainment systems, including navigation, media streaming, and productivity apps.
3. **Familiarity and Development Tools:** Android provides an established framework for developers, with robust development tools (like Android Studio) and APIs that are familiar to a large pool of developers. This helps accelerate development and reduces the learning curve compared to working with Linux directly.
4. **Google Services Integration:** Android integrates seamlessly with Google services such as **Google Maps**, **Google Assistant**, **Google Play Music**, and more, which are often desired features in modern infotainment systems.
5. **Real-Time Optimizations:** While Android doesn't support real-time computing natively, it provides a better experience for infotainment systems by optimizing task prioritization, memory management, and multitasking, ensuring a smooth user experience for the average consumer.
6. **Automotive-Specific Customizations:** Google has developed **Android Automotive OS**, a version of Android specifically tailored for the automotive industry. It supports deeper integration with vehicle hardware, sensors, and controls, enabling car manufacturers to customize the system to meet their specific needs.
