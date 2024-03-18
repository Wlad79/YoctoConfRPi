# Copyright (C) 2024 Wlad
# All rights reserved.
# Author: Waldemar Friesen
# Contact: Detmold


project(opencv4_cam0 CXX)

cmake_minimum_required(VERSION 3.22)

#set(CMAKE_C_COMPILER /opt/gcc-linaro-7.5.0-2019.12-x86_64_aarch64-linux-gnu/bin/aarch64-linux-gnu-gcc)

#set(CMAKE_CXX_COMPILER /opt/gcc-linaro-7.5.0-2019.12-x86_64_aarch64-linux-gnu/bin/aarch64-linux-gnu-g++)

set(cam_HDRS
        camera.h
	)

set(cam_SRCS
        camera.cpp
        )

add_library(cam_lib STATIC
        ${cam_HDRS}
        ${cam_SRCS}
)

find_package(PkgConfig REQUIRED)
pkg_search_module(PKG_OPENCV4 REQUIRED IMPORTED_TARGET opencv4)
include_directories(${PKG_OPENCV4_INCLUDE_DIRS})

#message ("PKG_OPENCV_LDFLAGS: ${PKG_OPENCV_LDFLAGS} " )

set(OpenCV_LIBS ${PKG_OPENCV4_LDFLAGS} )
#opencv_highgui opencv_imgcodecs opencv_imgproc opencv_features2d opencv_video opencv_videoio


include_directories(${OpenCV_INCLUDE_DIRS})
set(CMAKE_C_FLAGS "-mfloat-abi=hard" )

target_link_libraries(cam_lib stdc++ m ${OpenCV_LIBS} )

#link_libraries("-static")

add_executable(${PROJECT_NAME}
	main.cpp
)

target_link_libraries(${PROJECT_NAME} cam_lib stdc++ m ${OpenCV_LIBS} )
target_compile_features(${PROJECT_NAME} PUBLIC cxx_nullptr)

#install(FILES  opencv4_cam0 DESTINATION ${CMAKE_INSTALL_BINDIR})
