/**
 * For Macros: 
 *      - MODULE_LICENSE
 *      - MODULE_AUTHOR
 *      - MODULE_DESCRIPTION
 *      - module_init()
 *      - module_exit()
 */
#include <linux/module.h> 
/**
 * For Structs :
 *      - file_operations (open, read, write ,close..)
 * For Functions:
 *      - register_chrdev()
 *      - unregister_chrdev_region()
 *      - alloc_chrdev_region()
 */
#include <linux/fs.h>
/**
 * For Structs:
 *      - struct cdev
 * For Functions:
 *      - void cdev_init(struct cdev *cdev, const struct file_operations *fops)
 *      - int cdev_add(struct cdev *p, dev_t dev, unsigned count)
 *      - void cdev_del(struct cdev *p)
 * For MACROs:
 *      - MAJOR
 *      - MINOR
 * cdev.h header includes <linux/device.h> which contains (device_create) function
 */
#include <linux/cdev.h>

/**
 * For Functions:
 *      - struct class * class_create(const char *name);
 *      - void class_destroy(const struct class *cls)
 */
#include <linux/device.h> // Correct header file for class_create and device_create

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Anas Khamees");
MODULE_DESCRIPTION("Virtual Character Device (Kernel Module)");

dev_t deviceNumber;
struct cdev charDevice;
struct class* virtualClass;
struct device* virtualDevice;

/* Driver Open Function */
static int driverOpen(struct inode* deviceFile, struct file* currRunFile)
{
    printk(KERN_INFO "%s - Driver Open Function was Called\n", __FUNCTION__);
    return 0;
}

/* Driver Read Function */
ssize_t driverRead (struct file *file, char __user *usrBuff, size_t count, loff_t *offest)
{
    printk("driverRead Function is called\n");
    return 0;
}
/* Driver Close Function */
static int driverClose(struct inode* deviceFile, struct file* currRunFile)
{
    printk(KERN_INFO "%s - Driver Close Function was Called\n", __FUNCTION__);
    return 0; // Correct return type for `release` function
}
/* File operations structure */
struct file_operations fops = {
    .owner = THIS_MODULE,
    .open = driverOpen,
    .read=driverRead,
    .release = driverClose
};
/* Driver Initialization */
static int __init driverInit(void)
{
    int returnval;
    printk(KERN_INFO "Hello, This is the init function of my driver\n");

    /* 1- Allocate device driver Number */
    returnval = alloc_chrdev_region(&deviceNumber, 0, 1, "AnasDriver");
    if (returnval < 0) {
        printk(KERN_ERR "Could not register device with Major Number: %d\n", MAJOR(deviceNumber));
        return returnval;
    }
    printk(KERN_INFO "%s Return Value = 0 -- AnasDriver Registered with Major Number: %d, Minor Number: %d\n",
           __FUNCTION__, MAJOR(deviceNumber), MINOR(deviceNumber));

    /* 2- Initialize and add character device */
    cdev_init(&charDevice, &fops);
    returnval = cdev_add(&charDevice, deviceNumber, 1);
    if (returnval < 0) {
        printk(KERN_ERR "Failed to register character device to kernel\n");
        goto CHAR_DEVICE_REG_ERR;
    }
    /* 3- Create a device file */
    /* 3.1. Create Class */
    virtualClass = class_create("AnasClass");
    if (IS_ERR(virtualClass)) {
        printk(KERN_ERR "Failed to create device class\n");
        return PTR_ERR(virtualClass);
    }
    /* 3.2. Create Device File */
    virtualDevice = device_create(virtualClass, NULL, deviceNumber, NULL, "AnasDeviceFile");
    if (IS_ERR(virtualDevice)) {
        printk(KERN_ERR "Failed to create device file\n");
        goto DEVICE_CREATE_ERR;
    }
    printk(KERN_INFO "AnasDeviceDriver is Created Successfully\n");
    return 0;

DEVICE_CREATE_ERR:
    class_destroy(virtualClass);
CHAR_DEVICE_REG_ERR:
    cdev_del(&charDevice);
    unregister_chrdev_region(deviceNumber, 1);
    return -1;
}

/* Driver Exit */
static void __exit driverExit(void)
{
    device_destroy(virtualClass, deviceNumber); // Clean up device file
    class_destroy(virtualClass); // Clean up class
    cdev_del(&charDevice); // Remove character device
    unregister_chrdev_region(deviceNumber, 1); // Unregister device number
    printk(KERN_INFO "Goodbye, This is the exit function of my driver\n");
}

module_init(driverInit);
module_exit(driverExit);
