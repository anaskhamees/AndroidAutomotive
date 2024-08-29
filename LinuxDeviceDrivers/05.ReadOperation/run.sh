#!/bin/bash
case $1 in 
insert)
make 
sudo insmod FileOperation.ko
sudo chmod 777 /dev/AnasDeviceFile

;;
remove)
sudo rmmod FileOperation
make clean
;;

test)
sudo rmmod FileOperation
make clean
sudo dmesg -c
sudo dmesg -c
make 
sudo insmod FileOperation.ko
sudo chmod 777 /dev/AnasDeviceFile
cat /dev/AnasDeviceFile
sudo dmesg 
;;

esac
