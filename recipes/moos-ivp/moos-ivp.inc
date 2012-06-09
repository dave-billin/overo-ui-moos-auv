#==============================================================================
# moos-ivp - The IvP Autonomy Software Suite
#
# A set of open source C++ modules and applications developed at MIT that 
# supplies simulation utilities and behavior-based autonomy for marine robotics 
# systems based on the MOOS application framework
#
# bitbake recipe created by Dave Billin (david.billin@vandals.uidaho.edu)
#
#==============================================================================

DESCRIPTION = "MOOS-IvP autonomy software for marine robotics"
HOMEPAGE = "www.moos-ivp.org"
SECTION = "console/scientific"
DEPENDS = "zlib fltk moos-for-ivp"
RDEPENDS += "libstdc++"
PRIORITY = "optional"
LICENSE = "GPL2"

# Revision number of this package: Increment whenever this file is modified
INC_PR = "r0"

# Use CMake to generate makefiles
inherit cmake

# Define values for CMake variables needed to configure the build
EXTRA_OECMAKE = "\
	-DFLTK_DIR=/usr/lib \
	-DFLTK_INCLUDE_DIR=/usr/include \
	-DPYTHON_INCLUDE_PATH=/usr/include/python2.5 \
	-DIVP_BUILD_GUI_CODE=OFF \
    -DIVP_LIB_DIRECTORY=${WORKDIR}/moos-ivp-${PV}/lib \
    -DIVP_BIN_DIRECTORY=${WORKDIR}/moos-ivp-${PV}/bin \
"

