SUMMARY = "bitbake-layers recipe kernel append"
DESCRIPTION = "Recipe kernel append created by bitbake-layers"
LICENSE = "MIT"

PREFERRED_VERSION_linux-raspberrypi ?= "5.15"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://0003-patch-5.15.34-rt40.patch \
             file://can.cfg "

IMAGE_INSTALL:append = " kernel-modules"
#make sure it is activated followed driver in Kernel at compile time of yocto: Geschwister Schneider UG interfaces (search for it with /)
#make sure it is loaded followed driver from Kernel at runtime: # modprobe gs_usb
