#==============================================================================
# Operating system image for the Gumstix OVERO processor on the Front Seat 
# Driver (FSD) module in the University of Idaho MOOS-AUV
#
# Created by Dave Billin
#
# To build this image, run:
#   bitbake moos-auv-bsd-image
#
# To deploy the image on a microSD card, you must also build u-boot and x-load:
#   bitbake moos-auv-u-boot
#   bitbake x-load
#==============================================================================

# Increment PR every time this recipe changes!
PR = 1

inherit image

DEPENDS = "task-base"



IMAGE_EXTRA_INSTALL ?= ""



AUDIO_INSTALL = " \
#  alsa-utils \
#  alsa-utils-aplay \
#  alsa-utils-amixer \
#  angstrom-zeroconf-audio \
 "



BASE_INSTALL = " \
  task-base-extended \
 "



FIRMWARE_INSTALL = " \
#  linux-firmware \
  libertas-sd-firmware \
  rt73-firmware \
  zd1211-firmware \
 "



GLES_INSTALL = " \
#  libgles-omap3 \
 "



DEVTOOLS_INSTALL = " \
  cmake \
  emacs \
  gdb \
  gdbserver \
  git \
  lua5.1 \
  nano \
  octave \
  perl \
  python \
  task-sdk-native \
"



PYTHON_DEV_INSTALL = " \
  python \
  python-argparse \
  python-docutils \
  python-numeric \
  python-numpy \
  python-pyalsa \
  python-pyserial \
  python-webpy \
  python-pysqlite2 \
  python-pyusb \
  python-pyxml \
"  



TOOLS_INSTALL = " \
  bash \
  bzip2 \
  ckermit \
  devmem2 \
  dhcp-client \
  dosfstools \
  fbgrab \
  fbset \
  fbset-modes \
  i2c-tools \
  ksymoops \
  mkfs-jffs2 \
  mtd-utils \
  mysql \
  openssh-misc \
  openssh-scp \
  openssh-ssh \
  omap3-writeprom \
  procps \
  socat \
  strace \
  sudo \
  syslog-ng \
  task-proper-tools \
  u-boot-utils \
  kernel-modules \
  setserial \
  screen \
 "



GPS_UTILS_INSTALL = " \
  chrony \
  gpsd \
  gps-utils \
  ntp \
  ntpdate \
 "



IMAGE_INSTALL += " \
  ${BASE_INSTALL} \
  ${AUDIO_INSTALL} \
  ${FIRMWARE_INSTALL} \
  ${GLES_INSTALL} \
  ${IMAGE_EXTRA_INSTALL} \
  ${DEVTOOLS_INSTALL} \
  ${PYTHON_DEV_INSTALL} \
  ${TOOLS_INSTALL} \
  ${GPS_UTILS_INSTALL} \
 "



SRC_URI = " \
  file://bashrc \
  file://etc-hosts \
  file://etc-network-interfaces \
  file://PicardFirstBoot \
  file://Startup.moos \
  file://Yelsub_BringUpNetwork \
  file://Yelsub_SetSerialPortPermissions \
  file://Yelsub_StartMOOSApps \
"



ROOTFS_POSTPROCESS_COMMAND += " \
    rm ${IMAGE_ROOTFS}/etc/rc5.d/S20apmd; \
    rm ${IMAGE_ROOTFS}/etc/rc5.d/S23bluetooth; \
    cp ${WORKDIR}/bashrc ${IMAGE_ROOTFS}/etc/skel/.bashrc; \
    cp ${WORKDIR}/bashrc ${IMAGE_ROOTFS}/home/root/.bashrc; \
    echo 'source /home/root/.bashrc' >> ${IMAGE_ROOTFS}/home/root/.profile; \
    rm ${IMAGE_ROOTFS}/etc/hostname; \
    echo 'picard' >> ${IMAGE_ROOTFS}/etc/hostname; \
    chmod 664 ${IMAGE_ROOTFS}/etc/hostname; \
    mkdir ${IMAGE_ROOTFS}/usr/bin/MOOSApps; \
    chmod ugo+rwx ${IMAGE_ROOTFS}/usr/bin/MOOSApps; \
    rm ${IMAGE_ROOTFS}/etc/hosts; \
    cp ${WORKDIR}/etc-hosts ${IMAGE_ROOTFS}/etc/hosts; \
    rm ${IMAGE_ROOTFS}/etc/network/interfaces; \
    cp ${WORKDIR}/etc-network-interfaces ${IMAGE_ROOTFS}/etc/network/interfaces; \
    cp ${WORKDIR}/Startup.moos ${IMAGE_ROOTFS}/usr/share/Startup.moos; \
    chmod ugo+r ${IMAGE_ROOTFS}/usr/share/Startup.moos; \
    cp ${WORKDIR}/PicardFirstBoot ${IMAGE_ROOTFS}/home/root/.PicardFirstBoot; \
    chmod ug+rx ${IMAGE_ROOTFS}/home/root/.PicardFirstBoot; \
    echo '/home/root/.PicardFirstBoot' >> ${IMAGE_ROOTFS}/home/root/.profile; \
    cp ${WORKDIR}/Yelsub_BringUpNetwork ${IMAGE_ROOTFS}/etc/Yelsub_BringUpNetwork; \
    chmod 774 ${IMAGE_ROOTFS}/etc/Yelsub_BringUpNetwork; \
    cp ${WORKDIR}/Yelsub_BringUpNetwork ${IMAGE_ROOTFS}/etc/rc5.d/S101_BringUpNetwork; \
    chmod 774 ${IMAGE_ROOTFS}/etc/rc5.d/S101_BringUpNetwork; \
    cp ${WORKDIR}/Yelsub_StartMOOSApps ${IMAGE_ROOTFS}/etc/Yelsub_StartMOOSApps; \
    chmod 774 ${IMAGE_ROOTFS}/etc/Yelsub_StartMOOSApps; \
    cp ${WORKDIR}/Yelsub_StartMOOSApps ${IMAGE_ROOTFS}/etc/rc5.d/S102_StartMOOSApps; \
    chmod 774 ${IMAGE_ROOTFS}/etc/rc5.d/S102_StartMOOSApps; \
    cp ${WORKDIR}/Yelsub_SetSerialPortPermissions ${IMAGE_ROOTFS}/etc/Yelsub_SetSerialPortPermissions; \
    chmod u=rwx,go=r ${IMAGE_ROOTFS}/etc/Yelsub_SetSerialPortPermissions; \
"

