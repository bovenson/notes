# File/Dir Permissions

```shell
- --- --- ---
1 234 567 890
d rwx r-- rw-

# 1
-: file
d: directory
l: link

# 234
owner

# 567
group

# 890
other uesers
```

# chmod

```shell
# Options
-R: recursive

# cmd
chmod [ugo][+-][rwx] file

# exmaple
chmod u+w file
chmod ug+rw file
chmod -R ugo+x file
```

