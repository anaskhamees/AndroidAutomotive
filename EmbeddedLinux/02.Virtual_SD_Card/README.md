-------------------------------------------------------------------------------------------------------------------------
## Table of Contents

- [**How to create SD image in Linux (Virtual SD)**](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#how-to-create-sd-image-in-linux-virtual-sd)
  - [**1. Task Definition**](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#1-task-definition)
  - [**2. Steps of creating virtual SD card**](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#2-steps-of-creating-virtual-sd-card)
      - [2.1. Create a file called "sd.img" has a size of 1 GB (for example) filled by zeros.](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#21-create-a-file-called-sdimg-has-a-size-of-1-gb-for-example-filled-by-zeros)
      - [2.2. Configure the Partitions Table of the Virtual SD card](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#22-configure-the-partitions-table-of-the-virtual-sd-card)
          - [2.2.1. Create the boot partition](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#221--create-the-boot-partition)
          - [2.2.2. Create the rootfs (root file system) partition](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#222--create-the-rootfs-root-file-system-partition)
      - [2.3. Emulate the sd.img as a Storage Device (SD card)](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#23-emulate-the-sdimg-as-a-storage-device-sd-card)
          - [2.3.1. Loop Device Driver](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#231-loop-device-driver)
          - [2.3.2. Partitions Table Formating](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#232-partitions-table-formating)
  - [**3. References**](https://github.com/anaskhamees/Embedded_Linux/blob/main/EmbeddedLinuxTasks/Virtual_SD_Card/README.md#3-references)

-------------------------------------------------------------------------------------------------------------------------

  
# How to create SD image in Linux (Virtual SD)	             

## 1. Task Definition 

Emulate SD card instead of buying the physical hardware by occupy space on your hard disk.

Keep in mind that the virtual SD card image file is just a file on your hard disk, and it does not represent a physical partition or consume a specific part of your hard disk like a traditional partition would. If you want to free up space, you can simply delete the virtual SD card image file.

## 2. Steps of creating virtual SD card

#### 2.1. Create a file called "sd.img" has a size of 1 GB (for example) filled by zeros.

- Open the Linux terminal in your computer and write this command:

```
dd if=/dev/zero of=sd.img bs=1M count=1024
```

> Let's explain the command : 
>
> - ***dd*** : which stands for ***disk/data duplicator***, is a command-line utility used for copying raw data between storage  devices and performing byte-level manipulation tasks, such as creating  disk images. Unlike *[cp](https://www.gnu.org/software/coreutils/manual/html_node/cp-invocation.html#cp-invocation)*, `dd` is not designed for copying individual files but used for reading from and writing to block devices, such as physical hard drives.
>
> - ***if** : stand for **Input File***, in this case, `/dev/zero` is a special file in Linux that provides an endless stream of null bytes when read. 
>
> - ***of*** : stand for ***Output File***, in this case the output file name is`sd.img`.
>
> - ***bs*** : stand for ***block size***, it is a parameter specify the size of the data blocks that are read from the input file (`if`) or written to the output file (`of`) during each iteration of the command.
>
>    Larger block sizes generally result in faster transfers, but the optimal size can depend on the characteristics of the storage devices. In this case ***1 Megabyte*** of date will transfer at a time.
>
> - ***count*** :  this sets the number of blocks to copy. In this example, it's set to ***1024***, so `dd` will copy ***1024 blocks*** of 1 megabyte each, resulting in a ***1 Gigabyte file***.

![Screenshot from 2024-01-08 18-01-39](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/6158a0e8-65e7-429c-8030-55cf365ba186)

![Untitled Diagram](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/ecd81d32-3650-42d3-82e4-ecd116dd7993)


#### 2.2. Configure the Partitions Table of the Virtual SD card

- The required configuration parameters (in my case) to create partitions in virtual SD card : 

  - Size of sd.img file : 1GB
  - Virtual SD card divided into two partitions: ***FAT partition of 200 mb*** and ***EXT4 partition of 800 mb.***
  - The name of the partitions are: first partition ***boot*** and  second partition ***rootfs*** .

  | Partition Size | Partition Name | Partition Format | Bootable (boot partition) |
  | -------------- | -------------- | ---------------- | ------------------------- |
  | 200 MB         | boot           | *FAT 16*         | ***Yes***                 |
  | 800 MB         | rootfs         | *Linux* (Ext4)   | ***No***                  |

  

- Open the Linux terminal in your computer and run this command:

```
cfdisk sd.img
```

> -  ***cfdisk*** : it is command-line utility for managing disk partitions using a curses-based user interface. It provides an interactive way to view, create, delete, and modify disk partitions on a given storage device or disk image.
>
> - `sd.img`: This is the argument passed to `cfdisk`. It specifies the disk image file on which you want to perform partitioning. In this case, the disk image file named `sd.img`.

![Screenshot from 2024-01-08 18-02-41](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/cf9dbed1-6442-43d7-9b63-ff08a9243edf)


- User interface window will open :

  ![Screenshot from 2024-01-08 18-03-11](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/6ac64528-2318-4557-88f1-9eaf8b10b9c6)


- Select **dos**  by keyboard **arrows** and press ***Enter*** , then another configuration window will open :

  ![Screenshot from 2024-01-08 18-03-48](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/d0a84aab-57a1-4029-921f-51e8612827c1)


- Select ***New*** to create new partition and press ***Enter*** , then select the *partition size*  and press ***Enter***.

##### 2.2.1.  Create the ***boot*** partition 

- Select the Size of boot partition (200 M) and press ***Enter***:

![Untitled Diagram1](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/d4667c82-4603-497e-9385-c1a8ceaf2039)



- Then select the type of boot partition (it should be ***primary*** type to make it bootable partition) , usually the primary partition holds the operating system.

 ![Screenshot from 2024-01-08 18-14-31](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/ac281569-029f-4127-8826-50c6f9f08ef9)

  

- Make sure you select ***Bootable*** option for this partition (an Asterisk * will appear in **Boot** option) :

![Screenshot from 2024-01-08 18-17-10](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/9e55358f-b20b-4d7b-b3d9-46e45f4c0e9b)

- Then Press on **Type** option to select file system type for boot partition ( ***FAT16*** )  :

  ![Screenshot from 2024-01-08 18-17-23](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/cbe60457-1040-4dbb-b3b1-64cb28fcf65b)


- Search for **FAT16** file system type and press **Enter** :

  ![Screenshot from 2024-01-08 18-17-47](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/f0337c29-e652-4d31-ba24-36018ecde764)


- Save the configuration of ***boot partition*** by select **Write** option as bellow :

  ![Screenshot from 2024-01-08 18-19-21](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/b7efc439-284a-49e0-a6c2-777819c28e13)

  - There is a question will appear *" Are you sure you want to write the partition table to disk"* , replay by writing ***"Yes"*** and press **Enter** .

    ![Screenshot from 2024-01-08 18-19-43](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/0ce2153d-584c-43d3-8c55-1487bc4c4942)




- Congratulations ! , now you created a new partition has a size of 200Mb

  ![Screenshot from 2024-01-08 18-20-33](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/8a3ca99b-987a-4731-8984-5fcba5a7758a)


- Press ***Quit*** option to exist and create a new partition.

​		![Screenshot from 2024-01-08 18-21-08](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/43799ecc-bca9-4bf6-a633-fc572e129f4d)


##### 2.2.2.  Create the ***rootfs*** (root file system) partition 

- Re-do the same steps above but there are some different parameters: 

- Select the partition size 800M.

  ![Screenshot from 2024-01-08 18-21-16](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/8338049d-f9ec-4d3c-9f19-a5796da94957)


- Then select the type of **rootfs** partition (should be extentend) and press **Enter** :

​	![Screenshot from 2024-01-08 18-21-25](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/0214e140-6a86-4b4f-ab84-16c7f842eb74)


- Then Press on **Type** option to select file system type for rootfs partition:

   ![Screenshot from 2024-01-08 18-21-44](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/9aae647a-7f5d-41ad-ada3-4396145c010f)

- Select Linux file system type (Ext4)

  ![Screenshot from 2024-01-08 18-21-59](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/7a854c48-662f-4764-8c97-c55239f28d27)


- Then save the partition configuration by press on **Write option** and then **Quite** :

![Screenshot from 2024-01-08 18-22-08](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/c0b71850-c50c-40ce-9e98-a20055bdec4a)

![Screenshot from 2024-01-08 18-22-28](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/8201df15-c136-4f51-a67c-d01083064e3c)


![Screenshot from 2024-01-08 18-23-00](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/a525f6f5-53bd-4593-aa56-a1da9325b435)



#### 2.3. Emulate the sd.img as a Storage Device (SD card)

Till now we have the **sd.img**  as a regular file but we want to make ***sd.img*** as a storage device such as  SD card. we can do that by using the  ***loop device driver*** .

##### 2.3.1. Loop Device Driver

The loop driver in Linux is a kernel module that allows a file to be  mapped as a block device.                     The loop driver allows us to emulate block devices using regular files. This is  useful for testing and development when you want  to simulate the behavior of a physical storage device (such as an SD  card, USB drive, or hard disk) without having the physical hardware.

![Untitled Diagram2](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/dd414a66-febb-4705-aa0a-6e447196fcbb)


- Run the command bellow to do that :

  ```
  sudo losetup -f --show --partscan sd.img
  ```

  > - ***sudo***            : run the command with superuser privileges.
  > - ***losetup***       : the loop device setup command.
  > - ***f   flag***         : ***find*** the first available loop device.
  > - ***show flag*** : display the name of the loop device that is associated.
  > - ***partscan***    : scan the image for partitions. This option is useful when the image  contain multiple partition, and you want the loop device to be associated with all of them.
  > - ***sd.img***        : The name of the file (in this case, `sd.img`) that you want to attach as a block device using a loop device.
![image-20240108224103106](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/a88c646e-c147-4229-a03e-a2115fbda798)


- You can show the loop which associated with your virtual SD card by run bellow commands : 

```
#replace loopX by your device loop
losetup -a | grep <loopX>
```

```
lsblk | grep <loopX>
```

![Screenshot from 2024-01-08 22-44-23](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/ea75d2b0-f1f1-4bbb-977f-a77be973e71e)


- We should assign the path of device loop which associated with virtual SD card (block device) to simplicity and re-usability in the next steps:

  ```
  export DISK=/dev/loop<x>
  ```

  ![image-20240108225546951](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/1c31f683-8267-4ac0-874c-0d0cffbecd67)


##### 2.3.2. Partitions Table Formating

We should format the  ***boot***  partition to be  ***FAT16***  :

```
sudo mkfs.vfat -F 16 -n boot ${DISK}p1
```

> - `sudo`: Run the command with superuser privileges.
> - `mkfs.vfat`: This command creates a FAT filesystem on the specified device.
> - `-F 16`: Specifies the FAT type (FAT16 in this case).
> - `-n boot`: Sets the volume label to "boot"
> - `${DISK}p1`: Represents the first partition on the block device, using the variable `DISK` that you've set.

![image-20240108230550299](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/22e8947c-2b91-4873-9ca0-4b410d00648d)


And the  ***rootfs*** to be ***Ext4*** type:

```
sudo mkfs.ext4 -L rootfs ${DISK}p2
```

> - `sudo`: Run the command with superuser privileges.
> - `mkfs.ext4`: This command creates an ext4 filesystem on the specified device.
> - `-L rootfs`: Sets the volume label to "rootfs" .
> - `${DISK}p2`: Represents the second partition on the block device, using the variable `DISK` that you've set.

![image-20240108230846125](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/c5e13201-4a15-4704-800b-831b9a49a7e5)


- To make sure that the partitions formating done correctly, run this command :

  ```
  lsblk -o NAME,LABEL,FSTYPE ${DISK}
  ```

  > - **`lsblk`**: This command is used to list information about block devices, including disks and partitions.
  > - **`-o NAME,LABEL,FSTYPE`**: This part specifies the columns to be displayed in the output. Here's what each column means:
  >   - `NAME`: The name of the block device or partition.
  >   - `LABEL`: The volume label assigned to the filesystem (if available).
  >   - `FSTYPE`: The filesystem type of the partition.
  > - **`${DISK}`**: This is a variable that represents the block device path. 

![Screenshot from 2024-01-08 23-13-26](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/06c70d06-d901-40c3-9c20-5c9397c7ee64)

- Mount The Virtual SD Card
```
cd ~
mkdir boot 
mkdir rootfs
$ sudo mount ${DISK}p1 ~/boot
$ sudo mount ${DISK}p2 ~/rootfs
```

Congratulations ! , We created a Virtual SD card has two partitions first, boot partition and second rootfs partition. 



## 3. References 

- https://github.com/FadyKhalil/EmbeddedLinux/tree/main/3-Uboot
- https://dzone.com/articles/loop-device-in-linux
- https://www.easeus.com/knowledge-center/primary-vs-extended-partition.html
