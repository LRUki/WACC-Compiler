#!/usr/bin/env bash

# USAGE:

# This script will create the .out and .exitcode files requires for the testextension script

# It detects any lines with ## as outputs
# It detects any lines with #* as exitcode

# Example usage:  ./makeOutExitcode.sh temp_test/valid/ extension_examples/valid/structs/ extension_wacc/valid/structs
# Second arguement can specify where to move the generated files to
# Third argument provides where to save the wacc files to

# Please follow this style in a example wacc file

# Output:
## 12
## 16

#Exitcode:
#* 0

for WACC_FILENAME in `find $1 -type f -iname "*.wacc" `
do
  ORIG_FILE=${WACC_FILENAME}
  WACC_FILENAME=$(basename -- "$WACC_FILENAME")
  extension="${WACC_FILENAME##*.}"
  FILENAME="${WACC_FILENAME%.*}"
  FILENAME=${FILENAME#*/*/}
  grep '^##' ${ORIG_FILE} > ${FILENAME}1.out
  sed 's/^.\{,3\}//' ${FILENAME}1.out > ${FILENAME}.out
  grep '^#\*' ${ORIG_FILE} > ${FILENAME}1.exitcode
  sed 's/^.\{,3\}//' ${FILENAME}1.exitcode > ${FILENAME}.exitcode
  rm ${FILENAME}1.out
  rm ${FILENAME}1.exitcode
  if [ ! -z "$2" ]
  then
    mv ${FILENAME}.out $2
    mv ${FILENAME}.exitcode $2
    if [ ! -z "$2" ]
    then
      mv ${ORIG_FILE} $3
    fi
  fi
done
