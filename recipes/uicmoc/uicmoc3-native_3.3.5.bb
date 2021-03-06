DESCRIPTION = "User Interface Generator and Meta Object Compiler (moc) for Qt(E) 3.x"
HOMEPAGE = "http://www.trolltech.com"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "GPL QPL"
DEPENDS = "qmake-native"
PR = "r4"

SRC_URI = "ftp://ftp.trolltech.com/qt/source/qt-embedded-free-${PV}.tar.bz2 \
           file://no-examples.patch \
           file://64bit-cleanup.patch"
S = "${WORKDIR}/qt-embedded-free-${PV}"

inherit native qmake_base qt3e

export QTDIR = "${S}"
export OE_QMAKE_LINK="${CXX}"
CXXFLAGS += " -DQWS"

QT_CONFIG_FLAGS = "-release -static -depths 8,16 -qt-zlib -no-nas-sound \
                   -no-sm -no-libjpeg -no-libmng -no-gif -no-xshape -no-xinerama \
                   -no-xcursor -no-xrandr -no-xrender -no-xft -no-tablet \
                   -no-xkb -no-dlopen-opengl -no-freetype -no-thread \
                   -no-nis -no-cups -prefix ${prefix} \
                   -xplatform ${OE_QMAKE_PLATFORM} \
                   -platform ${OE_QMAKE_PLATFORM}"

do_configure() {
    oe_qmake_mkspecs
    echo "yes" | ./configure ${QT_CONFIG_FLAGS}
}

do_compile() {
    oe_runmake symlinks  || die "Can't symlink include files"
    oe_runmake src-moc   || die "Building moc failed"
    oe_runmake sub-tools || die "Building tools failed"
}

do_install() {
        install -d ${D}${includedir}/qte3/include
        install -d ${D}${libdir}/qte3/lib
        install -d ${D}${bindir}/
        install -d ${D}${bindir}/

        install -m 0755 bin/moc ${D}${bindir}/moc3
        install -m 0755 bin/uic ${D}${bindir}/uic3
        install -m 0655 lib/*.a ${D}${libdir}/qte3/lib

        for f in include/*.h
        do
                install -m 0644 $f ${D}${includedir}/qte3/include/
        done
}

NATIVE_INSTALL_WORKS = "1"

SRC_URI[md5sum] = "022d7a3c572b554f3c47b12cae71a8a4"
SRC_URI[sha256sum] = "a97656796c0ef8e87dd83e6138bc406e31830d08f9b213e039d8be39ea65c8e4"
