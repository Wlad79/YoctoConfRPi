SUMMARY = "bitbake-layers recipe kernel append"
DESCRIPTION = "Recipe kernel append created by bitbake-layers"
LICENSE = "MIT"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://0003-patch-5.15.34-rt40.patch \
             file://can.cfg "

IMAGE_INSTALL:append = " kernel-modules"
