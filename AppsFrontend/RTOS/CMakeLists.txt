# https://cmake.org/cmake/help/latest/module/FindBoost.html

cmake_minimum_required(VERSION 3.12)
set(PROJECT_NAME RTOS_Program)
project(${PROJECT_NAME})

#set( GPS_LIBRARY /opt/wfdistro/0.0.2/sysroots/cortexa53-wfdistro-linux/usr/lib/libgps.so.29 )

FILE(GLOB SRC_FILES main.cpp)
add_executable(${PROJECT_NAME} ${SRC_FILES})

#PaspberryPi
target_include_directories(${PROJECT_NAME} PUBLIC
  /opt/wfdistro/0.0.2/sysroots/cortexa53-wfdistro-linux/usr/include/
)

#Jetson-Nano:
#target_include_directories(${PROJECT_NAME} PUBLIC
#  /opt/fwos-dist/0.1/sysroots/armv8a-fwos-linux/usr/include
#)

#install(TARGETS ${PROJECT_NAME} RUNTIME DESTINATION bin)
#target_link_libraries(${PROJECT_NAME} ${GPS_LIBRARY})
