SUMMARY = "bitbake-layers recipe initing"
DESCRIPTION = "Recipe initing created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*     initing recipe created for wfdistro     *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

# https://docs.yoctoproject.org/dev-manual/init-manager.html?highlight=sysvinit#using-systemd
#INIT_MANAGER = "sysvinit"
#INIT_MANAGER = "mdev-busybox"
INIT_MANAGER = "systemd"
