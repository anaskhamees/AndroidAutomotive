
#include <linux/module.h>      
#include <linux/moduleparam.h>  
#include <linux/fs.h>     
#include <linux/cdev.h>

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
MODULE_DESCRIPTION("A simple Kernel Module ");

dev_t deviceNum;
struct cdev charDevice;
struct class *myClass;
struct device* myDevice;

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
    printk("%s  open function of the driver was called \n", __FUNCTION__);
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
    printk("%s  close function of the driver was called \n", __FUNCTION__);
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

    // 1. Allocate Device Number
    returnValue = alloc_chrdev_region(&deviceNum, 0, 1, "AnasDynamicDriver");
    if (returnValue != 0)
    {
        printk("%s: Failed to register device number with MajorNumber: %d\n", __FUNCTION__, MAJOR(deviceNum));
        return -1;
    }
    printk("%s: Registered Device with MajorNumber: %d, MinorNumber: %d\n", __FUNCTION__, MAJOR(deviceNum), MINOR(deviceNum));

    // 2. Initialize and Add cdev
    cdev_init(&charDevice, &fops);
    returnValue = cdev_add(&charDevice, deviceNum, 1);
    if (returnValue != 0)
    {
        printk("Failed to register the cdev with the kernel\n");
        goto CHAR_DEV_ERR;
    }

    // 3. Create device class
    myClass = class_create("AnasClass");
    if (IS_ERR(myClass))
    {
        printk("Failed to create device class\n");
        goto CLASS_ERR;
    }

    // 4. Create device file
    myDevice = device_create(myClass, NULL, deviceNum, NULL, "AnasDeviceFile");
    if (IS_ERR(myDevice))
    {
        printk("Failed to create device file\n");
        goto DEV_ERR;
    }

    printk("Anas device driver created successfully\n");
    return 0;

DEV_ERR:
    class_destroy(myClass);
CLASS_ERR:
    cdev_del(&charDevice);
CHAR_DEV_ERR:
    unregister_chrdev_region(deviceNum, 1);
    return -1;
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
