# YoctoConfRaspberryPi

Conf folder from Yocto project for RaspberryPi.

Copy folders meta-mydistro meta-godot and meta-lvgl into folder sources.

in order to remove followed error in install step:
```
scons: *** Do not know how to make File target `install' (/home/wlad/Public/GitHub/Godot/Yocto/build-raspi/tmp/work/cortexa53-wfdistro-linux/godot/3.1+gitAUTOINC+320f49f204-r0/git/install).  Stop.
```

it must be created the missed folder install in git folder !