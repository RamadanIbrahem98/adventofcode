#!/bin/bash

input_file="input.txt"
result=0

if [[ ! -f $input_file ]]; then
    echo "File not found!"
    exit 1
fi

abs() {
    if [ "$1" -lt 0 ]; then
        echo $(( -1 * $1 ))
    else
        echo "$1"
    fi
}

while IFS= read -r line; do
    read -a numbers <<< "$line"
    length="${#numbers[@]}"

    curr=$((numbers[0] - numbers[1]))
    abs_curr=$(abs "$curr")

    satisfy=0

    if [[ "$abs_curr" -gt 0 && "$abs_curr" -lt 4 ]]; then
        satisfy=1
        for ((i = 1; i < length - 1; i += 1)); do
            loop=$((numbers[i] - numbers[i + 1]))
	    abs_loop=$(abs "$loop")

	    if [[ $((curr * loop)) -lt 1 || "$abs_loop" -lt 1 || "$abs_loop" -gt 3 ]]; then
                satisfy=0
                break
            fi
        done
    fi

    if [[ "$satisfy" -eq 1 ]]; then
        result=$((result + 1))
    fi
done < "$input_file"

echo "$result"
