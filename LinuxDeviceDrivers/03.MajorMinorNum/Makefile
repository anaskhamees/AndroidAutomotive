# Specify the name of the module object file (my device driver)
obj-m += majorNumber.o

# Default target: build the module
all:
	# -C: Change to the kernel build directory
	# $(shell uname -r): Get the current kernel version
	# M=$(PWD): Set the module source directory
	# modules: Build the modules
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) modules

# Clean target: clean up the module build files
clean:
	# -C: Change to the kernel build directory
	# $(shell uname -r): Get the current kernel version
	# M=$(PWD) clean: Clean up the module build files in the current directory
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) clean
