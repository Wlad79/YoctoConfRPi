SUMMARY = "LVGL demo application"
DESCRIPTION = "Recipe for a LVGL demo application based on the LVGL widgets demo with wayland interface"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

SRC_URI = " \
    gitsm://git@github.com/lvgl/lv_port_linux_frame_buffer;branch=release/v9.0;protocol=https \
    file://lvgl-demo-fb.service \
"
SRCREV = "da6e60640e0ac4e04b7d258633fb67ab94739b27"

#DEPENDS += "wayland libxkbcommon"
#SRC_URI = " \
#    gitsm://git@github.com/phytec/demo-lvgl;branch=main;protocol=https \
#    file://lvgl-demo-wl.service \
#"
#SRCREV = "be5ee134a6473ca79efd17e212f88a3046796f79"

S = "${WORKDIR}/git"

inherit cmake systemd

SYSTEMD_SERVICE_${PN} = "lvgl-demo-fb.service"

#FILES_${PN} += "${systemd_unitdir}"

FILES:${PN} += " \
    /lib/systemd/system/ \
"

do_install:append() {
    #install -Dm 0644 ${WORKDIR}/lvgl-demo-wl.service ${D}${systemd_system_unitdir}/lvgl-demo-wl.service
    install -d ${D}/lib/systemd/system/
    install -m 0644 ${WORKDIR}/lvgl-demo-fb.service ${D}/lib/systemd/system/
    #mv ${D}${sysconfdir}/systemd/lvgl-demo-wl.service
}
