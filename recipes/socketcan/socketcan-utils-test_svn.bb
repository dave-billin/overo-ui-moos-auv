DESCRIPTION = "Socketcan user space utilities and test apps"
HOMEPAGE = "http://developer.berlios.de/projects/socketcan/"
SECTION = "console/utils"
LICENSE = "GPL"
RCONFLICTS_${PN} = "canutils"

SRCREV = "917"
PV = "0.0+svnr${SRCPV}"
PR = "r1"

SRC_URI = "svn://svn.berlios.de/socketcan;module=trunk;proto=svn \
           file://autotoolize.patch;striplevel=0 \
          "

S = "${WORKDIR}/trunk"

inherit autotools

