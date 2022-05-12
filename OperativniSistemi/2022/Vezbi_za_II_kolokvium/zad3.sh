#!/bin/bash

sameContent(){
    srcCn=`cat $1`
    destCn=`cat $2`
    if [ "$srcCn" = "$destCn" ]
    then
        echo true
    else
        echo false
    fi
}

if [ $# -ne 2 ]
then
    echo "USAGE: ./zad5.sh [from_folder] [to_folder]"
    exit -1
fi
if [ ! -d "$1" ]
then
    echo "USAGE: ./zad5.sh [from_folder] [to_folder]"
    exit -1
fi
if [ ! -d "$2" ]
then
    echo "USAGE: ./zad5.sh [from_folder] [to_folder]"
    exit -1
fi

if [ -f copies.txt ]
then
    rm copies.txt
fi
touch copies.txt

files=`find $1`
for file in $files
do
    if [ ! -f $file ]
    then
        continue
    fi
    dest=`echo "$file" | sed 's/\//_/g'`
    `cp $file ${2}${dest}`

    for destFile in `ls $2`
    do
        same=`sameContent $file ${2}${destFile}`
        if [ $same = true ]
        then
            echo $file $destFile >> copies.txt
        fi
    done
done
