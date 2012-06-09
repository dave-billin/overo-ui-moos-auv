#==============================================================================
# moos-ivp - The IvP Autonomy Software Suite
#
# A set of open source C++ modules and applications employing the MOOS software
# framework to supply simulation utilities and behavior-based autonomy for
# marine robotics applications
#
# bitbake recipe created by Dave Billin (david.billin@vandals.uidaho.edu)
#
#==============================================================================

require moos-ivp.inc

#------------------------------------------------------------------------
# For syntax and file conventions, refer to the OpenEmbedded manual at:
#   http://www.embeddedlinux.org.cn/OEManual/

# The revision ID of this bitbake recipe
PR="r6"

#----------------------------------------------------------------
# THE FOLLOWING VARIABLES AUTOMATICALLY GET SET BY BITBAKE:
#----------------------------------------------------------------
# PN    - Package name taken from the recipe file name.  All characters to 
#         the left of the  underscore in the file name are regarded as the
#         package name.
#
# PV    - Package version taken from the recipe file name.  The version is
#         determined from all characters in the file name between the first
#         underscore and the final '.bb' suffix.
#
# P     - The package name and version info separated by a hyphen.
#
# PF    - The package name, version and release separated by hyphens.
#------------------------------------------------------------------------


#-------------------------------------------------------------
# Define where source files should be fetched from
#-------------------------------------------------------------
SRC_URI = "svn://oceanai.mit.edu/svn/moos-ivp-aro/releases;module=moos-ivp-${PV};rev=HEAD;proto=https"


#-------------------------------------------------------------
# Add patch files to be applied to the downloaded source code
# prior to building
#-------------------------------------------------------------
SRC_URI += "file://fix-moos-ivp-cmakelists-headers.patch;apply=yes"


#-------------------------------------------------------------
# Define the working directory in the downloaded, unpacked
# source code (WORKDIR is the root of the downloaded source
# file packages)
#-------------------------------------------------------------
S = "${WORKDIR}/moos-ivp-${PV}/ivp/src"


#-----------------------------------------------------------
# Set build type.  Valid settings are:
#   "Debug"
#   "Release"
#   "RelWithDebugInfo"
#-----------------------------------------------------------
EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"

