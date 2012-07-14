#==============================================================================
# Operating system image for the Gumstix OVERO processor on the Back Seat 
# Driver (BSD) module in the University of Idaho MOOS-AUV
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
  iptables \
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


WEBSERVER_APPS_INSTALL = " \
  lighttpd \
  lighttpd-module-fastcgi \
  php \
  php-cgi \
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
  ${WEBSERVER_APPS_INSTALL} \
 "



## Here we notify bitbake of files to be referenced in subsequent
## build scripts
SRC_URI = " \
  file://bashrc \
  file://ConfigureWorfNAT \
  file://DasBootConfig \
  file://etc-hosts \
  file://index.php.default \
  file://lighttpd.conf.default \
  file://Startup.moos \
  file://Yelsub_BringUpNetwork \
  file://Yelsub_GenerateNetConfig \
  file://Yelsub_SetSerialPortPermissions \
  file://Yelsub_StartMOOSApps \
"



## This is the root directory for web pages served by the http server
LIGHTTPD_DOCUMENT_ROOT = "${IMAGE_ROOTFS}/www/pages"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

#ROOTFS_POSTPROCESS_COMMAND += '${@base_conditional("DISTRO_TYPE", "release", "zap_root_password; ", "",d)}'



## Here we execute commands on the root file system before it gets compressed.
## This is where all of the startup scripts, configuration files, etc. get
## put in place so that they will already be configured when Linux boots on
## the Gumstix module.
ROOTFS_POSTPROCESS_COMMAND += " \
  rm ${IMAGE_ROOTFS}/etc/rc5.d/S20apmd; \
  rm ${IMAGE_ROOTFS}/etc/rc5.d/S23bluetooth; \
  cp ${WORKDIR}/bashrc ${IMAGE_ROOTFS}/home/root/.bashrc; \
  cp ${WORKDIR}/bashrc ${IMAGE_ROOTFS}/etc/skel/.bashrc; \
  echo 'source /home/root/.bashrc' >> ${IMAGE_ROOTFS}/home/root/.profile; \
  rm ${IMAGE_ROOTFS}/etc/hostname; \
  echo 'worf' >> ${IMAGE_ROOTFS}/etc/hostname; \
  chmod ugo+r ${IMAGE_ROOTFS}/etc/hostname; \
  rm ${IMAGE_ROOTFS}/etc/hosts; \
  cp ${WORKDIR}/etc-hosts ${IMAGE_ROOTFS}/etc/hosts; \
  echo 'iptable_nat' >> ${IMAGE_ROOTFS}/etc/modutils/netfilter; \
  cp ${WORKDIR}/ConfigureWorfNAT ${IMAGE_ROOTFS}/etc/ConfigureWorfNAT; \
  chmod ugo+rx ${IMAGE_ROOTFS}/etc/ConfigureWorfNAT; \
  mkdir ${IMAGE_ROOTFS}/usr/bin/MOOSApps; \
  chmod ugo+rwx ${IMAGE_ROOTFS}/usr/bin/MOOSApps; \
  cp ${WORKDIR}/Startup.moos ${IMAGE_ROOTFS}/usr/share/Startup.moos; \
  chmod ugo+r ${IMAGE_ROOTFS}/usr/share/Startup.moos; \
  cp ${WORKDIR}/DasBootConfig ${IMAGE_ROOTFS}/home/root/.DasBootConfig; \
  chmod ugo+rx ${IMAGE_ROOTFS}/home/root/.DasBootConfig; \
  echo '/home/root/.DasBootConfig' >> ${IMAGE_ROOTFS}/home/root/.profile; \
  cp ${WORKDIR}/Yelsub_RemapUART2 ${IMAGE_ROOTFS}/etc/Yelsub_RemapUART2; \
  chmod u=rwx,go=r ${IMAGE_ROOTFS}/etc/Yelsub_RemapUART2; \
  cp ${WORKDIR}/Yelsub_GenerateNetConfig ${IMAGE_ROOTFS}/etc/Yelsub_GenerateNetConfig; \
  chmod u=rwx,go=r ${IMAGE_ROOTFS}/etc/Yelsub_GenerateNetConfig; \
  cp ${WORKDIR}/Yelsub_BringUpNetwork ${IMAGE_ROOTFS}/etc/Yelsub_BringUpNetwork; \
  chmod u=rwx,go=r ${IMAGE_ROOTFS}/etc/Yelsub_BringUpNetwork; \
  cp ${WORKDIR}/Yelsub_StartMOOSApps ${IMAGE_ROOTFS}/etc/Yelsub_StartMOOSApps; \
  chmod u=rwx,go=r ${IMAGE_ROOTFS}/etc/Yelsub_StartMOOSApps; \
  cp ${WORKDIR}/Yelsub_SetSerialPortPermissions ${IMAGE_ROOTFS}/etc/Yelsub_SetSerialPortPermissions; \
  chmod u=rwx,go=r ${IMAGE_ROOTFS}/etc/Yelsub_SetSerialPortPermissions; \
  cp ${WORKDIR}/lighttpd.conf.default ${IMAGE_ROOTFS}/etc/lighttpd.conf; \
  chmod 755 ${IMAGE_ROOTFS}/etc/lighttpd.conf; \
  rm -rf ${LIGHTTPD_DOCUMENT_ROOT}/*; \
  cp ${WORKDIR}/index.php.default ${LIGHTTPD_DOCUMENT_ROOT}/index.php; \
  chmod 644 ${LIGHTTPD_DOCUMENT_ROOT}/index.php; \
  echo 'net.ipv4.ip_forward = 1' >> ${IMAGE_ROOTFS}/etc/sysctl.conf; \
"

