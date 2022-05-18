#!/bin/bash

month=`date | awk '{print $2}'`
day=`date | awk '{print $3}'`

files=`ls -l | grep '^-' | awk '{print $NF}'`
for file in $files
do
    file_month=`ls -l $file | awk '{print $7}'`
    file_day=`ls -l $file | awk '{print $8}'`

    if [ $file_month = $month ]
    then
        if [ $file_day -eq $day ]
        then
            echo $file
        fi
    fi
done