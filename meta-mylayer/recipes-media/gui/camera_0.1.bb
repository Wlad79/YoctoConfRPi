SUMMARY = "bitbake-layers recipe camera"
DESCRIPTION = "Recipe camera created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*     camera recipe created for wfdistro      *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

#Bildverarbeitung
#CORE_IMAGE_EXTRA_INSTALL += " opencv opencv-samples"
#VIDEO_CAMERA = "1"
