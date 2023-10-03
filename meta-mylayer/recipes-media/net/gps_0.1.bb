SUMMARY = "bitbake-layers recipe GPS"
DESCRIPTION = "Recipe GPS created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*       gps recipe created for wfdistro       *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

IMAGE_INSTALL += " crda gpsd gps-utils libgps gpsd-gpsctl"
