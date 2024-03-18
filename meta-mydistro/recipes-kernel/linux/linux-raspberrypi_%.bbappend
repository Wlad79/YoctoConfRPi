SUMMARY = "bitbake-layers recipe kernel append"
DESCRIPTION = "Recipe kernel append created by bitbake-layers"
LICENSE = "MIT"
# file://0003-patch-5.15.34-rt40.patch

#PREFERRED_VERSION_linux-raspberrypi ?= "5.15"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "  \
             file://interfaces.cfg "


