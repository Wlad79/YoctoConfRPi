SUMMARY = "bitbake-layers recipe"
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

# https://docs.yoctoproject.org/dev-manual/init-manager.html?highlight=sysvinit#using-systemd
#INIT_MANAGER = "sysvinit"
#INIT_MANAGER = "mdev-busybox"
INIT_MANAGER = "systemd"
