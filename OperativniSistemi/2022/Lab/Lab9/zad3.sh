#!/bin/bash

if [ $# -ne 2 ]
then
    echo "USAGE: hw08_02_{index}.sh [FROM] [TO]"
    exit -1
fi
if [ ! -d $1 ]
then
    echo "USAGE: hw08_02_{index}.sh [FROM] [TO]"
    exit -1
fi
if [ ! -d $2 ]
then
    echo "USAGE: hw08_02_{index}.sh [FROM] [TO]"
    exit -1
fi

rm -r $2
mkdir $2

for file in `ls $1 | grep '^[a-z]*\.txt$'`
do
    new_name=`echo $file | sed 's/\.txt/\.moved_txt/'`
    mv ${1}$file ${2}$new_name
done

ls -l $2 | awk 'BEGIN {total=0} {total+=$6} END {print total}'