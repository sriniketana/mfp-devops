#!/bin/bash
echo "#### Test Adapter Endpoint : resource/greet"
output=$(mfpdev adapter call javaAdapter/resource/greet --id "test" --secret "test" --query "name:MFP Adapter")
if [ $? -eq 0 ]; then
	echo OK
else
	echo FAIL
	exit 1
fi
echo "Adapter Call Response : "
echo $output
