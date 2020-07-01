---
title: Scss样式
tags: 
	- Scss
categories:
	- Web
---

# 常用标签

## `a`

- `a:link` : 未访问
- `a:visited` : 已访问
- `a:hover` : 悬浮
- `a:active` : 活动链接

```scss
@import './color.scss';
.style-a {
  color: $font-color-blue;
  font-size: .8rem;
  padding: .1rem .2rem;
  text-decoration: none;

  &-multi {
    margin-right: .3rem;
  }

  &:hover {
    text-decoration: none;
    background-color: $font-color-blue;
    color: white;
    border-radius: .2rem;
  }
}
```

