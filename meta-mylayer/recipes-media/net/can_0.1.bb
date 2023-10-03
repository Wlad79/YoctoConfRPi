SUMMARY = "bitbake-layers recipe can interfaces"
DESCRIPTION = "Recipe can created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*       can recipe created for wfdistro       *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

#make sure it is activated followed driver in Kernel at compile time of yocto: Geschwister Schneider UG interfaces (search for it with /)
#make sure it is loaded followed driver from Kernel at runtime: # modprobe gs_usb

#CAN over USB: dumpcan
IMAGE_INSTALL += " can-utils"

# see docs in my google drive for more (C-api and maintanance) or https://github.com/INNO-MAKER/usb2can
