SUMMARY = "LVGL demo application"
DESCRIPTION = "Recipe for a LVGL demo application based on the LVGL widgets demo with sdl2 interface"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

DEPENDS = "libsdl2"

SRC_URI = " \
    gitsm://git@github.com/Wlad79/lvgl-backends-demos;branch=main;protocol=https \
    file://lvgl-demo-sdl2.service \
"
SRCREV = "3cbd269621e7276d649b972907e01825b192c4c4"

S = "${WORKDIR}/git"

inherit cmake systemd

SYSTEMD_SERVICE_${PN} = "lvgl-demo-sdl2.service"

FILES:${PN} += " \
    /lib/systemd/system/ \
    /usr/bin/ \
"

#do_configure:prepend(){
#	cp ${WORKDIR}/build/lvgl/lvgl.pc ${S}/build/lvgl.pc
#}

do_compile:append(){
	cp ${WORKDIR}/build/lvgl/lvgl.pc ${WORKDIR}/build/lvgl.pc
}

do_install:append() {
    #cp ${WORKDIR}/build/lvgl/lvgl.pc  ${WORKDIR}/build/lvgl.pc
    install -d ${D}/lib/systemd/system/
    install -d ${D}/usr/bin/
    install -m 0644 ${WORKDIR}/lvgl-demo-sdl2.service ${D}/lib/systemd/system/
    install -m 0744 ${WORKDIR}/build/lvgl-demo-sdl2 ${D}/usr/bin/
    #so geht es hier nicht: bb.plain("Waldemar: do_install:append()");
}


python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*    in lvgl-demo-sdl2: before do_install     *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_install
