SUMMARY = "libcamera demo application"
DESCRIPTION = "Recipe for a rpicam-apps demo application based on the libcamera demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

DEPENDS += " meson libcamera pkgconfig cmake libexif libpng"
RDEPENDS:${PN} += " meson libcamera pkgconfig cmake libexif libpng"

SRC_URI = "file://001-fix-depandancies-in-meson.patch"

SRC_URI = " \
    gitsm://git@github.com/raspberrypi/rpicam-apps.git;branch=main;protocol=https \
"
SRCREV = "f8b05c47894cad8803ba1d85fc1d7c8296367096"

S = "${WORKDIR}/git"

inherit meson pkgconfig

FILES:${PN} += " \
    ${sysconfdir}/systemd/lvgl-demo-wl.service \
"

do_install:append() {
    #install -Dm 0644 ${WORKDIR}/lvgl-demo-wl.service ${D}${systemd_system_unitdir}/lvgl-demo-wl.service
    install -d ${D}${sysconfdir}/systemd/lvgl-demo-wl.service
    install -m 0644 ${WORKDIR}/lvgl-demo-wl.service ${D}${sysconfdir}/systemd/
}
