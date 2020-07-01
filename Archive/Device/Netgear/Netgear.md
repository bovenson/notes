# Astrill

## Openvpn error

```shell
# error
Cannot open TUN/TAP dev /dev/net/tun: No such device (errno=19)

# connect router
$ telnet 192.168.*.1

# fix
$ modprobe tun
```

