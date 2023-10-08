echo "see: https://cmake.org/cmake/help/latest/module/FindBoost.html"

. /opt/wfdistro/0.0.2/environment-setup-cortexa53-wfdistro-linux

rm -drf build-aarch64
mkdir build-aarch64

cd build-aarch64
cmake ..
make
