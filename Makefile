install_kirkstone_ubuntu:
	sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev zstd liblz4-tool

setup_yocto:
	#https://meta-raspberrypi.readthedocs.io/en/kirkstone/readme.html#quick-start
	git clone -b kirkstone git://git.yoctoproject.org/poky
	git clone -b kirkstone git://git.openembedded.org/meta-openembedded
	git clone -b kirkstone https://github.com/agherzan/meta-raspberrypi.git

start_bitbake:
	bash -c "source ../../sources/poky/oe-init-build-env rpi-build && bitbake core-image-sato"

reset:
	rm -drf rpi-build/cache rpi-build/downloads rpi-build/sstate-cache rpi-build/tmp rpi-build/bitbake-cookerdaemon.log

flash1:
	#https://blog.lazy-evaluation.net/posts/linux/bmaptool.html
	time sudo bmaptool copy /run/media/waldi/4C47-78FE/raspberrypi3-64/core-image-sato-raspberrypi3-64-20221105144918.rootfs.wic.bz2 /dev/sdb

flash2:
	#https://blog.lazy-evaluation.net/posts/linux/bmaptool.html
	time ( bzcat /run/media/waldi/4C47-78FE/raspberrypi3-64/core-image-sato-raspberrypi3-64-20221105144918.rootfs.wic.bz2 | sudo dd of=/dev/sdb bs=1M conv=fsync )
