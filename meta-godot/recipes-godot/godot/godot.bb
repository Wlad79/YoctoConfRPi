SUMMARY = "Godot Engine - Free and Open Source 2D and 3D Engine"
DESCRIPTION = " Godot provides a huge set of common tools, so \
you can just focus on making your game without reinventing the wheel. \
\
Godot is completely free and open-source under the very permissive MIT license. \
No strings attached, no royalties, nothing. Your game  chlinux.org/is yours, down to the \
last line of engine code. "
HOMEPAGE = "https://godotengine.org"

LICENSE = "MIT"
#LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4af6e265c363b35652a1cecf6cf233ab"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

PV = "4.2+git${SRCPV}"

#SRC_URI[sha256sum] = "a8af2f5fcd819c40ffd2f39abe4aadcb2b541c1264ce3b69de4a898ab353356d"

# file://0001-gdnative-fix-GGC-bug.patch
# file://0001-libvpx-replace-platform.machine-by-env-arch-in-scons.patch
# https://github.com/godotengine/godot
SRCREV = "320f49f204cfbf9b480fe62aaa7718afb74920a5"
SRC_URI = " \
           gitsm://git@github.com/godotengine/godot;branch=4.2;protocol=https \
            \
           "

S = "${WORKDIR}/git"

# Some dependencies are listed at:
# https://docs.godotengine.org/en/latest/development/compiling/compiling_for_x11.html#requirements
DEPENDS = " virtual/libx11 libxcursor libxrandr libxi libxinerama freetype alsa-lib pulseaudio yasm virtual/libgl virtual/libgles2 virtual/egl openssl "
RDEPENDS_${PN} = " virtual/libx11 libxcursor libxrandr libxi libxinerama freetype alsa-lib pulseaudio yasm virtual/libgl virtual/libgles2 virtual/egl openssl "

# Optional dependencies that can be built-in or from OE build
DEPENDS += " libpng libogg libtheora libvorbis libvpx libwebp libwebsockets \
        libpcre2 zlib \
        "
RDEPENDS_${PN} = " libpng libogg libtheora libvorbis libvpx libwebp \
        libwebsockets libpcre2 zlib \
        "

# Scons does not pass full include path for freetype, gcc throws the error:
#  ./scene/resources/dynamic_font.h:41:10: fatal error: ft2build.h: No such file or directory
#  #include <ft2build.h>
CFLAGS += " -I${WORKDIR}/recipe-sysroot/usr/include/freetype2 "
CXXFLAGS += " -I${WORKDIR}/recipe-sysroot/usr/include/freetype2 "

# Scons invokes ld instead of g++ for linking, thus -Wl, has to be removed
#LDFLAGS = "-O1 --hash-style=gnu --as-needed"
LD = "${HOST_PREFIX}g++ ${TOOLCHAIN_OPTIONS} ${HOST_LD_ARCH}"

# https://github.com/godotengine/godot/blob/master/SConstruct
# Godot scons accepts a file with custom build options
EXTRA_OESCONS += " verbose=1 profile=${S}/oe.py "
export PACKAGE_ARCH
do_compile:prepend(){
    echo "
arch = '${PACKAGE_ARCH}'
platform = 'x11'
tools = 'no'
target = 'release'
builtin_freetype = 'False'
builtin_libogg = 'False'
builtin_libpng = 'False'
builtin_libtheora = 'False'
builtin_libvorbis = 'False'
builtin_libvpx = 'False'
builtin_libwebp = 'False'
builtin_libwebsockets = 'True'
builtin_pcre2 = 'True'
builtin_zlib = 'False'
CXX = '${CXX}'
CC = '${CC}'
LINK = '${LD}'
CCFLAGS = '${CCFLAGS}'
CFLAGS = '${CFLAGS}'
CXXFLAGS = '${CXXFLAGS}'
LINKFLAGS = '${LDFLAGS}'" > ${S}/oe.py
}

inherit scons pkgconfig

FILES:${PN} += " \
    /git/bin/godot \
"

# https://docs.godotengine.org/en/latest/development/compiling/compiling_for_x11.html#doc-compiling-for-x11
# "If all goes well, the resulting binary executable will be placed in the “bin”
# subdirectory. This executable file contains the whole engine and runs without
# any dependencies."
# Therefore we have to override default scons do_install
do_install(){
    install -d ${D}/usr/bin/godot
    install -m 0755 ${WORKDIR}/git/bin/godot.x11.opt.cortexa76 ${D}${bindir}/godot
    #ln -sf ${bindir}/$(ls ${S}/bin) ${D}${bindir}/godot
}
