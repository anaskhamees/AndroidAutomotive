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

}