from typing import Callable

class Operacion:

    def __init__(self  ) -> None:
        self.operacion = Callable[[float,float],float]

    
    def sumar(self, a: float, b: float) -> float:
        return a + b
    
    def restar(self, a: float, b: float) -> float:
        return a - b
    

operar : Operacion = Operacion()
resultado = operar.restar(10,5)
print(resultado)