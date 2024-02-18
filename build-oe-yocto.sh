#!/bin/bash
#usage: copy into conf folder and from there

populate_sdk()
{
	#usage: $ source build-oe-yocto.sh && populate_sdk
	#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
	cd ../../
	source sources/poky/oe-init-build-env build-raspi
	bitbake core-image-weston -c populate_sdk
	#bitbake core-image-full-cmdline -c populate_sdk
	#bitbake raspberrypi-general-image -c populate_sdk
	cd conf
}

uboot()
{
	#usage: $ source build-oe-yocto.sh && uboot
	#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
	cd ../../
	source sources/poky/oe-init-build-env build-raspi
	bitbake u-boot -c menuconfig
	cd conf
}

case $1 in
    add-layer )
        #calling1: . build-oe-yocto.sh add-layer conf/meta-mylayer
        #calling2: ./build-oe-yocto.sh add-layer conf/meta-mylayer
        cd ../../
        source sources/poky/oe-init-build-env build-raspi
        bitbake-layers create-layer $2 #../../meta-ke
        bitbake-layers add-layer $2
        cd conf
        ;;
	show-all-infos )
        cd ../../
        source sources/poky/oe-init-build-env build-raspi
        bitbake-layers show-layers
        bitbake-layers --help
        cd conf
        ;;
	cleanall )
		echo "Date: " `date`
		cd ../../
		source sources/poky/oe-init-build-env build-raspi
		bitbake core-image-sato -c cleanall
		bitbake core-image-weston -c cleanall
		cd conf
		;;
    start )
        # $ source build-oe-yocto.sh start
		#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
		echo "Date: " `date`
		cd ../../
		source sources/poky/oe-init-build-env build-raspi

		# CLI image
		#bitbake core-image-base

		#bitbake raspberrypi-general-image

		# GUI X11 image
		#bitbake core-image-sato

		# GUI Wayland/Weston
		#bitbake core-image-weston
		# core-image-full-cmdline

		bitbake core-image-minimal

		echo "Date: " `date`
		cd conf
		populate_sdk
		echo "Date: " `date`
		;;
    kernel )
        #export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
		echo "Date: " `date`
		cd ../../
		source sources/poky/oe-init-build-env build-raspi
		bitbake virtual/kernel -c menuconfig
		echo "Date: " `date`
		cd conf
		;;
	makeSDK )
		echo "Date: " `date`
		./../tmp/deploy/sdk/./wfdistro-glibc-x86_64-raspberrypi-general-image-cortexa53-raspberrypi3-64-toolchain-0.0.2.sh
		#for cross compiling:$ . /opt/wfdistro/0.0.2/environment-setup-cortexa53-wfdistro-linux
		echo "Date: " `date`
		;;
	flashing )
		#https://blog.lazy-evaluation.net/posts/linux/bmaptool.html
		#time sudo bmaptool copy ../tmp/deploy/images/raspberrypi3-64/core-image-sato-raspberrypi3-64.wic.bz2 /dev/sdb
		#bzcat ../tmp/deploy/images/raspberrypi3-64/raspberrypi-general-image-raspberrypi3-64.wic.bz2 | sudo dd of=/dev/sda bs=1M conv=fsync
		#bzcat ../tmp/deploy/images/raspberrypi3-64/core-image-sato-raspberrypi3-64.wic.bz2 | sudo dd of=/dev/sda bs=1M conv=fsync
		#bzcat ../tmp/deploy/images/raspberrypi3-64/core-image-base-raspberrypi3-64.wic.bz2 | sudo dd of=/dev/sda bs=1M conv=fsync
		bzcat ../tmp/deploy/images/raspberrypi3-64/core-image-minimal-raspberrypi3-64.wic.bz2 | sudo dd of=/dev/sda bs=1M conv=fsync
		;;
	remove_read_only_at_sdcard )
		#https://www.alphr.com/remove-write-protection-from-sd-card/
		sudo hdparm -r0 /dev/sdb
		;;
	reset )
		cd ../../
		source sources/poky/oe-init-build-env build-raspi
		bitbake core-image-sato -c cleanall
		rm -drf tmp-glibc
		rm -drf tmp
		rm -drf sstate-cache
		rm -drf downloads
		rm -drf cache
		cd conf
		;;
esac
