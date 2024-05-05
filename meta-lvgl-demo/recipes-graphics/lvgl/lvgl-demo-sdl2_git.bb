SUMMARY = "LVGL demo application"
DESCRIPTION = "Recipe for a LVGL demo application based on the LVGL widgets demo with wayland interface"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

DEPENDS = "libsdl2"

SRC_URI = " \
    gitsm://git@github.com/Wlad79/lvgl-backends-demos;branch=main;protocol=https \
    file://lvgl-demo-sdl2.service \
"
SRCREV = "3f93033bbc8256c8a3e08b7efd60a966c8734829"

S = "${WORKDIR}/git"

inherit cmake systemd

SYSTEMD_SERVICE_${PN} = "lvgl-demo-sdl2.service"

FILES:${PN} += " \
    /lib/systemd/system/ \
    /usr/bin/ \
"

do_install:append() {
    install -d ${D}/lib/systemd/system/
    install -d ${D}/usr/bin/
    install -m 0644 ${WORKDIR}/lvgl-demo-sdl2.service ${D}/lib/systemd/system/
    install -m 0644 ${WORKDIR}/build/lvgl-demo-sdl2 ${D}/usr/bin/
}
