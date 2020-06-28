# Boolean

**Java**

```java
public static boolean f(String label, boolean value) {
System.out.println("exec " + label);
return value;
}

public static void main(String[] args) {
// System.out.println(f("A", false) || f("B", false) || f("C", true));
/*
exec A
exec B
exec C
true
 */

System.out.println(f("A", false) || f("B", true) && f("C", false));
/*
exec A
exec B
exec C
false
 */

System.out.println(f("A", true) || f("B", false) && f("C", true));
/*
exec A
true
 */

System.out.println((f("A", true) || f("B", false)) && f("C", false));
/*
exec A
exec C
true
 */
}
```

**JavaScript**

```javascript
> function f(v, l) {console.log('exec ', l); return v}

> f(false, 'a') || f(false, 'b') && f(true, 'c')
exec  a
exec  b
false
> f(false, 'a') || f(false, 'b') && f('STR', 'c')
exec  a
exec  b
false
> f(true, 'a') || f(false, 'b') && f('STR', 'c')
exec  a
true
> (f(true, 'a') || f(false, 'b')) && f('STR', 'c')
exec  a
exec  c
'STR'
```

