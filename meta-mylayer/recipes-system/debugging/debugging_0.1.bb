SUMMARY = "bitbake-layers recipe debugging"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Example recipe created by bitbake-layers   *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

# Remote Debugging
EXTRA_IMAGE_FEATURES += " debug-tweaks tools-sdk tools-debug ssh-server-dropbear"
IMAGE_INSTALL:append = " gdbserver boost"
# comment for releas out, because worse performance
#IMAGE_INSTALL:append = " valgrind"

# https://hub.mender.io/t/how-to-configure-networking-using-systemd-in-yocto-project/1097
#!!!!!!!!!!!!!!!!!!!!!!disable this in order to get console messages back
#ENABLE_UART = "1"
