#!/bin/bash
getFiles(){
    local res=`ls -l | awk -v start="$1" -v end="$2" '{if($8>start && $8<end)print $8,$10;}' |\
    grep '\.txt$' | awk '{print $2;}'`
    echo "$res"
}
findAllTextFiles(){
    outFile="$1"
    shift
    for file in $@
    do
        size=`ls -l ${file} | awk '{print $6;}'`
        if [ "$size" -lt 150 ]
        then
            continue
        fi
        num=`cat ${file} | grep -o 'echo' | wc -l`
        copy=0
        if [ -f ${outFile}index_${num} ]
        then
            copy=$((copy+1))
        fi
        while [ -f ${outFile}index_${num}${copy} ]
        do
            copy=$((copy+1))
        done
        if [ $copy -eq 0 ]
        then
            touch ${outFile}index_${num}
        else
            touch ${outFile}index_${num}${copy}
        fi
    done  

}
# random checks treba ko sekoja zadaca
result=`getFiles $1 $2`
findAllTextFiles $3 $result 
