#include <linux/init.h>        // Macros used to mark up functions e.g., __init __exit
#include <linux/module.h>      // Core header for loading LKMs into the kernel
#include <linux/kernel.h>      // Contains types, macros, functions for the kernel

// The __init macro means the function is only used at initialization time
static int __init simple_driver_init(void) {
    printk(KERN_INFO "Simple Driver: Initialization //Hello, I am Anas \\\ \n");
    return 0;  // Return 0 means we are successful
}

// The __exit macro means the function is only used at exit time
static void __exit simple_driver_exit(void) {
    printk(KERN_INFO "Simple Driver: Exit\n");
}

// Macros for registering module entry and exit points
module_init(simple_driver_init);
module_exit(simple_driver_exit);

MODULE_LICENSE("GPL");            // The license type -- this affects runtime behavior
MODULE_AUTHOR("Anas Khamees");       // The author -- visible when you use modinfo
MODULE_DESCRIPTION("A simple Linux driver");  // The description -- see modinfo
MODULE_VERSION("0.1");            // The version of the module

