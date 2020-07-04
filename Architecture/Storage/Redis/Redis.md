---
title: Redis
---

# Install

## Client

```shell
# redis-cli
## macos
$ brew install redis
```

## Get All Master Nodes

```shell
#!/bin/bash
set -x

usage() {
    cat <<EOF
Usage: $0 [-h host] [-p port]
       $0 [-a host:port]
EOF
}

while getopts "h:p:a:" opt; do
    case $opt in
        h) rhost="$OPTARG" ;;
        p) rport="$OPTARG" ;;
        a) raddress="$OPTARG" ;;
        *) usage ; exit 1 ;;
    esac
done

if [[ ! -z "$raddress" ]]; then
    rhost=$(echo "$raddress" | cut -d ":" -f1)
    rport=$(echo "$raddress" | cut -d ":" -f2)
fi

if [[ -z "$rhost" || -z "$rport" ]]; then
    usage
    exit 1
fi

redis-cli -h $rhost -p $rport cluster nodes | grep master | cut -d ' ' -f2 | paste -sd ',' -
```

