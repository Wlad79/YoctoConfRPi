#!/bin/sh

# https://qengineering.eu/install-gstreamer-1.18-on-raspberry-pi-4.html
# instal in yocto autovideosink or use wayland !
	gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw, width=1280, height=720, framerate=30/1 ! videoconvert ! videoscale ! clockoverlay time-format="%D %H:%M:%S" ! video/x-raw, width=640, height=360 ! autovideosink
