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
#include<linux/fs.h>
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
#include<linux/cdev.h>

/**
 * For Functions:
 *      - struct class *  class_create(const char *name);
 *      - void class_destroy(const struct class *cls)
 */
#include <linux/device/class.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Anas Khamees");
MODULE_DESCRIPTION("Virtual Character Device (Kernel Module)");

dev_t          deviceNumber ;
struct cdev    charDevice   ;
struct class*  virtualClass ;
struct device* virtualDevice; 

static int driverOpen(struct inode* deviceFile,struct file* currRunFile)
{
    printk("%s - Driver Open Function was Called \n");
    return 0;
}

static in driverClose(struct inode* deviceFile,struct file* currRunFile)
{
    printk("%s - Driver close Function was Called\n");
}

struct file_operations fops{
    .owner=THIS_MODULE,
    .open=driverOpen,
    .release=driverClose
};

static int __init driverInit()
{
    int returnval;
    printk("Hello, This is the init function of my driver\n");
    
    /* 1- Allocate device driver Number */

    returnval=alloc_chrdev_region(&deviceNumber,0,1,"AnasDriver");
    if(returnval==0)
    {
        printk("%s Return Value = 0 -- AnasDriver Registered with Major Number: %d ,, Minor Number : %d \n",__FUNCTION__,MAJOR(deviceNumber),MINOR(deviceNumber));
    }
    else
    {
        printk("could Not register Device with Major Numer: %d \n",MAJOR(deviceNum));
        return -1;
    }

/* 2- Define Is the driver character or Block or Network Device*/
    cdev_init(&charDevice,&fops);
    returnval=cdev_add(&charDevice,1);
    if(returnval!=0)
    {
        printk("Faild to register a device driver to kernel \n");
        goto CHAR_DEVICE_REG_ERR;
    }
/* 3- Generate a File for a device driver */
/* 3.1. Create Class */
    virtualClass=class_create("AnasClass");
    if(virtualClass==NULL)
    {
        printk("Faild to create Device Class");
        goto CLASS_ERR;
    }
    /* 3.2. Create  Device File */
    virtualDevice=device_create(virtualClass,NULL,deviceNumber,NULL,"AnasDeviceFile");
    if(virtualDevice==NULL)
    {
        printk("Faild to create device file");
    }
    
    printk("AnasDeviceDriver is Created Sucessfully\n");
    return 0;
}

static void __exit driverExit(void)
{
    unregister_chrdev_region(deviceNum, 1);
    printk("Goodbye, This is the exit function of my driver\n");
}

DEVICE_CREATE_ERR:
    class_destroy(virtualClass);
CLASS_ERR:
    cdev_del(&charDevice);
CHAR_DEVICE_REG_ERR:
    unregister_chrdev_region(deviceNumber,1);
    return -1;

module_init(driverInit);
module_exit(driverExit);