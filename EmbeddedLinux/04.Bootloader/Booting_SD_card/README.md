## Table of Contents 
- ### [U-boot (Bootloader)](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#u-boot-bootloader)
    - [**1. Booting Sequence**](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#1-booting-sequence)
        - [1.1. Computer (x86) Booting sequence](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#11-computer-x86-booting-sequence)
        - [1.2. BeagleBone Booting Sequence](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#12-beaglebone-booting-sequence)
    - [**2. Install U-boot**](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#2-install-u-boot)
        - [2.4. U-boot Machine Configurations](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#24-u-boot-machine-configurations)
        - [2.5. U-boot Configurations](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#25-u-boot-configurations)
        - [2.6. Build U-boot](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#26-build-u-boot)
    - [**3. Test U-boot over Vexpress-a9 machine using Qemu emulator**](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#3-test-u-boot-over-vexpress-a9-machine-using-qemu-emulator)
        - [3.1. Install Qemu](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#31-install-qemu)
        - [3.2. Run qemu with Emulated SD card](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#32-run-qemu-with-emulated-sd-card)
        - [3.3. Booting](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#33-booting)
   - [**4. References**](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Bootloader#4-references)



-----------------------------------------------------------------------------------------------------------------------------------------------

# U-boot (Bootloader)

Generally the bootloader is an application reads the partition table in the MBR / GPT to identify the active (bootable) partition. It loads the boot sector of the active partition into DRAM. It has two main responsibilities: first, loads the Kernel into DRAM and passing control to the kernel. Second,  loading new boot images into memory, and running diagnostics.  

U-Boot, short for Das U-Boot (German for "The U-Boot"), is an open-source bootloader widely used in embedded systems, particularly in the realm of embedded Linux. It plays a crucial role in the boot process of these systems. It perform various low-level hardware initialization tasks and  boot the device's operating system kernel. It is available for a number  of computer architectures, including 68k, ARM, Blackfin, MicroBlaze,  MIPS, Nios, SuperH, PPC, RISC-V and x86.

> I suggest you to take a look on the booting sequence to understand the importance of bootloader.



### 1. Booting Sequence

#### 1.1. Computer (x86) Booting sequence 

![X86_BootingSequence-Page-1(1)](README.assets/X86_BootingSequence-Page-1(1).jpg)



#### 1.2. BeagleBone Booting Sequence

![BeagleBoneBooting](README.assets/BeagleBoneBooting.jpg)



### 2. Install U-boot

##### 2.1. Cloning the U-boot Repository

```g
git clone git@github.com:u-boot/u-boot.git
```

> - **`git clone`:** This is the Git command used for cloning a repository. It creates a copy of a Git repository, including all of its files, commit history, and branches.
> - **`git@github.com:u-boot/u-boot.git`:**
>   - **`git@github.com`:** This part specifies the Git protocol and the host from which to clone the repository. In this case, it indicates that the repository is hosted on GitHub.
>   - **`u-boot/u-boot.git`:** This part specifies the path to the repository on GitHub. In this case, it points to the U-Boot repository, which is named "u-boot.git." The format is `username/repo`.
>
> So, when you run this command, Git will connect to the GitHub repository at `git@github.com:u-boot/u-boot.git`, download all the files, and set up a local copy on your machine. The local copy will be placed in a directory named "u-boot," and you can then navigate into that directory to work with the cloned repository.
![Screenshot from 2024-01-11 03-17-20](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/914f2ec4-3d2a-4f5d-b72e-ff007185a11f)


##### 2.2. Go to U-boot directory

```
cd u-boot/
```

##### 2.3. Switch to specific (stable) branch of U-boot Repository (v2022.07)

```
git checkout v2022.07
```

>- **`git checkout`:** This Git command is used for switching between branches or checking out specific  branches.
>- **`v2022.07`:** This is the tag or commit identifier you are checking out. In Git, tags are often used to mark specific releases or versions of a project. In this context, `v2022.07`refers to the U-Boot release version from July 2022.

![Screenshot from 2024-01-11 03-29-46](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/6b57bd71-7973-4bf3-ab28-cbad5af6b4ea)


##### 2.4. U-boot Machine Configurations

```
ls configs/ | grep [your machine] 
```

>The command is used to list and filter U-Boot configuration files based on a specific machine or target platform. Let's break down the components of the command:
>
>- **`ls configs/`:** This part of the command uses the `ls` (list) command to display the contents of the `configs/` directory. The `configs/` directory typically contains configuration files for various target machines(x86, Vexpress, Raspberry Pi, BeagleBone.....etc).
>- **`|`:** This is the pipe symbol, and it is used to pass the output of the `ls` command as input to the `grep` command.
>- **`grep [your machine]`:** `grep` command to search for lines in the output of `ls` command, which in this case, is `[your machine]`. The square brackets `[]` indicate a placeholder where you would substitute the actual name of your target machine.



![Home Diagram](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/60198c4f-00ae-4601-ab45-60fc4de51829)

- **In my case I use Vexpress Cortex A9 (Qemu Emulator)**

```
ls configs/ | grep vexpress_ca9x4_defconfig
```

![Screenshot from 2024-01-11 03-49-26](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/c1829829-8b6d-45b2-986c-044b66afb4bc)


- Set the cross compiler into environment variables to enable the U-boot to use it

  >  Remember we use ARM compiler (arm-cortexa9_neon-linux-musleabihf) 
  >
  > ```
  > export CROSS_COMPILE=path/to/cross/compiler/arm-cortexa9_neon-linux-musleabihf-
  > ```

```python
#In my case
export CROSS_COMPILE=~/x-tools/arm-cortexa9_neon-linux-musleabihf/bin/arm-cortexa9_neon-linux-musleabihf-
```

>- **`export`:** This is a shell command used to create environment variables(global). 
>
>- **`CROSS_COMPILE=`:** This sets the environment variable named `CROSS_COMPILE`. 
>
>- **`~/x-tools/arm-cortexa9_neon-linux-musleabihf`:** It is the path to a cross-compilation toolchain. 
>
>  - `arm-cortexa9_neon`: Specifies the ARM Cortex-A9 processor architecture with NEON SIMD (Single Instruction, Multiple Data) extension.
>  - `linux`: Indicates that the compiler generates binaries for the Linux operating system.
>  - `musleabihf`: Suggests the use of the musl libc library and the hard float ABI (Application Binary Interface) for ARM.
>  - **`bin/`:** the `bin/` subdirectory typically contains the cross-compiler binaries (gcc, ar, as...) .
>  - Ensure that the path ends with a hyphen (`-`), as it is part of the standard convention for cross-compilation toolchains.
>
>  The `~` at the beginning of the path represents the user's home directory.

```
export ARCH=arm
```

>Specify the architecture of the target to be ARM architecture 
![Screenshot from 2024-01-11 05-05-06](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/f8f1fb8c-944e-41ad-a4d7-b1833362e4f9)


- Use the default configurations of ARM Vexpress Cortex A9

  ```
  make vexpress_ca9x4_defconfig
  ```

 ![Screenshot from 2024-01-11 05-05-25](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/2ad97c5f-5bbe-46b6-9c9b-ad2c49bb4b22)

> If you faced this error when run the above command :
>
> ![Screenshot from 2024-01-11 04-26-40](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/48d547dd-798e-4177-bbd9-3b64e4700a8c)

>
> [SOLVED] : select the correct path of the cross compiler of arm



##### 2.5. U-boot Configurations

```
make menuconfig
```

A user interface window will open , customize U-Boot for your specific board. You can try and select your options.

![image-20240111063908591](README.assets/image-20240111063908591.png)

 

- **But there are mandatory options must be selected** :

  

- [x]  Support **bootd**.

  > `bootd` is a U-Boot command used to boot an operating system image from a device.

  - Select ***Command line interface*** :

    ![image-20240111064321339](README.assets/image-20240111064321339.png)

  - Select ***Boot commands*** :

    ![image-20240111064507297](README.assets/image-20240111064507297.png)

  - Select ***bootd*** and ***run*** : 

    ![image-20240111064641319](README.assets/image-20240111064641319.png)

- [x]  Support **editenv**.

  > `editenv` is a U-Boot command that allows the user to interactively edit the U-Boot environment variables.
  > - Select **Environment commands** :

![Screenshot from 2024-01-11 06-47-43](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/896a6062-721d-4932-9b76-ca3623ce8142)

  - Select ***editenv***  and ***saveenv*** :

    ![image-20240111065412397](README.assets/image-20240111065412397.png)

- [x]  Store the environment variable inside file call **uboot.env**.

  >uboot.env  is a file that stores U-Boot environment variables persistent across reboots and these variables can be saved to and loaded from a file to maintain their values across power cycles.

  -  ***Select Enviroments*** and then make surename of FAT file is ***uboot.env***:

    ![image-20240111141317596](README.assets/image-20240111141317596.png)

- [x] Configure **shell prompt**
      
  - Select return to the home menu and Select ***Command line interface*** :

    ![Screenshot from 2024-01-11 06-42-08](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/fe535b3d-b73f-41a9-8401-5618d456c0e6)


  - Then select ***shell prompt***  to appears "ITI_INTAKE44=>":

    ![image-20240111144553701](README.assets/image-20240111144553701.png)

- [x] Unset support of **Flash** .

  >U-Boot should be configured without support for flashing firmware to the flash memory of the target device. This implies that U-Boot will not  include functionality for writing or updating firmware to the  non-volatile memory (flash memory) on the board.

  -  **You can select (press Y)and un-select(press space) the options.**

    - Select  ***Environment*** :

![Screenshot from 2024-01-11 14-07-24](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/a69e3619-c8e8-4993-ab1b-047a1c204d88)

  - Un select  ***Environment in flash memory*** :

![Screenshot from 2024-01-11 14-07-44](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/f4e6cb93-755d-42d8-86c7-e3dd82f84da7)

- [x] Support **FAT file system** 

  ![image-20240111141423024](README.assets/image-20240111141423024.png)

- [x] Configure **Memory Commands** :

  ![image-20240111144919034](README.assets/image-20240111144919034.png)

  - Then Select ***md5sum and meminfo*** :

    ![image-20240111145154371](README.assets/image-20240111145154371.png)

  - [x] Configure **Device access commands** :

    ![](README.assets/image-20240111145311304.png)

    - Select ***lsblk***  option :

      ![image-20240111145402985](README.assets/image-20240111145402985.png) 

  - [x] Configure the **FAT** interface to **mmc** .

    >Configuring the FAT interface to work with MMC (MultiMediaCard) storage  devices in U-Boot involves specifying settings that allow U-Boot to  interact with MMC devices and access the FAT file system on a specific  partition

    -  Default name is mmc (you can change the name as you want) :

      ![image-20240111141510789](README.assets/image-20240111141510789.png)

  - [x] Configure the partition where the fat is store to **0 : 1** .

    >U-Boot needs to know the specific partition on the MMC device where the FAT file system is stored.

  - Select ***Device and partition for where to store the environment in FAT*** :

![Screenshot from 2024-01-11 14-54-59](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/fa0fcf86-624c-447e-80a8-4425ef852ecf)

    -  Then  **Write 0 : 1 ** :

![Screenshot from 2024-01-11 14-55-05](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/c07b0ffe-7791-4d11-8ed5-d9aaeb1e6070)

- [x] Configure The time delay to Auto boot

  - Select  ***Boot options*** 

![Screenshot from 2024-01-11 16-20-25](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/8e91cb4d-e20c-40bc-b32a-126744002fbe)

  - Select ***Auto boot*** option and select you preferred time delay ( ***5 seconds*** )

![Screenshot from 2024-01-11 16-22-56](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/b51924dd-fc2c-4ff6-9a15-bc815f805794)

![Screenshot from 2024-01-11 16-23-04](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/7944a760-d4fa-4519-9db1-909a41ec4005)

- [x] Configure ***bootcmd value*** to print default value on the screen when booting :

  - Select ***Boot options*** 

![Screenshot from 2024-01-11 16-22-48](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/923f581c-e254-4f02-b5cf-eaafd7affb58)

- Then Write which you want (in my case "from Anas to world, helloooooo")

![Screenshot from 2024-01-11 16-20-38](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/631c99c2-eb3e-49ac-bbb5-a2fa116c9d3b)

- Remove the written commands and rewrite which you want:

![Screenshot from 2024-01-11 16-25-52](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/dfbe5678-7f2a-43af-b6e7-06f61b584f00)

- **Congratulations ! , the all configurations are DONE **

##### 2.6. Build U-boot

```
make
```

> **You may face this error** : 
>
> fatal error: openssl/evp.h: No such file or directory
>  1184 | #  include <openssl/evp.h>
>
> - The OpenSSL library is missing on your Linux system, you should install it.![image-20240111152527451](README.assets/image-20240111152527451.png)

- [SOLVED] : run this command below

  ```
  sudo apt-get install libssl-dev
  ```

  ![image-20240111153055447](README.assets/image-20240111153055447.png)

- Successful Build :

  - You will see the building process like that :

![Screenshot from 2024-01-11 15-32-46](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/792ca035-3ece-432f-abf7-85c6dcf595e5)

  - Warnings messages may appears [ignore it] :

![Screenshot from 2024-01-11 15-34-18](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/c797b015-cc33-47db-a542-6cf72d96fe68)

##### 3. Test *U-boot* over *Vexpress-a9* machine using *Qemu* emulator

##### 3.1. Install Qemu 

If you don't install qemu before, you will face this error :

![image-20240111154621700](README.assets/image-20240111154621700.png) 

- [SOLVED] : run this command

  ```
  sudo apt install qemu-system-arm
  ```

  >The command  is used to install the QEMU emulator for the ARM architecture on a Debian-based Linux system, such as Ubuntu. 
  >
  >- `sudo`: This is a command that allows a permitted user to execute a command as the superuser or another user, as specified by the security policy.
  >- `apt`: This is the package management tool used on Debian-based systems to handle the installation, removal, and upgrading of software packages.
  >- `install`: This is an argument for `apt` indicating that you want to install a package.
  >- `qemu-system-arm`: This is the name of the package you want to install. It includes the QEMU emulator for the ARM architecture.
  >
  >

  The installation will be like that :

  ![image-20240111154824215](README.assets/image-20240111154824215.png)

- Make sure the *qemu-system-arm* installed correctly run this command:

  ```
  qemu-system-arm --version
  ```

  ![image-20240111155233425](README.assets/image-20240111155233425.png)

OR

```
dpkg -l | grep qemu-system-arm
```

>when you run this command, you are essentially asking the system to list all installed packages and then filter the results to show only those lines that contain the string **"qemu-system-arm."**
>
>If the QEMU emulator for ARM (`qemu-system-arm`) is installed, you should see one or more lines in the output that contain information about the installed package.

![image-20240111155402789](README.assets/image-20240111155402789.png)

##### 3.2. Run ***qemu*** with Emulated SD card

```
qemu-system-arm -M vexpress-a9 -m 128M -nographic -kernel path/u-boot -sd path/sd.img
```

>- `qemu-system-arm`: This is the command to run the QEMU emulator for the ARM architecture.
>
>- `-M vexpress-a9`: Specifies the machine type to emulate. In this case, it's the Versatile Express (vexpress) development board with an ARM Cortex-A9 processor.
>
>- `-m 128M`: Sets the amount of RAM available to the emulated system. In this case, it's 128 megabytes.
>
>- `-nographic`: Disables graphical output and redirects the console to the terminal. This is useful for text-based interaction with the emulated system.
>
>- `-kernel u-boot/u-boot`: Specifies the kernel image to be used. Here, it points to the U-Boot bootloader (`u-boot/u-boot`).
>
>- `-sd sd.img`: Attaches a virtual SD card to the emulated system. The SD card image is specified by the file `sd.img`.
>
>- Make sure you mounted the Virtual SD card
>
>  > **Note That** : you should created a virtual SD before , if you didn't create it click [Here](https://github.com/anaskhamees/Embedded_Linux/tree/main/EmbeddedLinuxTasks/Virtual_SD_Card) to create it.

```
qemu-system-arm -M vexpress-a9 -m 128M -nographic -kernel u-boot -sd sd.img
```

![Untitled Diagram55](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/52419c1a-b7ca-4fb5-b34d-254ae2469d9a)

##### 3.3. Booting 

![Screenshot from 2024-01-11 16-27-20](https://github.com/anaskhamees/Embedded_Linux/assets/52020047/33ee006e-a33d-432e-810d-4d51f818ed30)



**Congratulation ! , the U-boot is running :)**  

### 4. References 

1. https://docs.u-boot.org/en/latest/
2. https://github.com/FadyKhalil/EmbeddedLinux/tree/main/3-Uboot#vexpress-a9-qemu
3. https://github.com/PacktPublishing/Mastering-Embedded-Linux-Programming-Third-Edition
4. https://github.com/u-boot/u-boot
