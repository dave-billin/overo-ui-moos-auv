require moos-auv-u-boot.inc
PR = "r0"

SRC_URI_overo = "git://www.sakoman.com/git/u-boot.git;branch=omap3-v2011.09;protocol=git"
SRCREV_overo = "0f331e606c80166c1bfe5cac40dfc0616708f31b"
PV_overo = "2011.09+${PR}+gitr${SRCREV}"

S = "${WORKDIR}/git"


