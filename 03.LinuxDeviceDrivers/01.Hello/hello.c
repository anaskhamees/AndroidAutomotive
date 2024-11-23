#include <linux/module.h>


MODULE_LICENSE("GPL");
MODULE_AUTHOR("Ansa Khamees");
MODULE_DESCRIPTION("A simple Hello World Kernel Module");

static int __init mydriver_init(void)
{
	printk("Hello, This is init Fun of my driver\n");
    return 0;
}

static void __exit mydriver_exit(void)
{
    printk("goodbye, This is exit Fun of my driver\n");
}

module_init(mydriver_init);
module_exit(mydriver_exit);
