#!/bin/bash
if [ $# -ne 1 ]
then
    echo "Vnesete papka kako argument"
    exit -1
fi
if [ ! -d $1 ]
then
    echo "Vnesete papka kako argument"
    exit -1
fi
if [ -f results.rez ]
then
        rm results.rez
fi
touch results.rez
site=`ls $1 | grep '\.rez$'`
for pap in $site
do
    toadd=`cat $1/$pap | awk -F, '{print $1,$2}' | grep -v '^l' | awk '{print $2}'`
    for new in $toadd
    do
        resultFile=`cat results.rez | grep "^$new"`
        if [ -z "$resultFile" ]
        then
            echo $new 1 >> results.rez
        else
            amount=`echo "$resultFile" | awk '{print $2}'`
            amount=$((amount+1))
            result=`cat results.rez | grep -v "^$new"`
            echo "$result" > results.rez
            echo $new $amount >> results.rez
        fi
        done
done
