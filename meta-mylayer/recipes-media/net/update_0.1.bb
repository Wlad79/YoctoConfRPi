SUMMARY = "bitbake-layers recipe GPS"
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

## https://linux-dev.gitbook.io/linux-os-build-.../how-to-install-apt-get-to-the-yocto-project-image
## https://subscription.packtpub.com/book/iot-and-hardware/9781788399210/1/ch01lvl1sec24/setting-up-a-package-feed
## https://stackoverflow.com/questions/57452581/what-is-equivalent-of-apt-get-or-yum-in-any-yocto-system
#PACKAGE_CLASSES = "package_deb"
#PACKAGE_FEED_URIS = "http://some_domain_or_ip:5678"
#EXTRA_IMAGE_FEATURES += " package-management "
#IMAGE_INSTALL:append = " python3-ansible"
