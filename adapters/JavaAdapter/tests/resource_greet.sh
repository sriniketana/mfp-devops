#!/bin/bash
mfpdev --version
echo "#### Test Adapter Endpoint : resource/gree"
output=$(mfpdev adapter call javaAdapter/resource/greet --id "test" --secret "test" --query "name:MFP Adapter")
if [ $? -eq 0 ]; then
	echo OK
else
	echo FAIL
fi
echo "Adapter Call Response : "
echo $output
