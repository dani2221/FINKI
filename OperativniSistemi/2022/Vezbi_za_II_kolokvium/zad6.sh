#!/bin/bash

if [ -f $2 ]
then
    rm $2
touch $2
fi

dats=`find "$1" | grep '\\\\[0-9][0-9]*\\\\'`
for dat in $dats
do
    num=`echo $dat | grep -o '\\\\[0-9][0-9]*\\\\'`
    contents=`cat $dat`
    echo "===== $num =====" >> $2
    echo "$contents" >> $2
    echo "===== $num =====" >> $2
done