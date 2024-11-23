### rpi

- clone rpi bootloaders 

  ```
  
  ```

  

- cd /home/anas/uboot

- make clean

- install rpi tool chain :

  ```
  sudo apt install gcc-arm-linux-gnueabihf
  ```

  

- ```
  export CROSS_COMPILE=arm-linux-gnueabihf-
  export ARCH=arm
  make rpi_3_32b_defconfig
  make
  ```

  
