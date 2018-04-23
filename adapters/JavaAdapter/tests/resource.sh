#!/bin/bash
echo "#### Test Adapter Endpoint : resource"
output=$(mfpdev adapter call javaAdapter/resource --id "test" --secret "test")
if [ $? -eq 0 ]; then
	echo OK
else
	echo FAIL
	exit 1
fi
echo "Adapter Call Response : "
echo $output
