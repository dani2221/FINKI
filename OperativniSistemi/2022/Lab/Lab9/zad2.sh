#!/bin/bash

for file in `ls $1 | grep '^[a-z]*\.txt$'`
do
    new_name=`echo $file | sed 's/\.txt/\.moved_txt/'`
    mv ${1}$file ${2}$new_name
done