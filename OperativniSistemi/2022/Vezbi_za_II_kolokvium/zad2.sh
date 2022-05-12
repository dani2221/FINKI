#!/bin/bash

if [ $# -ne 1 ]
then
    echo "oof args"
    exit -1
fi
if [ ! -f $1  ]
then
    echo "oof not file"
    exit -1
fi
if [ -z `echo "$1" | grep '\.csv$'` ]
then
    echo "oof not csv"
    exit -1
fi
if [ -z `echo "$1" | grep '\.csv$'` ]
then
    echo "oof not csv"
    exit -1
fi

if [ -f "passed_$1" ]
then
    rm "passed_$1"
fi
`touch "passed_$1"`

passed=`cat os_grades.csv | awk -F, '{if($4 > 5){ printf "%s,%s,%s,%s\n",$1,$2,$3,$4 }}'`
sum=`echo "$passed" | awk -F, 'BEGIN {total=0;} {total+=$4;} END {print total;}'`
len=`echo "$passed" | wc -l`
avg=$((sum/(len-1)))
echo "$passed" >> "passed_$1"
echo "Average grade of people who passed is $avg" >> "passed_$1"

cat "passed_$1"
