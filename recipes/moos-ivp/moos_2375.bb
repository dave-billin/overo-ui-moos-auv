#==============================================================================
# moos - The MOOS (Mission-Oriented Operating Suite)
#
# A set of open source C++ modules and applications developed at Oxford 
# University that supply a 'middle-ware' application framework and IPC 
# mechanism for robotics applications
#
# bitbake recipe created by Dave Billin (david.billin@vandals.uidaho.edu)
#
#==============================================================================

require moos.inc

#========================================================================
# For syntax and file conventions, refer to the OpenEmbedded manual at:
#   http://www.embeddedlinux.org.cn/OEManual/
#========================================================================


#----------------------------------------------------------------
# Set the revision ID of this bitbake recipe - this ID should be 
# incremented whenever the recipe changes to ensure bitbake will
# check it for changes and rebuild if necessary
#----------------------------------------------------------------
PR="r2"


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
SRC_URI = "svn://login2.robots.ox.ac.uk/MOOS;module=trunk;rev=${PV};proto=svn"


#-------------------------------------------------------------
# Add patch files to be applied to the downloaded source code
# prior to building
#-------------------------------------------------------------
SRC_URI += "file://fix-moosserialport-guard.patch;apply=yes"


#-------------------------------------------------------------
# Define the working directory in the downloaded, unpacked
# source code (WORKDIR is the root of the downloaded source
# file packages)
#-------------------------------------------------------------
S = "${WORKDIR}/trunk"


#-----------------------------------------------------------
# Set the CMAKE build type.  Valid settings are:
#   "Debug"
#   "Release"
#   "RelWithDebugInfo"
#-----------------------------------------------------------
EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"

