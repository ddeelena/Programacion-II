from typing import Callable 

resta = Callable[[float,float],float]

def restar(a: float, b: float) -> float:
    return a - b


operar : resta = restar

#resultado = operar(10,5)
#print(resultado)

SaludoFuncion = Callable[[],str]

def saludar() -> str:
    return "Hola, Bienvenido"

saludo : SaludoFuncion = saludar
#print(saludo())

FuncionGenerica = Callable[...,any]

def proceso(*args, **kwargs) -> any:
    print(args)
    print(kwargs)

procesar : FuncionGenerica = proceso

procesar(1,2,3,4,5, nombre="Juan", edad=25)

