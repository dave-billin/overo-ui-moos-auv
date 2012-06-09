#==============================================================================
# moos
#
# A set of open source C++ modules and applications supplying a 'middle-ware'
# application framework and IPC mechanism using a central database.
#
# bitbake recipe created by Dave Billin (david.billin@vandals.uidaho.edu)
#
#
# NOTES:
#   By default, this recipe builds the following MOOS components:
#       - Core MOOS components (MOOSLib, MOOSGenLib, MOOSDB)
#       - Essential MOOS (pLogger, pAntler, pMOOSBridge, etc.)
#       - MOOSUtility library
#       - MOOSNav libarary
#       - uPlayback
#
#
#
#   MOOS components may be added or removed from the bitbake build by appending 
#   CMake define statements to the EXTRA_OECMAKE bitbake variable as follows:
#
#
#   EXAMPLE 1: 
#       To add MOOS instruments to the build, you would add the following lines
#       to the moos_xxxx.bb bitbake recipe at some point after the 
#       'require moos.inc' statement:
#
#       EXTRA_OECMAKE += "-DBUILD_INSTRUMENTS=ON \
#                         -DBUILD_COMMON_INSTRUMENTS=ON \
#                         -DBUILD_OCEAN_INSTRUMENTS=ON \
#                         -DBUILD_LAND_INSTRUMENTS=ON \
#                        "
#
#   EXAMPLE 2:
#       To remove uPlayback from the build, you would add the following lines
#       to the moos_xxxx.bb bitbake recipe at some point after the 
#       'require moos.inc' statement:
#       
#       EXTRA_OECMAKE += "-DBUILD_UPB=OFF"
#
#
#   EXAMPLE 3:
#       By default, the MOOS database is built such that it serves a web page.
#       To disable this feature, you would add the following line to the
#       moos_xxxx.bb bitbake recipe at some point after the 
#       'require moos.inc' statement:
#
#       EXTRA_OECMAKE += "-DDB_HAS_HTTP=OFF"
#
#==============================================================================
DESCRIPTION = "MOOS software framework and utilities"
HOMEPAGE = "http://www.robots.ox.ac.uk/~mobile/MOOS/wiki/pmwiki.php/Main/HomePage"
LICENSE = "GPL"
DEPENDS = "zlib"

# Revision number of this package: Increment whenever this file is modified
INC_PR = "r0"

# Use CMake to generate makefiles
inherit cmake

# Define values for CMake variables needed to configure the build
EXTRA_OECMAKE = "\
	-DFLTK_DIR=/usr/lib \
	-DFLTK_INCLUDE_DIR=/usr/include \
	-DPYTHON_INCLUDE_PATH=/usr/include/python2.5 \
	-DDB_HAS_HTTP=ON \
	-DBUILD_CORE_MOOS=ON \
	-DBUILD_ESSENTIAL_MOOS=ON \
	-DBUILD_UTILITY_LIBRARY=ON \
	-DBUILD_CONTROL_AND_NAV=ON \
    -DBUILD_NAVIGATION=ON \
	-DBUILD_UPB=ON \
	-DBUILD_INSTRUMENTS=OFF \
	-DBUILD_COMMON_INSTRUMENTS=OFF \
	-DBUILD_OCEAN_INSTRUMENTS=OFF \
	-DBUILD_LAND_INSTRUMENTS=OFF \
    -DBUILD_HELM=OFF \
    -DBUILD_IREMOTE=OFF \
	-DBUILD_EXAMPLES=OFF \
	-DBUILD_FLTKVW=OFF \
	-DBUILD_GRAPHICAL_TOOLS=OFF \
	-DBUILD_MATLAB_TOOLS=OFF \
	-DBUILD_NEWMAT=OFF \
	-DBUILD_OCEAN_SIM=OFF \
	-DBUILD_SIM_TOOLS=OFF \
	-DBUILD_TOOLS=ON \
	-DBUILD_UMS=OFF \
	-DBUILD_UMVS=OFF \
	-DMATLAB_DIR= \
	-DCMAKE_CXX_FLAGS=-fPIC \
"




