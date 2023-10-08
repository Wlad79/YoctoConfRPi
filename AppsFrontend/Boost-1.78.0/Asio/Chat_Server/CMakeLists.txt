cmake_minimum_required(VERSION 3.12)
set(PROJECT_NAME Boost_Asio_Chat_Server)
#set(CMAKE_TOOLCHAIN_FILE ${CMAKE_CURRENT_SOURCE_DIR}/../../RPiToolchainCmake/toolchain-rpi.cmake) # must be before project()
project(${PROJECT_NAME})

find_package(Boost)


include_directories( ${Boost_INCLUDE_DIRS}
                     ${INCLUDE_DIR}
                     ${BUILD_DIR}
                     ${PROJECT_SOURCE_DIR} )

FILE(GLOB SRC_FILES main.cpp)
add_executable(${PROJECT_NAME} ${SRC_FILES})

#install(TARGETS ${PROJECT_NAME} RUNTIME DESTINATION bin)
target_link_libraries(${PROJECT_NAME} ${Boost_LIBRARIES})

