#!/bin/bash
#usage: copy into conf folder and from there

populate_sdk()
{
	#usage: $ source build-oe-yocto.sh && populate_sdk
	#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
	cd ../../
	source sources/poky/oe-init-build-env build-raspi
	#bitbake core-image-minimal -c populate_sdk
	#bitbake core-image-full-cmdline -c populate_sdk
	bitbake raspberrypi-general-image -c populate_sdk
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
    start )
        # $ source build-oe-yocto.sh start
		#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
		echo "Date: " `date`
		cd ../../
		source sources/poky/oe-init-build-env build-raspi
		pwd
		cp -vr "conf/meta-mydistro/recipes-kernel/linux/files/0003-patch-5.15.34-rt40.patch" ../sources/meta-raspberrypi/recipes-kernel/linux/files
		cp -vr "conf/meta-mydistro/recipes-kernel/linux/files/can.cfg" ../sources/meta-raspberrypi/recipes-kernel/linux/files
		#bitbake core-image-minimal
		bitbake raspberrypi-general-image
		#bitbake core-image-sato # error with cc1plus
		#bitbake core-image-weston
		#bitbake core-image-full-cmdline
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
		./../tmp/deploy/sdk/poky-glibc-x86_64-core-image-minimal-cortexa53-raspberrypi3-64-toolchain-4.0.12.sh
		#for cross compiling:$ . /opt/poky/4.0.12/environment-setup-cortexa53-poky-linux
		echo "Date: " `date`
		;;
	flashing )
		#https://blog.lazy-evaluation.net/posts/linux/bmaptool.html
		#time sudo bmaptool copy ../tmp/deploy/images/raspberrypi3-64/core-image-sato-raspberrypi3-64.wic.bz2 /dev/sdb
		bzcat ../tmp/deploy/images/raspberrypi3-64/core-image-full-cmdline-raspberrypi3-64.wic.bz2 | sudo dd of=/dev/sda bs=1M conv=fsync
		;;
	remove_read_only_at_sdcard )
		#https://www.alphr.com/remove-write-protection-from-sd-card/
		sudo hdparm -r0 /dev/sdb
		;;
	reset )
		cd ../../
		source sources/poky/oe-init-build-env build-raspi
		bitbake raspberrypi-general-image -c cleanall
		rm -drfv tmp-glibc
		rm -drfv tmp
		rm -drfv sstate-cache
		rm -drfv downloads
		rm -drfv cache
		rm -v sources/meta-raspberrypi/recipes-kernel/linux/files/0003-patch-5.15.34-rt40.patch
		rm -v sources/meta-raspberrypi/recipes-kernel/linux/files/can.cfg
		cd conf
		;;
esac

# cd conf
