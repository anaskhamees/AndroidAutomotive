/**
 * @file   : linux/module.h
 * @brief  : Core header file for creating loadable kernel modules.
 * 
 * @details:
 * - Provides the necessary macros, functions, and data structures that allow the kernel to interact with your module.
 * 
 * - Defines the following macros:
 *   - `MODULE_LICENSE`: Specifies the licensing information of the module.
 *   - `MODULE_AUTHOR`: Specifies the author of the module.
 *   - `MODULE_DESCRIPTION`: Provides a brief description of the module.
 * 
 * - Defines the following macros for module initialization and cleanup:
 *   - module_init()`: Specifies the function to be called when the module is loaded into the kernel.
 *   - module_exit()`: Specifies the function to be called when the module is removed from the kernel.
 * 
 * - Provides logging functionality:
 * - printk(): The kernel's version of `printf` in userspace, used to log messages from your module to the kernel's message buffer (viewable using `dmesg`).
 */
#include <linux/module.h>      

/**
 * @brief   : This header file provides functionality for handling module parameters.
 *            It allows you to define parameters that can be passed to your kernel module
 *            at the time of loading, enabling configurable behavior.
 * 
 * MODULE_PARAM and MODULE_PARM_DESC macros are defined here.
 * - module_param()     : 
 * Allows you to define parameters that users can pass to the module when it is loaded (in run time).
 * - MODULE_PARM_DESC() : 
 * Provides a description for the module parameters, which can be viewed via the `modinfo` command.
 * 
 * This header is essential when you need to create modules that accept user-defined parameters,
 * making your module more flexible and adaptable to different environments or use cases.
 */
#include <linux/moduleparam.h>  /* For handling module parameters */

/**
 * @brief   : This header file is crucial for implementing file operations in kernel modules,
 *            especially for character devices.
 * 
 * The following are defined in this header:
 * - struct file_operations:
 *  A structure used to define the set of functions (like open, read, write, close, etc.)
 *  that your module will use to interact with user space through device files.
 * - register_chrdev()     : 
 * Function used to register a character device driver with the kernel, associating it with a major number.
 * 
 * This header is used when you are developing modules that need to interface with the filesystem,
 * such as creating custom device drivers.
 */
#include <linux/fs.h>         


/**
 * @brief   : Specifies the license type for the module.
 * @details : GPL (General Public License) is used, which is a free software license. 
 *            It allows users to freely use, modify, and distribute the software.
 */
MODULE_LICENSE("GPL");

/**
 * @brief   : Specifies the author of the module.
 * @details : This macro defines the author of the module as "Anas Khamees".
 */
MODULE_AUTHOR("Anas Khamees");

/**
 * @brief   : Describes the purpose of the module.
 * @details : A simple Hello World Kernel Module for educational purposes.
 */
MODULE_DESCRIPTION("A simple Hello World Kernel Module");

/**
 * @brief   : Defines a module parameter.
 * @param   : number - An integer parameter that can be passed during module load.
 * @details : The 'number' variable is declared as a module parameter, which can be passed 
 *            when the module is loaded. The permissions (S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP) 
 *            allow the parameter to be readable and writable by the user and group.
 */
int number = 0;
module_param(number, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP);

/**
 * @brief   : Provides a description of the module parameter.
 * @details : This macro describes the 'number' parameter as the "major number".
 */
MODULE_PARM_DESC(number, "major number");

/**
 * @brief   : Stores the major number of the device.
 * @details : The major number is used by the kernel to identify the device driver associated 
 *            with a particular device.
 */
int majorNumber = 0;

/**
 * @brief   : Opens the device file.
 * @param   : device_file - Pointer to the inode structure, representing the file on disk.
 * @param   : instance - Pointer to the file structure, representing an open file.
 * @return  : Always returns 0 (success).
 * @details : This function is called whenever the device file is opened. It logs a message 
 *            to the kernel log indicating that the 'open' function was called.
 */
static int driver_open(struct inode *device_file, struct file *instance)
{
    printk("%s driver open function was called \n", __FUNCTION__);
    return 0;
}

/**
 * @brief   : Closes the device file.
 * @param   : device_file - Pointer to the inode structure, representing the file on disk.
 * @param   : instance - Pointer to the file structure, representing an open file.
 * @return  : Always returns 0 (success).
 * @details : This function is called whenever the device file is closed. It logs a message 
 *            to the kernel log indicating that the 'close' function was called.
 */
static int driver_close(struct inode *device_file, struct file *instance)
{
    printk("%s driver close function was called \n", __FUNCTION__);
    return 0;
}

/**
 * @brief   : Defines the file operations for the device driver.
 * @details : This structure links the device file operations (open and release) 
 *            to their respective functions in the driver. The owner field is set 
 *            to THIS_MODULE, indicating that this module is responsible for these operations.
 */
struct file_operations fops = {
    .owner = THIS_MODULE,
    .open = driver_open,
    .release = driver_close
};

/**
 * @brief   : Initializes the device driver module.
 * @return  : Always returns 0 (success).
 * @details : This function is called when the module is loaded into the kernel. It registers 
 *            a character device with the major number provided in the 'number' parameter 
 *            and links it with the file operations defined in the 'fops' structure.
 * @note    : The 'majorNumber' is set to the value of 'number' passed during module load.
 */
static int __init mydriver_init(void)
{
    majorNumber = number;
    register_chrdev(majorNumber, "AnasVirtualDriver", &fops);
    printk("Hello, This is the init function of my driver\n");
    return 0;
}

/**
 * @brief   : Cleans up the device driver module.
 * @details : This function is called when the module is removed from the kernel. 
 *            It unregisters the character device and logs a goodbye message to the kernel log.
 */
static void __exit mydriver_exit(void)
{
    unregister_chrdev(majorNumber, "AnasVirtualDriver");
    printk("Goodbye, This is the exit function of my driver\n");
}

/**
 * @brief   : Macros to define the module's entry and exit points.
 * @details : These macros tell the kernel which functions to call when the module is loaded 
 *            (mydriver_init) and unloaded (mydriver_exit).
 */
module_init(mydriver_init);
module_exit(mydriver_exit);
