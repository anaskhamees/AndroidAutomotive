#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/uaccess.h>  // for copy_to_user and copy_from_user
#include <linux/cdev.h>
#include <linux/device.h>

#define DEVICE_NAME "simple_char_device"
#define CLASS_NAME  "simple_char_class"

static int major_number;
static char message[256] = {0};
static short message_length;
static struct class*  char_class  = NULL;
static struct device* char_device = NULL;

// Prototypes for device functions
static int     dev_open(struct inode*, struct file*);
static int     dev_release(struct inode*, struct file*);
static ssize_t dev_read(struct file*, char*, size_t, loff_t*);
static ssize_t dev_write(struct file*, const char*, size_t, loff_t*);

// File operations structure
static struct file_operations fops =
{
   .open = dev_open,
   .read = dev_read,
   .write = dev_write,
   .release = dev_release,
};

// Initialization function
static int __init simple_char_init(void) {
    printk(KERN_INFO "SimpleChar: Initializing the SimpleChar LKM\n");

    // Register a major number for the device
    major_number = register_chrdev(0, DEVICE_NAME, &fops);
    if (major_number < 0) {
        printk(KERN_ALERT "SimpleChar failed to register a major number\n");
        return major_number;
    }
    printk(KERN_INFO "SimpleChar: registered correctly with major number %d\n", major_number);

    // Register the device class
    char_class = class_create(THIS_MODULE->name);
    if (IS_ERR(char_class)) {
        unregister_chrdev(major_number, DEVICE_NAME);
        printk(KERN_ALERT "Failed to register device class\n");
        return PTR_ERR(char_class);
    }
    printk(KERN_INFO "SimpleChar: device class registered correctly\n");

    // Register the device driver
    char_device = device_create(char_class, NULL, MKDEV(major_number, 0), NULL, DEVICE_NAME);
    if (IS_ERR(char_device)) {
        class_destroy(char_class);
        unregister_chrdev(major_number, DEVICE_NAME);
        printk(KERN_ALERT "Failed to create the device\n");
        return PTR_ERR(char_device);
    }
    printk(KERN_INFO "SimpleChar: device class created correctly\n");

    return 0;
}

// Exit function
static void __exit simple_char_exit(void) {
    device_destroy(char_class, MKDEV(major_number, 0));
    class_unregister(char_class);
    class_destroy(char_class);
    unregister_chrdev(major_number, DEVICE_NAME);
    printk(KERN_INFO "SimpleChar: Goodbye from the LKM!\n");
}

// Open function
static int dev_open(struct inode *inodep, struct file *filep) {
    printk(KERN_INFO "SimpleChar: Device has been opened\n");
    return 0;
}

// Read function
static ssize_t dev_read(struct file *filep, char *buffer, size_t len, loff_t *offset) {
    int error_count = 0;

    error_count = copy_to_user(buffer, message, message_length);

    if (error_count == 0) {
        printk(KERN_INFO "SimpleChar: Sent %d characters to the user\n", message_length);
        return (message_length = 0);
    } else {
        printk(KERN_INFO "SimpleChar: Failed to send %d characters to the user\n", error_count);
        return -EFAULT;  // Failed to send the message
    }
}

// Write function
static ssize_t dev_write(struct file *filep, const char *buffer, size_t len, loff_t *offset) {
    sprintf(message, "%s(%zu letters)", buffer, len);
    message_length = strlen(message);
    printk(KERN_INFO "SimpleChar: Received %zu characters from the user\n", len);
    return len;
}

// Release function
static int dev_release(struct inode *inodep, struct file *filep) {
    printk(KERN_INFO "SimpleChar: Device successfully closed\n");
    return 0;
}

// Register module entry and exit points
module_init(simple_char_init);
module_exit(simple_char_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Your Name");
MODULE_DESCRIPTION("A simple Linux char driver");
MODULE_VERSION("0.1");

