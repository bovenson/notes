def f(param):
    def inner(param):
        print('Inner: ', param)
    print(param)
    inner('HI')
    

if __name__ == '__main__':
    f('Hello Python Function')
    # f.inner('Hello Inner')
