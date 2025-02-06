from typing import Callable, Any
from functools import wraps


def mi_decorador(funcion: Callable[[], None]) -> Callable[[], None]:
    """
    Decorador simple que ejecuta código antes y después de la función decorada.

    Args:
        funcion: La función que será decorada

    Returns:
        Callable: La función envolvente (wrapper)
    """
    def wrapper() -> None:
        print("Antes de llamar a la función")
        funcion()
        print("Después de llamar a la función")

    return wrapper


@mi_decorador
def saludar() -> None:
    """Función simple que imprime un saludo."""
    print("¡Hola!")


# Uso:
saludar()