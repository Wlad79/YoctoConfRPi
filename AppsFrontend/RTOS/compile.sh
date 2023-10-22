# https://stackoverflow.com/questions/71254360/how-to-get-gps-h-into-a-yocto-recipe-build

#. /opt/wfdistro/0.0.2/environment-setup-cortexa53-wfdistro-linux
. /opt/wfdistro/0.0.3/environment-setup-cortexa53-wfdistro-linux

rm -drf build-aarch64
mkdir build-aarch64

cd build-aarch64
cmake ..
make
