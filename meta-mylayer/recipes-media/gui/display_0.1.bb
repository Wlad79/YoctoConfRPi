SUMMARY = "bitbake-layers recipe display"
DESCRIPTION = "Recipe display created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*    display recipe created for wfdistro      *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

#Grafik:
##IMAGE_INSTALL:append = " weston"
##DISTRO_FEATURES += " x11 wayland" # sdl2 libsdl2-dev
#IMAGE_INSTALL:append = " cmake"
#IMAGE_FEATURES += "ssh-server-dropbear"
#DISTRO_FEATURES += " sdl sdl2 opengl vulkan"
#DISTRO_FEATURES += " gles2"
#MACHINE_FEATURES:append = " vc4graphics"
##TOOLCHAIN_HOST_TASK += " nativesdk-cmake"
##TOOLCHAIN_TARGET_TASK += " libgl-mesa-dev "
##REQUIRED_DISTRO_FEATURES += " opengl "
##MACHINE_FEATURES += " vc4graphics "
