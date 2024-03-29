SUMMARY = "bitbake-layers recipe image raspi"
DESCRIPTION = "Recipe raspi created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*   image recipe raspi created for wfdistro   *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

#IMAGE_INSTALL:remove = "initing debugging gps can"

inherit core-image

# https://docs.yoctoproject.org/dev-manual/init-manager.html?highlight=sysvinit#using-systemd
#INIT_MANAGER = "sysvinit"
#INIT_MANAGER = "mdev-busybox"
INIT_MANAGER = "systemd"

#Updating:
## https://linux-dev.gitbook.io/linux-os-build-.../how-to-install-apt-get-to-the-yocto-project-image
## https://subscription.packtpub.com/book/iot-and-hardware/9781788399210/1/ch01lvl1sec24/setting-up-a-package-feed
## https://stackoverflow.com/questions/57452581/what-is-equivalent-of-apt-get-or-yum-in-any-yocto-system
#PACKAGE_CLASSES = "package_deb"
#PACKAGE_FEED_URIS = "http://some_domain_or_ip:5678"
#EXTRA_IMAGE_FEATURES += " package-management "
#IMAGE_INSTALL:append = " python3-ansible"

#CAN over USB: dumpcan
IMAGE_INSTALL += " can-utils"
# see docs in my google drive for more (C-api and maintanance) or https://github.com/INNO-MAKER/usb2can
#make sure it is activated followed driver in Kernel at compile time of yocto: Geschwister Schneider UG interfaces (search for it with /)
#make sure it is loaded followed driver from Kernel at runtime: # modprobe gs_usb

#GPS:
IMAGE_INSTALL += " crda gpsd gps-utils libgps gpsd-gpsctl"

# Remote Debugging
EXTRA_IMAGE_FEATURES += " debug-tweaks tools-sdk tools-debug ssh-server-dropbear"
IMAGE_INSTALL += " gdbserver boost"
# comment for releas out, because worse performance
#IMAGE_INSTALL:append = " valgrind"

# https://hub.mender.io/t/how-to-configure-networking-using-systemd-in-yocto-project/1097
#!!!!!!!!!!!!!!!!!!!!!!disable this in order to get console messages back
#ENABLE_UART = "1"

#Grafik:
MACHINE_FEATURES:append = " vc4graphics"
##IMAGE_INSTALL:append = " weston"
##DISTRO_FEATURES += " x11 wayland" # sdl2 libsdl2-dev
#IMAGE_INSTALL:append = " cmake"
#IMAGE_FEATURES += "ssh-server-dropbear"
#DISTRO_FEATURES += " sdl opengl vulkan"
##TOOLCHAIN_HOST_TASK += " nativesdk-cmake"
##TOOLCHAIN_TARGET_TASK += " libgl-mesa-dev "
##REQUIRED_DISTRO_FEATURES += " opengl "


# Adding a user
inherit extrausers
EXTRA_USERS_PARAMS = "usermod -P '' root;"
#https://stackoverflow.com/questions/37788111/meta-qt-yocto-layer-change-default-qt-platform-qt-qpa-platform
#EXTRA_USERS_PARAMS = "export QT_QPA_PLATFORM=eglfs;"

#Bildverarbeitung
#CORE_IMAGE_EXTRA_INSTALL += " opencv opencv-samples"
#VIDEO_CAMERA = "1"

#Add Gstreamer
IMAGE_INSTALL:append = " gstreamer1.0"
IMAGE_INSTALL:append = " gstreamer1.0-plugins-base"
IMAGE_INSTALL:append = " gstreamer1.0-plugins-good"
#pkgconfig cmake
IMAGE_INSTALL:append = " gstreamer1.0-plugins-bad"
#RDEPENDS += " pkgconfig cmake"
#DEPENDS += " pkgconfig cmake"
# https://stackoverflow.com/questions/66492990/yocto-gstreamer1-0-plugins-bad-1-16-3-bbdo-configure-in-yocto
#PACKAGECONFIG_remove_pn-qtmultimedia = "gstreamer"
#PACKAGECONFIG:append_pn-qtmultimedia = " opengl"
IMAGE_INSTALL:append = " gstreamer1.0-rtsp-server"
IMAGE_INSTALL:append = " libcamera"

#Logo splash:
IMAGE_INSTALL:append = " psplash"
IMAGE_FEATURES:append = " splash"
#IMAGE_INSTALL:append = " splash-app"
#IMAGE_INSTALL:append = " l4t-graphics-demos"
RASPBERRYPI_DISPLAY = "1"
MACHINE_FEATURES += " screen"
CORE_IMAGE_EXTRA_INSTALL += " wayland weston"
#IMAGE_FEATURES:append = " x11"
