---
title: VSCode
---

# 必备插件

- File Utils
  - 

# 快捷键

## 折叠代码

- **Fold** folds the innermost uncollapsed region at the cursor:
  - Ctrl + Shift + [ on Windows
  - ⌥ + ⌘ + [ on macOS
- **Unfold** unfolds the collapsed region at the cursor:
  - Ctrl + Shift + ] on Windows
  - ⌥ + ⌘ + ] on macOS
- **Fold All** folds all region in the editor:
  - Ctrl + K, Ctrl + 0 (zero) on Windows
  - ⌘ + K, ⌘ + 0 (zero) on macOS
- **Unfold All** unfolds all regions in the editor:
  - Ctrl + K, Ctrl + J on Windows
  - ⌘ + K, ⌘ + J on macOS

# snippers

## python

```json
{
	// Place your snippets for python here. Each snippet is defined under a snippet name and has a prefix, body and 
	// description. The prefix is what is used to trigger the snippet and the body will be expanded and inserted. Possible variables are:
	// $1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders. Placeholders with the 
	// same ids are connected.
	// Example:
	// "Print to console": {
	// 	"prefix": "log",
	// 	"body": [
	// 		"console.log('$1');",
	// 		"$2"
	// 	],
	// 	"description": "Log output to console"
	// }
	"Auto gen template" : {
		"prefix" : "header",
		"body": [
			"#!/bin/python3",
			"",
			"\"\"\"",
			"@author	sunzhenkai",
			"@file	$TM_FILENAME",
			"@date	$CURRENT_YEAR-$CURRENT_MONTH-$CURRENT_DATE $CURRENT_HOUR:$CURRENT_MINUTE:$CURRENT_SECOND"
			"@desc	$1"
			"\"\"\"",
		],
	}
}
```

