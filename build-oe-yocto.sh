#!/bin/bash

start()
{
	#usage: copy into conf folder and from there:
	# $ source openEmbedded.sh && start
	#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
	cd ../../
	source sources/poky/oe-init-build-env build-rpi
	#bitbake core-image-minimal
	bitbake core-image-full-cmdline
	#bitbake core-image-sato # error with cc1plus
	#bitbake core-image-weston
	#bitbake core-image-full-cmdline
	cd conf
	populate_sdk
}

populate_sdk()
{
	#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
	cd ../../
	source sources/poky/oe-init-build-env build-rpi
	#bitbake core-image-minimal -c populate_sdk
	bitbake core-image-full-cmdline -c populate_sdk
	cd conf
}

reset()
{
	cd ../../
	rm -drf build-rpi/tmp-glibc
	rm -drf build-rpi/tmp
	rm -drf build-rpi/sstate-cache
	rm -drf build-rpi/downloads
	rm -drf build-rpi/cache
	cd build-rpi/conf
}

uboot()
{
	#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
	cd ../../
	source sources/poky/oe-init-build-env build-rpi
	bitbake u-boot -c menuconfig
	cd conf
}


kernel()
{
	#export PATH=$(pwd)/../../sources/poky/bitbake/bin:$PATH
	cd ../../
	source sources/poky/oe-init-build-env build-rpi
	bitbake virtual/kernel -c menuconfig
	cd conf
}

make_sdk()
{
	./../tmp/deploy/sdk/poky-glibc-x86_64-core-image-minimal-cortexa53-raspberrypi3-64-toolchain-4.0.12.sh
	#for cross compiling:$ . /opt/poky/4.0.12/environment-setup-cortexa53-poky-linux
}

flash()
{
	#https://blog.lazy-evaluation.net/posts/linux/bmaptool.html
	time sudo bmaptool copy ../tmp/deploy/images/raspberrypi3-64/core-image-sato-raspberrypi3-64.wic.bz2 /dev/sdb
}

