SECTION = "console/utils"
DESCRIPTION = "Kernel oops and error message decoder."
LICENSE = "GPLv2"
DEPENDS = "binutils"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/utils/kernel/ksymoops/v2.4/ksymoops-${PV}.tar.gz \
	   file://flags.patch"

S = "${WORKDIR}/ksymoops-${PV}"

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ksymoops ${D}${bindir}/ksymoops
	install -d ${D}${mandir}/man8
	install -m 0755 ksymoops.8 ${D}${mandir}/man8/ksymoops.8
}

SRC_URI[md5sum] = "d281a723e67137e8d2185b75acc92721"
SRC_URI[sha256sum] = "e16b50097e0ef258c76c73c7794cbd8b45f77c6e93b2087657bdb67e2e9ff56b" 

#SRC_URI = "${KERNELORG_MIRROR}/pub/linux/utils/kernel/ksymoops/v2.4/ksymoops-${PV}.tar.bz2 \
#	   file://flags.patch"

#SRC_URI[md5sum] = "4a8249e182a5dbc75e566d162e9f3314"
#SRC_URI[sha256sum] = "52d3546062681e91460013acba6150ece9139b7c2787c2f3213ad0770b72a6b4"

