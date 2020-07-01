# Flags

```shell
Variable           | lang | Usage
-------------------|------|---------
C_INCLUDE_PATH     | C    | colon separated list of include directory paths
CPLUS_INCLUDE_PATH | C++  | colon separated list of include directory paths
LIBRARY_PATH       | C/C++| colon separated compiling time static linking dirs
LD_RUN_PATH        | C/C++| colon separated compiling time dynamic linking dirs
LD_LIBRARY_PATH    | C/C++| colon separated run-time dynamic linking dirs
CPPFLAGS           | C/C++| prepocessor flags
CFLAGS             | C    | Compiling flags
CXXFLAGS           | C++  | Compiling flags
LDFLAGS            | C++  | Linking flags
```

# Autoconf

```shell
# AC_CHECK_LIB
AC_CHECK_LIB (library, function, [action-if-found], [action-if-not-found], [other-libraries])

AC_SEARCH_LIBS (function, search-libs, [action-if-found], [action-if-not-found], [other-libraries])
```

**Docs**

- [AC_CHECK_LIB](https://www.gnu.org/software/autoconf/manual/autoconf-2.65/html_node/Libraries.html)

