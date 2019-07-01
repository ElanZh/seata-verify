#!/bin/bash
fileList=`ls ./`
for fileName in $fileList
do
    firstName=${fileName%%.*}
    lastName=${fileName#*.}
    if [[ "$lastName" == "yml" || "$lastName" == "yaml" ]]
    then
        curl --request PUT --data-binary @$fileName http://172.20.1.132:8500/v1/kv/seataVerify/$firstName/data
    else
        echo "$fileName not yml"
    fi
done
