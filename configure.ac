AC_INIT([icedtea-web],[1.6.2],[distro-pkg-dev@openjdk.java.net], [icedtea-web], [http://icedtea.classpath.org/wiki/IcedTea-Web])
AM_INIT_AUTOMAKE([1.9 tar-pax foreign])
AC_CONFIG_FILES([Makefile netx.manifest])
AM_MAINTAINER_MODE([enable])

# Older automake doesn't generate these correctly
abs_top_builddir=`pwd -P`
AC_SUBST(abs_top_builddir)
abs_top_srcdir=`dirname $0`
cd $abs_top_srcdir
abs_top_srcdir=`pwd`
cd $abs_top_builddir
AC_SUBST(abs_top_srcdir)

AC_CANONICAL_HOST
AC_PROG_CC
AC_PROG_CXX
IT_SET_ARCH_SETTINGS

IT_CP_SUPPORTS_REFLINK
IT_CAN_HARDLINK_TO_SOURCE_TREE

AC_MSG_CHECKING([whether to build documentation])
AC_ARG_ENABLE([docs],
	      [AS_HELP_STRING([--disable-docs],
	      		      [Disable generation of documentation])],
	      [ENABLE_DOCS="${enableval}"], [ENABLE_DOCS='yes'])
AM_CONDITIONAL([ENABLE_DOCS], [test x$ENABLE_DOCS = xyes])
AC_MSG_RESULT(${ENABLE_DOCS})

AC_MSG_CHECKING([whether to filter by whitelisting on directory name when processing, compiling and running reproducers])
AC_ARG_ENABLE([whitelist-processing],
	      [AS_HELP_STRING([--enable-whitelist-processing],
	      		      [Enable whitelist filter on directory name when processing, compiling and running reproducers. Otherwise filter on testcase name when running reproducers])],
	      [ENABLE_WHITELIST="${enableval}"], [ENABLE_WHITELIST='no'])
AM_CONDITIONAL([ENABLE_WHITELIST], [test x$ENABLE_WHITELIST = xyes])
AC_MSG_RESULT(${ENABLE_WHITELIST})

AC_PATH_PROG([BIN_BASH], [bash],, [/bin])
if test x"$BIN_BASH" = x ; then
    AC_MSG_ERROR([/bin/bash is used in runtime and for about generation. Dying sooner rather then later])
fi

IT_CHECK_WITH_GCJ
FIND_TOOL([ZIP], [zip])
FIND_JAVAC
FIND_JAR
FIND_ECJ_JAR
IT_CHECK_JAVA_VERSION
IT_FIND_JAVADOC
IT_FIND_KEYTOOL
IT_FIND_JARSIGNER
IT_FIND_PACK200

IT_SET_VERSION
IT_CHECK_XULRUNNER_VERSION

AC_CHECK_LIB(z, main,, [AC_MSG_ERROR("zlib not found - try installing zlib-devel")])
dnl Check for libX11 headers and libraries.
PKG_CHECK_MODULES(X11, x11,[X11_FOUND=yes],[X11_FOUND=no])
if test "x${X11_FOUND}" = xno
then
  AC_MSG_ERROR([Could not find x11 - \
  Try installing libX11-devel.])
fi
AC_SUBST(X11_CFLAGS)
AC_SUBST(X11_LIBS)

dnl PR46074 (gcc) - Missing java.net cookie code required by IcedTea plugin
dnl IT563 - NetX uses sun.security code
dnl IT605 - NetX depends on sun.misc HexDumpEncoder or sun.security.util.HexDumpEncoder dnl IT570 - NetX depends on sun.applet.AppletViewPanel
dnl IT571 - NetX depends on com.sun.net.ssl.internal.ssl.X509ExtendedTrustManager.java
dnl IT573 - Plugin depends on sun.awt,X11.XEmbeddedFrame.java
dnl IT575 - Plugin depends on com.sun/jndi.toolkit.url.UrlUtil
dnl IT576 - Plugin depends on sun.applet.AppletImageRef
dnl IT578 - Remove need for patching AppletPanel for Plugin/Webstart
IT_CHECK_FOR_CLASS(JAVA_UTIL_JAR_PACK200, [java.util.jar.Pack200])
IT_CHECK_FOR_CLASS(JAVA_NET_COOKIEMANAGER, [java.net.CookieManager])
IT_CHECK_FOR_CLASS(JAVA_NET_HTTPCOOKIE, [java.net.HttpCookie])
IT_CHECK_FOR_CLASS(JAVA_NET_COOKIEHANDLER, [java.net.CookieHandler])
IT_CHECK_FOR_CLASS(SUN_SECURITY_PROVIDER_X509FACTORY, [sun.security.provider.X509Factory])
IT_CHECK_FOR_CLASS(SUN_SECURITY_UTIL_SECURITYCONSTANTS, [sun.security.util.SecurityConstants])
IT_CHECK_FOR_CLASS(SUN_SECURITY_UTIL_HOSTNAMECHECKER, [sun.security.util.HostnameChecker])
IT_CHECK_FOR_CLASS(SUN_SECURITY_X509_X500NAME, [sun.security.x509.X500Name])
IT_CHECK_FOR_HEXDUMPENCODER
IT_CHECK_FOR_CLASS(SUN_SECURITY_VALIDATOR_VALIDATOREXCEPTION, [sun.security.validator.ValidatorException])
IT_CHECK_FOR_CLASS(COM_SUN_NET_SSL_INTERNAL_SSL_X509EXTENDEDTRUSTMANAGER,
	[com.sun.net.ssl.internal.ssl.X509ExtendedTrustManager])
IT_CHECK_FOR_CLASS(SUN_NET_WWW_PROTOCOL_JAR_URLJARFILE, [sun.net.www.protocol.jar.URLJarFile])
IT_CHECK_FOR_CLASS(SUN_NET_WWW_PROTOCOL_JAR_URLJARFILECALLBACK, [sun.net.www.protocol.jar.URLJarFileCallBack])
IT_CHECK_FOR_CLASS(SUN_AWT_X11_XEMBEDDEDFRAME, [sun.awt.X11.XEmbeddedFrame])
IT_CHECK_FOR_CLASS(COM_SUN_JNDI_TOOLKIT_URL_URLUTIL, [com.sun.jndi.toolkit.url.UrlUtil])
IT_CHECK_FOR_CLASS(SUN_APPLET_APPLETIMAGEREF, [sun.applet.AppletImageRef])
IT_CHECK_FOR_SUN_APPLET_ACCESSIBILITY
IT_CHECK_GLIB_VERSION
IT_CHECK_XULRUNNER_MIMEDESCRIPTION_CONSTCHAR
IT_CHECK_XULRUNNER_REQUIRES_C11

#
# Find optional depedencies
#

AC_CHECK_PROGS([XSLTPROC],[xsltproc],[], [])
# browser to be linked/tested
# Example: IT_FIND_BROWSER([browser-name],[variable-to-store-path],[default-run-command-if-different-from-the-browser-name])
IT_FIND_BROWSER([firefox],[FIREFOX])
IT_FIND_BROWSER([chrome],[CHROME],[google-chrome])
IT_FIND_BROWSER([chromium],[CHROMIUM],[chromium-browser])
IT_FIND_BROWSER([opera],[OPERA])
IT_FIND_BROWSER([midori],[MIDORI])
IT_FIND_BROWSER([epiphany],[EPIPHANY])
IT_SET_GLOBAL_BROWSERTESTS_BEHAVIOUR

AM_CONDITIONAL([WITH_XSLTPROC], [test x"$XSLTPROC" != x ])
IT_FIND_OPTIONAL_JAR([rhino], RHINO,
    [/usr/share/java/js.jar /usr/share/rhino-1.6/lib/js.jar])
IT_FIND_OPTIONAL_JAR([junit], JUNIT,
    [/usr/share/java/junit4.jar /usr/share/junit-4/lib/junit.jar])
IT_FIND_OPTIONAL_JAR([hamcrest], HAMCREST,
    [/usr/share/java/hamcrest/all.jar /usr/share/java/hamcrest-core.jar])
IT_FIND_OPTIONAL_JAR([jacoco], JACOCO,
    [/usr/share/java/jacoco/org.jacoco.core.jar])
IT_FIND_OPTIONAL_JAR([asm], ASM,
    [/usr/share/java/objectweb-asm4/asm-all.jar /usr/share/java/objectweb-asm4/asm-all-4.0.jar /usr/share/java/objectweb-asm/asm-all.jar])

IT_CHECK_FOR_TAGSOUP

AC_CONFIG_FILES([jrunscript], [chmod u+x jrunscript])
AC_CONFIG_FILES([build.properties])

AC_OUTPUT
