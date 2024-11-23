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
 * @details : A simple  Kernel Module for educational purposes.
 */
MODULE_DESCRIPTION("A simple Kernel Module with major Num");

dev_t deviceNum;

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
    printk("%s  open function was called \n", __FUNCTION__);
    return 0;
}

/**
 * @brief   : Closes the device file.
 * @param   : device_file - Pointer to the inode structure, representing the file on disk.
 * @param   : instance - Pointer to the file structure, representing an open file in the current process (RAM).
 * @return  : Always returns 0 (success).
 * @details : This function is called whenever the device file is closed. It logs a message 
 *            to the kernel log indicating that the 'close' function was called.
 */
static int driver_close(struct inode *device_file, struct file *instance)
{
    printk("%s  close function was called \n", __FUNCTION__);
    return 0;
}

/**
 * @brief   : Defines the file operations for the device driver.
 * @details : This structure links the device file operations (open and release) 
 *            to their respective functions in the driver. The owner field is set 
 *            to THIS_MODULE, indicating that this module is responsible for these operations.
 */
struct file_operations fops = {
    .owner = THIS_MODULE,  /* this file structure related to this driver */
    .open = driver_open,
    .release = driver_close
};

/**
 * @brief   : Initializes the device driver module.
 * @return  : Always returns 0 (success).
 * @details : This function is called when the module is loaded into the kernel.
 *  It registers a character device with the major number Dynamically 
 *  and links it with the file operations defined in the 'fops' structure.
 */
static int __init mydriver_init(void)
{
    int returnValue;
    printk("Hello, This is the init function of my driver\n");

    /**
 * alloc_chrdev_region() - register a range of char device numbers
 * @dev: output parameter for first assigned number
 * @baseminor: first of the requested range of minor numbers
 * @count: the number of minor numbers required
 * @name: the name of the associated device or driver
 *
 * Allocates a range of char device numbers.  The major number will be
 * chosen dynamically, and returned (along with the first minor number)
 * in @dev.  Returns zero or a negative error code.
 */
    returnValue=alloc_chrdev_region(&deviceNum,0,1,"AnasDynamicDriver");


    if(returnValue==0)
    {
        printk("%s return value =0  -- Registered Device with MajorNumber: %d , MinorNumber: %d \n",__FUNCTION__,MAJOR(deviceNum),MINOR(deviceNum));
    }
    else
    {
        printk("could Not register Device with Major Numer: %d \n",MAJOR(deviceNum));
        return -1;
    }
    return 0;
}

/**
 * @brief   : Cleans up the device driver module.
 * @details : This function is called when the module is removed from the kernel. 
 *            It unregisters the character device and logs a goodbye message to the kernel log.
 */
static void __exit mydriver_exit(void)
{
    /**
 * unregister_chrdev_region() - unregister a range of device numbers
 * @from: the first in the range of numbers to unregister
 * @count: the number of device numbers to unregister
 *
 * This function will unregister a range of @count device numbers,
 * starting with @from.  The caller should normally be the one who
 * allocated those numbers in the first place...
 */
    unregister_chrdev_region(deviceNum, 1);
    printk("Goodbye, This is the exit function of my driver\n");
}

/**
 * @brief   : Macros to define the module's entry and exit points.
 * @details : These macros tell the kernel which functions to call when the module is loaded 
 *            (mydriver_init) and unloaded (mydriver_exit).
 */
module_init(mydriver_init);
module_exit(mydriver_exit);
