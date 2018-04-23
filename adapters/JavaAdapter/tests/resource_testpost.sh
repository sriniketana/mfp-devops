#!/bin/bash
echo "#### Test Adapter Endpoint : resource/testpost"
output=$(mfpdev adapter call javaAdapter/resource/testpost --id "test" --secret "test" --header "content-type:application/json" --form "name:shioj,age:35" -type post)
if [ $? -eq 0 ]; then
	echo OK
else
	echo FAIL
	exit 1
fi
echo "Adapter Call Response : "
echo $output
