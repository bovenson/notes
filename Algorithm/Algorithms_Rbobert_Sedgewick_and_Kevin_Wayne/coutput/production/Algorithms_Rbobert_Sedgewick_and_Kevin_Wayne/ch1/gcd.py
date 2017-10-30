#!/bin/python3

def gcd(m, n):
    """求最大公约数
    m,n为正整数,且m > n
    """
    if n == 0:
        return m
    r = m % n
    return gcd(n, r)

if __name__ == "__main__":
    print(gcd(5, 3))
    print(gcd(15, 9))

