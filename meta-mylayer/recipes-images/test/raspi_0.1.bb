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

inherit core-image


IMAGE_INSTALL += " initing debugging can gps"
