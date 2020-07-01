#!/bin/bash

usage() {
    echo <<EOF
Usage: $0 [-a] [-b name] msg
EOF
}

while getopts ":ab:Bc:" opt; do
    case $opt in
        a) echo "found -a" ; a="hello" ;;
        b) echo "found -b and value is: $OPTARG" ;;
        c) echo "found -c and value is: $OPTARG" ;;
        *) usage ;;
    esac
done

shift $(($OPTIND - 1))

echo "MSG: $1"

if [ -n "${a}" ] ; then         # or [ ! -z "${a}" ]
    echo "-a exists : ${a}"
else
    echo "-a not exists"
fi
