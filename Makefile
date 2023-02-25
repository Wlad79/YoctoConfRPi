# copy that file into repo root folder
install_kirkstone_ubuntu:
	sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev zstd liblz4-tool

UbuntuDependanciesInstall:
	#absolete (1.8):
	#sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat libsdl1.2-dev xterm

	#Dunfell (3.1.19):
	sudo apt install gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev

	#Kirkstone (4.0.999):
	#sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev zstd liblz4-tool

FedoraDependanciesInstall:
	sudo dnf install gawk make wget tar bzip2 gzip python3 unzip perl patch diffutils diffstat git cpp gcc gcc-c++ glibc-devel texinfo chrpath ccache perl-Data-Dumper perl-Text-ParseWords perl-Thread-Queue perl-bignum socat python3-pexpect findutils which file cpio python python3-pip xz python3-GitPython python3-jinja2 SDL-devel xterm rpcgen mesa-libGL-devel perl-FindBin perl-File-Compare perl-File-Copy perl-locale zstd lz4

	sudo dnf install make python3-pip which
	sudo pip3 install sphinx sphinx_rtd_theme pyyaml

setup_yocto:
	#https://meta-raspberrypi.readthedocs.io/en/kirkstone/readme.html#quick-start
	git clone -b kirkstone git://git.yoctoproject.org/poky
	git clone -b kirkstone git://git.openembedded.org/meta-openembedded
	git clone -b kirkstone https://github.com/agherzan/meta-raspberrypi.git

start_bitbake:
	# https://askubuntu.com/questions/99651/apt-get-warning-no-support-for-locale-en-us-utf8
	#sudo dpkg-reconfigure locales
	#sudo update-locale LANG=en_US.UTF-8
	bash -c "source sources/poky/oe-init-build-env rpi-build && bitbake core-image-sato"

reset:
	rm -drf rpi-build/cache rpi-build/downloads rpi-build/sstate-cache rpi-build/tmp rpi-build/bitbake-cookerdaemon.log

flash1:
	#https://blog.lazy-evaluation.net/posts/linux/bmaptool.html
	time sudo bmaptool copy /run/media/waldi/4C47-78FE/raspberrypi3-64/core-image-sato-raspberrypi3-64-20221105144918.rootfs.wic.bz2 /dev/sdb

flash2:
	#https://blog.lazy-evaluation.net/posts/linux/bmaptool.html
	time ( bzcat /run/media/waldi/4C47-78FE/raspberrypi3-64/core-image-sato-raspberrypi3-64-20221105144918.rootfs.wic.bz2 | sudo dd of=/dev/sdb bs=1M conv=fsync )
