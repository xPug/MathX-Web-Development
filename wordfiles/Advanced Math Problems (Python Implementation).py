#```python

'''
Refer to #Modules file
'''

import sympy as sp

def derivative(function, variable):
    x = sp.Symbol(variable)
    return sp.diff(function, x)

def integral(function, variable):
    x = sp.Symbol(variable)
    return sp.integrate(function, x)

def definite_integral(function, variable, lower, upper):
    x = sp.Symbol(variable)
    return sp.integrate(function, (x, lower, upper))

def taylor_series(function, variable, point, order):
    x = sp.Symbol(variable)
    return sp.series(function, x, point, order)

def laplace_transform(function, variable):
    x = sp.Symbol(variable)
    s = sp.Symbol('s')
    return sp.laplace_transform(function, x, s, noconds=True)

def fourier_transform(function, variable):
    x = sp.Symbol(variable)
    k = sp.Symbol('k')
    return sp.fourier_transform(function, x, k)

if __name__ == "__main__":
    x = sp.Symbol('x')
    f = x**2 + 3*x + 5
    print("Derivative:", derivative(f, 'x'))
    print("Integral:", integral(f, 'x'))
    print("Definite Integral (0 to 2):", definite_integral(f, 'x', 0, 2))
    print("Taylor Series (around x=0, order=4):", taylor_series(f, 'x', 0, 4))
    print("Laplace Transform:", laplace_transform(f, 'x'))
    print("Fourier Transform:", fourier_transform(f, 'x'))
