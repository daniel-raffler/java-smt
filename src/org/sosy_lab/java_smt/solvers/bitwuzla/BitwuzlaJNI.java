// This file is part of JavaSMT,
// an API wrapper for a collection of SMT solvers:
// https://github.com/sosy-lab/java-smt
//
// SPDX-FileCopyrightText: 2020 Dirk Beyer <https://www.sosy-lab.org>
//
// SPDX-License-Identifier: Apache-2.0

/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.sosy_lab.java_smt.solvers.bitwuzla;

public class BitwuzlaJNI {
  public static final native long new_BitwuzlaTermArray(int jarg1);

  public static final native void delete_BitwuzlaTermArray(long jarg1);

  public static final native long BitwuzlaTermArray_getitem(long jarg1, int jarg2);

  public static final native void BitwuzlaTermArray_setitem(long jarg1, int jarg2, long jarg3);

  public static final native long new_BitwuzlaSortArray(int jarg1);

  public static final native void delete_BitwuzlaSortArray(long jarg1);

  public static final native long BitwuzlaSortArray_getitem(long jarg1, int jarg2);

  public static final native void BitwuzlaSortArray_setitem(long jarg1, int jarg2, long jarg3);

  public static final native long new_uint64_tArray(int jarg1);

  public static final native void delete_uint64_tArray(long jarg1);

  public static final native long uint64_tArray_getitem(long jarg1, int jarg2);

  public static final native void uint64_tArray_setitem(long jarg1, int jarg2, long jarg3);

  public static final native int true_get();

  public static final native int false_get();

  public static final native int __bool_true_false_are_defined_get();

  public static final native int _STDIO_H_get();

  public static final native int _FEATURES_H_get();

  public static final native int _DEFAULT_SOURCE_get();

  public static final native int __GLIBC_USE_ISOC2X_get();

  public static final native int __USE_POSIX_IMPLICITLY_get();

  public static final native int _POSIX_SOURCE_get();

  public static final native int _POSIX_C_SOURCE_get();

  public static final native int __USE_POSIX_get();

  public static final native int __USE_POSIX2_get();

  public static final native int __USE_POSIX199309_get();

  public static final native int __USE_POSIX199506_get();

  public static final native int __USE_XOPEN2K_get();

  public static final native int __USE_ISOC95_get();

  public static final native int __USE_ISOC99_get();

  public static final native int __USE_XOPEN2K8_get();

  public static final native int _ATFILE_SOURCE_get();

  public static final native int __WORDSIZE_get();

  public static final native int __WORDSIZE32_SIZE_ULONG_get();

  public static final native int __WORDSIZE32_PTRDIFF_LONG_get();

  public static final native int __WORDSIZE_TIME64_COMPAT32_get();

  public static final native int __TIMESIZE_get();

  public static final native int __USE_MISC_get();

  public static final native int __USE_ATFILE_get();

  public static final native int __USE_FORTIFY_LEVEL_get();

  public static final native int __GLIBC_USE_DEPRECATED_GETS_get();

  public static final native int __GLIBC_USE_DEPRECATED_SCANF_get();

  public static final native int _STDC_PREDEF_H_get();

  public static final native int __STDC_IEC_559___get();

  public static final native int __STDC_IEC_60559_BFP___get();

  public static final native int __STDC_IEC_559_COMPLEX___get();

  public static final native int __STDC_IEC_60559_COMPLEX___get();

  public static final native int __STDC_ISO_10646___get();

  public static final native int __GNU_LIBRARY___get();

  public static final native int __GLIBC___get();

  public static final native int __GLIBC_MINOR___get();

  public static final native int _SYS_CDEFS_H_get();

  public static final native int __glibc_c99_flexarr_available_get();

  public static final native int __LDOUBLE_REDIRECTS_TO_FLOAT128_ABI_get();

  public static final native int __HAVE_GENERIC_SELECTION_get();

  public static final native int __GLIBC_USE_LIB_EXT2_get();

  public static final native int __GLIBC_USE_IEC_60559_BFP_EXT_get();

  public static final native int __GLIBC_USE_IEC_60559_BFP_EXT_C2X_get();

  public static final native int __GLIBC_USE_IEC_60559_EXT_get();

  public static final native int __GLIBC_USE_IEC_60559_FUNCS_EXT_get();

  public static final native int __GLIBC_USE_IEC_60559_FUNCS_EXT_C2X_get();

  public static final native int __GLIBC_USE_IEC_60559_TYPES_EXT_get();

  public static final native int _BITS_TYPES_H_get();

  public static final native int _BITS_TYPESIZES_H_get();

  public static final native int __RLIM_T_MATCHES_RLIM64_T_get();

  public static final native int __STATFS_MATCHES_STATFS64_get();

  public static final native int __KERNEL_OLD_TIMEVAL_MATCHES_TIMEVAL64_get();

  public static final native int __FD_SETSIZE_get();

  public static final native int _BITS_TIME64_H_get();

  public static final native long new___fpos64_t();

  public static final native void delete___fpos64_t(long jarg1);

  public static final native int ____FILE_defined_get();

  public static final native int __FILE_defined_get();

  public static final native int __struct_FILE_defined_get();

  public static final native void _IO_FILE__flags_set(long jarg1, _IO_FILE jarg1_, int jarg2);

  public static final native int _IO_FILE__flags_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_read_ptr_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_read_ptr_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_read_end_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_read_end_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_read_base_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_read_base_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_write_base_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_write_base_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_write_ptr_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_write_ptr_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_write_end_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_write_end_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_buf_base_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_buf_base_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_buf_end_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_buf_end_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_save_base_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_save_base_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_backup_base_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_backup_base_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__IO_save_end_set(
      long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__IO_save_end_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__markers_set(long jarg1, _IO_FILE jarg1_, long jarg2);

  public static final native long _IO_FILE__markers_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__chain_set(
      long jarg1, _IO_FILE jarg1_, long jarg2, _IO_FILE jarg2_);

  public static final native long _IO_FILE__chain_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__fileno_set(long jarg1, _IO_FILE jarg1_, int jarg2);

  public static final native int _IO_FILE__fileno_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__flags2_set(long jarg1, _IO_FILE jarg1_, int jarg2);

  public static final native int _IO_FILE__flags2_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__old_offset_set(long jarg1, _IO_FILE jarg1_, int jarg2);

  public static final native int _IO_FILE__old_offset_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__cur_column_set(long jarg1, _IO_FILE jarg1_, int jarg2);

  public static final native int _IO_FILE__cur_column_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__vtable_offset_set(
      long jarg1, _IO_FILE jarg1_, byte jarg2);

  public static final native byte _IO_FILE__vtable_offset_get(long jarg1, _IO_FILE jarg1_);

  // public static final native void _IO_FILE__shortbuf_set(long jarg1, _IO_FILE jarg1_, String
  // jarg2);

  public static final native String _IO_FILE__shortbuf_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__lock_set(long jarg1, _IO_FILE jarg1_, long jarg2);

  public static final native long _IO_FILE__lock_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__offset_set(long jarg1, _IO_FILE jarg1_, long jarg2);

  public static final native long _IO_FILE__offset_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__codecvt_set(long jarg1, _IO_FILE jarg1_, long jarg2);

  public static final native long _IO_FILE__codecvt_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__wide_data_set(long jarg1, _IO_FILE jarg1_, long jarg2);

  public static final native long _IO_FILE__wide_data_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__freeres_list_set(
      long jarg1, _IO_FILE jarg1_, long jarg2, _IO_FILE jarg2_);

  public static final native long _IO_FILE__freeres_list_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__freeres_buf_set(
      long jarg1, _IO_FILE jarg1_, long jarg2);

  public static final native long _IO_FILE__freeres_buf_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE___pad5_set(long jarg1, _IO_FILE jarg1_, long jarg2);

  public static final native long _IO_FILE___pad5_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__mode_set(long jarg1, _IO_FILE jarg1_, int jarg2);

  public static final native int _IO_FILE__mode_get(long jarg1, _IO_FILE jarg1_);

  public static final native void _IO_FILE__unused2_set(long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native String _IO_FILE__unused2_get(long jarg1, _IO_FILE jarg1_);

  public static final native long new__IO_FILE();

  public static final native void delete__IO_FILE(long jarg1);

  public static final native int _IO_EOF_SEEN_get();

  public static final native int _IO_ERR_SEEN_get();

  public static final native int _IO_USER_LOCK_get();

  public static final native int _IOFBF_get();

  public static final native int _IOLBF_get();

  public static final native int _IONBF_get();

  public static final native int BUFSIZ_get();

  public static final native int EOF_get();

  public static final native int SEEK_SET_get();

  public static final native int SEEK_CUR_get();

  public static final native int SEEK_END_get();

  public static final native String P_tmpdir_get();

  public static final native int _BITS_STDIO_LIM_H_get();

  public static final native int L_tmpnam_get();

  public static final native int TMP_MAX_get();

  public static final native int FILENAME_MAX_get();

  public static final native int L_ctermid_get();

  public static final native int FOPEN_MAX_get();

  public static final native void stdin_set(long jarg1, _IO_FILE jarg1_);

  public static final native long stdin_get();

  public static final native void stdout_set(long jarg1, _IO_FILE jarg1_);

  public static final native long stdout_get();

  public static final native void stderr_set(long jarg1, _IO_FILE jarg1_);

  public static final native long stderr_get();

  public static final native int remove(String jarg1);

  public static final native int rename(String jarg1, String jarg2);

  public static final native int renameat(int jarg1, String jarg2, int jarg3, String jarg4);

  public static final native int fclose(long jarg1, _IO_FILE jarg1_);

  public static final native long tmpfile();

  public static final native String tmpnam(String jarg1);

  public static final native String tmpnam_r(String jarg1);

  public static final native String tempnam(String jarg1, String jarg2);

  public static final native int fflush(long jarg1, _IO_FILE jarg1_);

  public static final native int fflush_unlocked(long jarg1, _IO_FILE jarg1_);

  public static final native long fopen(String jarg1, String jarg2);

  public static final native long freopen(String jarg1, String jarg2, long jarg3, _IO_FILE jarg3_);

  public static final native long fdopen(int jarg1, String jarg2);

  public static final native long fmemopen(long jarg1, long jarg2, String jarg3);

  public static final native long open_memstream(long jarg1, long jarg2);

  public static final native void setbuf(long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native int setvbuf(
      long jarg1, _IO_FILE jarg1_, String jarg2, int jarg3, long jarg4);

  public static final native void setbuffer(long jarg1, _IO_FILE jarg1_, String jarg2, long jarg3);

  public static final native void setlinebuf(long jarg1, _IO_FILE jarg1_);

  public static final native int fprintf(long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native int printf(String jarg1);

  public static final native int sprintf(String jarg1, String jarg2);

  public static final native int vfprintf(long jarg1, _IO_FILE jarg1_, String jarg2, long jarg3);

  public static final native int vprintf(String jarg1, long jarg2);

  public static final native int vsprintf(String jarg1, String jarg2, long jarg3);

  public static final native int snprintf(String jarg1, long jarg2, String jarg3);

  public static final native int vsnprintf(String jarg1, long jarg2, String jarg3, long jarg4);

  public static final native int vdprintf(int jarg1, String jarg2, long jarg3);

  public static final native int dprintf(int jarg1, String jarg2);

  public static final native int fscanf(long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native int scanf(String jarg1);

  public static final native int sscanf(String jarg1, String jarg2);

  public static final native int __HAVE_FLOAT128_get();

  public static final native int __HAVE_DISTINCT_FLOAT128_get();

  public static final native int __HAVE_FLOAT64X_get();

  public static final native int __HAVE_FLOAT64X_LONG_DOUBLE_get();

  public static final native int __HAVE_FLOAT16_get();

  public static final native int __HAVE_FLOAT32_get();

  public static final native int __HAVE_FLOAT64_get();

  public static final native int __HAVE_FLOAT32X_get();

  public static final native int __HAVE_FLOAT128X_get();

  public static final native int __HAVE_DISTINCT_FLOAT16_get();

  public static final native int __HAVE_DISTINCT_FLOAT32_get();

  public static final native int __HAVE_DISTINCT_FLOAT64_get();

  public static final native int __HAVE_DISTINCT_FLOAT32X_get();

  public static final native int __HAVE_DISTINCT_FLOAT64X_get();

  public static final native int __HAVE_DISTINCT_FLOAT128X_get();

  public static final native int __HAVE_FLOATN_NOT_TYPEDEF_get();

  public static final native int __isoc99_fscanf(long jarg1, _IO_FILE jarg1_, String jarg2);

  public static final native int __isoc99_scanf(String jarg1);

  public static final native int __isoc99_sscanf(String jarg1, String jarg2);

  public static final native int vfscanf(long jarg1, _IO_FILE jarg1_, String jarg2, long jarg3);

  public static final native int vscanf(String jarg1, long jarg2);

  public static final native int vsscanf(String jarg1, String jarg2, long jarg3);

  public static final native int __isoc99_vfscanf(
      long jarg1, _IO_FILE jarg1_, String jarg2, long jarg3);

  public static final native int __isoc99_vscanf(String jarg1, long jarg2);

  public static final native int __isoc99_vsscanf(String jarg1, String jarg2, long jarg3);

  public static final native int fgetc(long jarg1, _IO_FILE jarg1_);

  public static final native int getc(long jarg1, _IO_FILE jarg1_);

  public static final native int getchar();

  public static final native int getc_unlocked(long jarg1, _IO_FILE jarg1_);

  public static final native int getchar_unlocked();

  public static final native int fgetc_unlocked(long jarg1, _IO_FILE jarg1_);

  public static final native int fputc(int jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native int putc(int jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native int putchar(int jarg1);

  public static final native int fputc_unlocked(int jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native int putc_unlocked(int jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native int putchar_unlocked(int jarg1);

  public static final native int getw(long jarg1, _IO_FILE jarg1_);

  public static final native int putw(int jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native String fgets(String jarg1, int jarg2, long jarg3, _IO_FILE jarg3_);

  public static final native int __getdelim(
      long jarg1, long jarg2, int jarg3, long jarg4, _IO_FILE jarg4_);

  public static final native int getdelim(
      long jarg1, long jarg2, int jarg3, long jarg4, _IO_FILE jarg4_);

  public static final native int getline(long jarg1, long jarg2, long jarg3, _IO_FILE jarg3_);

  public static final native int fputs(String jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native int puts(String jarg1);

  public static final native int ungetc(int jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native long fread(
      long jarg1, long jarg2, long jarg3, long jarg4, _IO_FILE jarg4_);

  public static final native long fwrite(
      long jarg1, long jarg2, long jarg3, long jarg4, _IO_FILE jarg4_);

  public static final native long fread_unlocked(
      long jarg1, long jarg2, long jarg3, long jarg4, _IO_FILE jarg4_);

  public static final native long fwrite_unlocked(
      long jarg1, long jarg2, long jarg3, long jarg4, _IO_FILE jarg4_);

  public static final native int fseek(long jarg1, _IO_FILE jarg1_, int jarg2, int jarg3);

  public static final native int ftell(long jarg1, _IO_FILE jarg1_);

  public static final native void rewind(long jarg1, _IO_FILE jarg1_);

  public static final native int fseeko(long jarg1, _IO_FILE jarg1_, int jarg2, int jarg3);

  public static final native int ftello(long jarg1, _IO_FILE jarg1_);

  public static final native int feof(long jarg1, _IO_FILE jarg1_);

  public static final native int ferror(long jarg1, _IO_FILE jarg1_);

  public static final native void clearerr_unlocked(long jarg1, _IO_FILE jarg1_);

  public static final native int feof_unlocked(long jarg1, _IO_FILE jarg1_);

  public static final native int ferror_unlocked(long jarg1, _IO_FILE jarg1_);

  public static final native void perror(String jarg1);

  public static final native int fileno(long jarg1, _IO_FILE jarg1_);

  public static final native int fileno_unlocked(long jarg1, _IO_FILE jarg1_);

  public static final native int pclose(long jarg1, _IO_FILE jarg1_);

  public static final native long popen(String jarg1, String jarg2);

  public static final native String ctermid(String jarg1);

  public static final native void flockfile(long jarg1, _IO_FILE jarg1_);

  public static final native int ftrylockfile(long jarg1, _IO_FILE jarg1_);

  public static final native void funlockfile(long jarg1, _IO_FILE jarg1_);

  public static final native int __uflow(long jarg1, _IO_FILE jarg1_);

  public static final native int __overflow(long jarg1, _IO_FILE jarg1_, int jarg2);

  public static final native int BITWUZLA_SAT_get();

  public static final native int BITWUZLA_UNSAT_get();

  public static final native int BITWUZLA_UNKNOWN_get();

  public static final native int BITWUZLA_RM_RNE_get();

  public static final native int BITWUZLA_RM_RNA_get();

  public static final native int BITWUZLA_RM_RTN_get();

  public static final native int BITWUZLA_RM_RTP_get();

  public static final native int BITWUZLA_RM_RTZ_get();

  public static final native int BITWUZLA_RM_MAX_get();

  public static final native int BITWUZLA_KIND_CONSTANT_get();

  public static final native String bitwuzla_copyright();

  public static final native String bitwuzla_version();

  public static final native String bitwuzla_git_id();

  public static final native void BitwuzlaOptionInfo_opt_set(
      long jarg1, BitwuzlaOptionInfo jarg1_, int jarg2);

  public static final native int BitwuzlaOptionInfo_opt_get(long jarg1, BitwuzlaOptionInfo jarg1_);

  public static final native void BitwuzlaOptionInfo_shrt_set(
      long jarg1, BitwuzlaOptionInfo jarg1_, String jarg2);

  public static final native String BitwuzlaOptionInfo_shrt_get(
      long jarg1, BitwuzlaOptionInfo jarg1_);

  public static final native void BitwuzlaOptionInfo_lng_set(
      long jarg1, BitwuzlaOptionInfo jarg1_, String jarg2);

  public static final native String BitwuzlaOptionInfo_lng_get(
      long jarg1, BitwuzlaOptionInfo jarg1_);

  public static final native void BitwuzlaOptionInfo_description_set(
      long jarg1, BitwuzlaOptionInfo jarg1_, String jarg2);

  public static final native String BitwuzlaOptionInfo_description_get(
      long jarg1, BitwuzlaOptionInfo jarg1_);

  public static final native void BitwuzlaOptionInfo_is_numeric_set(
      long jarg1, BitwuzlaOptionInfo jarg1_, boolean jarg2);

  public static final native boolean BitwuzlaOptionInfo_is_numeric_get(
      long jarg1, BitwuzlaOptionInfo jarg1_);

  public static final native void BitwuzlaOptionInfo_numeric_set(
      long jarg1, BitwuzlaOptionInfo jarg1_, long jarg2, NumericValue jarg2_);

  public static final native long BitwuzlaOptionInfo_numeric_get(
      long jarg1, BitwuzlaOptionInfo jarg1_);

  public static final native long BitwuzlaOptionInfo_mode_get(
      long jarg1, BitwuzlaOptionInfo jarg1_);

  public static final native long new_BitwuzlaOptionInfo();

  public static final native void delete_BitwuzlaOptionInfo(long jarg1);

  public static final native void NumericValue_cur_set(long jarg1, NumericValue jarg1_, long jarg2);

  public static final native long NumericValue_cur_get(long jarg1, NumericValue jarg1_);

  public static final native void NumericValue_dflt_set(
      long jarg1, NumericValue jarg1_, long jarg2);

  public static final native long NumericValue_dflt_get(long jarg1, NumericValue jarg1_);

  public static final native void NumericValue_min_set(long jarg1, NumericValue jarg1_, long jarg2);

  public static final native long NumericValue_min_get(long jarg1, NumericValue jarg1_);

  public static final native void NumericValue_max_set(long jarg1, NumericValue jarg1_, long jarg2);

  public static final native long NumericValue_max_get(long jarg1, NumericValue jarg1_);

  public static final native long new_NumericValue();

  public static final native void delete_NumericValue(long jarg1);

  public static final native long new_ModeValue();

  public static final native void delete_ModeValue(long jarg1);

  public static final native long bitwuzla_options_new();

  public static final native void bitwuzla_options_delete(long jarg1);

  public static final native boolean bitwuzla_option_is_valid(long jarg1, String jarg2);

  public static final native boolean bitwuzla_option_is_numeric(long jarg1, int jarg2);

  public static final native boolean bitwuzla_option_is_mode(long jarg1, int jarg2);

  public static final native void bitwuzla_set_option(long jarg1, int jarg2, long jarg3);

  public static final native void bitwuzla_set_option_mode(long jarg1, int jarg2, String jarg3);

  public static final native long bitwuzla_get_option(long jarg1, int jarg2);

  public static final native String bitwuzla_get_option_mode(long jarg1, int jarg2);

  public static final native void bitwuzla_get_option_info(
      long jarg1, int jarg2, long jarg3, BitwuzlaOptionInfo jarg3_);

  public static final native String bitwuzla_result_to_string(int jarg1);

  public static final native String bitwuzla_rm_to_string(int jarg1);

  public static final native String bitwuzla_kind_to_string(int jarg1);

  public static final native long bitwuzla_sort_hash(long jarg1);

  public static final native long bitwuzla_sort_bv_get_size(long jarg1);

  public static final native long bitwuzla_sort_fp_get_exp_size(long jarg1);

  public static final native long bitwuzla_sort_fp_get_sig_size(long jarg1);

  public static final native long bitwuzla_sort_array_get_index(long jarg1);

  public static final native long bitwuzla_sort_array_get_element(long jarg1);

  public static final native long bitwuzla_sort_fun_get_domain_sorts(long jarg1, long[] jarg2);

  public static final native long bitwuzla_sort_fun_get_codomain(long jarg1);

  public static final native long bitwuzla_sort_fun_get_arity(long jarg1);

  public static final native String bitwuzla_sort_get_uninterpreted_symbol(long jarg1);

  public static final native boolean bitwuzla_sort_is_equal(long jarg1, long jarg2);

  public static final native boolean bitwuzla_sort_is_array(long jarg1);

  public static final native boolean bitwuzla_sort_is_bool(long jarg1);

  public static final native boolean bitwuzla_sort_is_bv(long jarg1);

  public static final native boolean bitwuzla_sort_is_fp(long jarg1);

  public static final native boolean bitwuzla_sort_is_fun(long jarg1);

  public static final native boolean bitwuzla_sort_is_rm(long jarg1);

  public static final native boolean bitwuzla_sort_is_uninterpreted(long jarg1);

  public static final native String bitwuzla_sort_to_string(long jarg1);

  public static final native void bitwuzla_sort_print(long jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native long bitwuzla_term_hash(long jarg1);

  public static final native int bitwuzla_term_get_kind(long jarg1);

  public static final native long[] bitwuzla_term_get_children(long jarg1, long[] jarg2);

  public static final native long[] bitwuzla_term_get_indices(long jarg1, long[] jarg2);

  public static final native boolean bitwuzla_term_is_indexed(long jarg1);

  public static final native long bitwuzla_term_get_sort(long jarg1);

  public static final native long bitwuzla_term_array_get_index_sort(long jarg1);

  public static final native long bitwuzla_term_array_get_element_sort(long jarg1);

  public static final native long bitwuzla_term_fun_get_domain_sorts(long jarg1, long[] jarg2);

  public static final native long bitwuzla_term_fun_get_codomain_sort(long jarg1);

  public static final native long bitwuzla_term_bv_get_size(long jarg1);

  public static final native long bitwuzla_term_fp_get_exp_size(long jarg1);

  public static final native long bitwuzla_term_fp_get_sig_size(long jarg1);

  public static final native long bitwuzla_term_fun_get_arity(long jarg1);

  public static final native String bitwuzla_term_get_symbol(long jarg1);

  public static final native boolean bitwuzla_term_is_equal_sort(long jarg1, long jarg2);

  public static final native boolean bitwuzla_term_is_array(long jarg1);

  public static final native boolean bitwuzla_term_is_const(long jarg1);

  public static final native boolean bitwuzla_term_is_fun(long jarg1);

  public static final native boolean bitwuzla_term_is_var(long jarg1);

  public static final native boolean bitwuzla_term_is_value(long jarg1);

  public static final native boolean bitwuzla_term_is_bv_value(long jarg1);

  public static final native boolean bitwuzla_term_is_fp_value(long jarg1);

  public static final native boolean bitwuzla_term_is_rm_value(long jarg1);

  public static final native boolean bitwuzla_term_is_bool(long jarg1);

  public static final native boolean bitwuzla_term_is_bv(long jarg1);

  public static final native boolean bitwuzla_term_is_fp(long jarg1);

  public static final native boolean bitwuzla_term_is_rm(long jarg1);

  public static final native boolean bitwuzla_term_is_uninterpreted(long jarg1);

  public static final native boolean bitwuzla_term_is_true(long jarg1);

  public static final native boolean bitwuzla_term_is_false(long jarg1);

  public static final native boolean bitwuzla_term_is_bv_value_zero(long jarg1);

  public static final native boolean bitwuzla_term_is_bv_value_one(long jarg1);

  public static final native boolean bitwuzla_term_is_bv_value_ones(long jarg1);

  public static final native boolean bitwuzla_term_is_bv_value_min_signed(long jarg1);

  public static final native boolean bitwuzla_term_is_bv_value_max_signed(long jarg1);

  public static final native boolean bitwuzla_term_is_fp_value_pos_zero(long jarg1);

  public static final native boolean bitwuzla_term_is_fp_value_neg_zero(long jarg1);

  public static final native boolean bitwuzla_term_is_fp_value_pos_inf(long jarg1);

  public static final native boolean bitwuzla_term_is_fp_value_neg_inf(long jarg1);

  public static final native boolean bitwuzla_term_is_fp_value_nan(long jarg1);

  public static final native boolean bitwuzla_term_is_rm_value_rna(long jarg1);

  public static final native boolean bitwuzla_term_is_rm_value_rne(long jarg1);

  public static final native boolean bitwuzla_term_is_rm_value_rtn(long jarg1);

  public static final native boolean bitwuzla_term_is_rm_value_rtp(long jarg1);

  public static final native boolean bitwuzla_term_is_rm_value_rtz(long jarg1);

  public static final native boolean bitwuzla_term_value_get_bool(long jarg1);

  public static final native String bitwuzla_term_value_get_str(long jarg1);

  public static final native String bitwuzla_term_value_get_str_fmt(long jarg1, int jarg2);

  public static final native void bitwuzla_term_value_get_fp_ieee(
      long jarg1, long jarg2, long jarg3, long jarg4, int jarg5);

  public static final native int bitwuzla_term_value_get_rm(long jarg1);

  public static final native String bitwuzla_term_to_string(long jarg1);

  public static final native String bitwuzla_term_to_string_fmt(long jarg1, int jarg2);

  public static final native void bitwuzla_term_print(long jarg1, long jarg2, _IO_FILE jarg2_);

  public static final native void bitwuzla_term_print_fmt(
      long jarg1, long jarg2, _IO_FILE jarg2_, int jarg3);

  public static final native long bitwuzla_new(long options);

  public static final native void bitwuzla_delete(long env);

  public static final native void bitwuzla_set_termination_callback(
      long jarg1, long jarg2, long jarg3);

  public static final native long bitwuzla_get_termination_callback_state(long jarg1);

  public static final native void bitwuzla_set_abort_callback(long jarg1);

  public static final native void bitwuzla_push(long env, long numOfLevels);

  public static final native void bitwuzla_pop(long env, long numOfLevel);

  public static final native void bitwuzla_assert(long env, long jarg2);

  public static final native long bitwuzla_get_assertions(long jarg1, long[] jarg2);

  public static final native boolean bitwuzla_is_unsat_assumption(long jarg1, long jarg2);

  public static final native long bitwuzla_get_unsat_assumptions(long jarg1, long[] jarg2);

  public static final native long bitwuzla_get_unsat_core(long env, long[] jarg2);

  public static final native void bitwuzla_simplify(long jarg1);

  public static final native int bitwuzla_check_sat(long env);

  public static final native int bitwuzla_check_sat_assuming(long jarg1, long jarg2, long[] jarg3);

  public static final native long bitwuzla_get_value(long env, long term);

  public static final native void bitwuzla_print_formula(
      long jarg1, String jarg2, long jarg3, _IO_FILE jarg3_, int jarg4);

  public static final native void bitwuzla_get_statistics(
      long jarg1, long jarg2, long jarg3, long[] jarg4);

  public static final native long bitwuzla_mk_array_sort(long index, long element);

  public static final native long bitwuzla_mk_bool_sort();

  public static final native long bitwuzla_mk_bv_sort(long width);

  public static final native long bitwuzla_mk_fp_sort(long exponent, long significant);

  public static final native long bitwuzla_mk_fun_sort(long arity, long[] domain, long codomain);

  public static final native long bitwuzla_mk_rm_sort();

  public static final native long bitwuzla_mk_uninterpreted_sort(String symbol);

  public static final native long bitwuzla_mk_true();

  public static final native long bitwuzla_mk_false();

  public static final native long bitwuzla_mk_bv_zero(long jarg1);

  public static final native long bitwuzla_mk_bv_one(long jarg1);

  public static final native long bitwuzla_mk_bv_ones(long jarg1);

  public static final native long bitwuzla_mk_bv_min_signed(long jarg1);

  public static final native long bitwuzla_mk_bv_max_signed(long jarg1);

  public static final native long bitwuzla_mk_fp_pos_zero(long jarg1);

  public static final native long bitwuzla_mk_fp_neg_zero(long jarg1);

  public static final native long bitwuzla_mk_fp_pos_inf(long jarg1);

  public static final native long bitwuzla_mk_fp_neg_inf(long jarg1);

  public static final native long bitwuzla_mk_fp_nan(long jarg1);

  public static final native long bitwuzla_mk_bv_value(long jarg1, String jarg2, int jarg3);

  public static final native long bitwuzla_mk_bv_value_uint64(long jarg1, long jarg2);

  public static final native long bitwuzla_mk_bv_value_int64(long jarg1, int jarg2);

  public static final native long bitwuzla_mk_fp_value(long jarg1, long jarg2, long jarg3);

  public static final native long bitwuzla_mk_fp_from_real(long jarg1, long jarg2, String jarg3);

  public static final native long bitwuzla_mk_fp_from_rational(
      long jarg1, long jarg2, String jarg3, String jarg4);

  public static final native long bitwuzla_mk_rm_value(int jarg1);

  public static final native long bitwuzla_mk_term1(int jarg1, long jarg2);

  public static final native long bitwuzla_mk_term2(int jarg1, long jarg2, long jarg3);

  public static final native long bitwuzla_mk_term3(int jarg1, long jarg2, long jarg3, long jarg4);

  public static final native long bitwuzla_mk_term(int jarg1, long jarg2, long[] jarg3);

  public static final native long bitwuzla_mk_term1_indexed1(int jarg1, long jarg2, long jarg3);

  public static final native long bitwuzla_mk_term1_indexed2(
      int jarg1, long jarg2, long jarg3, long jarg4);

  public static final native long bitwuzla_mk_term2_indexed1(
      int jarg1, long jarg2, long jarg3, long jarg4);

  public static final native long bitwuzla_mk_term2_indexed2(
      int jarg1, long jarg2, long jarg3, long jarg4, long jarg5);

  public static final native long bitwuzla_mk_term_indexed(
      int jarg1, long jarg2, long[] jarg3, long jarg4, long[] jarg5);

  public static final native long bitwuzla_mk_const(long jarg1, String jarg2);

  public static final native long bitwuzla_mk_const_array(long jarg1, long jarg2);

  public static final native long bitwuzla_mk_var(long jarg1, String jarg2);

  public static final native long bitwuzla_substitute_term(
      long jarg1, long jarg2, long[] jarg3, long[] jarg4);

  public static final native void bitwuzla_substitute_terms(
      long sizeOfTermsToSubstituteIn,
      long[] termsToSubstituteIn,
      long sizeOfSubstitutionMap,
      long[] mapKeys,
      long[] mapValues);

  public static final native long bitwuzla_parser_new(
      long jarg1, String jarg2, long jarg3, _IO_FILE jarg3_, String jarg4, int jarg5, String jarg6);

  public static final native void bitwuzla_parser_delete(long jarg1);

  public static final native String bitwuzla_parser_parse(long jarg1, boolean jarg2);

  public static final native long bitwuzla_parser_get_bitwuzla(long jarg1);

  // This returns the assertions of the input String as formulas individually
  public static final native long[] parse(String jarg);

  /**
   * Dumps the SMT2 String representation of a entered Bitwuzla term.
   *
   * @param term Bitwuzla term.
   * @param baseOfValueInterpretation base of the values to be printed. I.e. 2, 10 or 16.
   * @return String of the term.
   */
  public static final native String dump_formula_smt2(long term, int baseOfValueInterpretation);

  /**
   * Prints the assertion stack of the entered Bitwuzla environment.
   *
   * @param env a Bitwuzla environment where the assertion stack should be printed.
   * @param baseOfValueInterpretation base of the values to be printed. I.e. 2, 10 or 16.
   * @return String of the terms on the assertion stack.
   */
  public static final native String dump_assertions_smt2(long env, int baseOfValueInterpretation);

  /**
   * Sets termination callback to chosen implementation of a method.
   *
   * @param env instance
   * @param terminationCallback TerminationCallback method
   * @return address to helper struct. Call method boolector_free_termination with it to free its
   *     ressources after termination!
   */
  protected static native long set_termination(long env, TerminationCallback terminationCallback);

  /**
   * Frees resources of the termination callback function. Call ONLY after termination has occured
   * with the return value of set_termination of its instance!
   *
   * @param helper address to helper struct used in termination callback.
   */
  protected static native void free_termination(long helper);

  /** This is used to get the methodID for the JNI call to the termination callback method. */
  interface TerminationCallback {
    boolean shouldTerminate() throws InterruptedException;
  }
}
