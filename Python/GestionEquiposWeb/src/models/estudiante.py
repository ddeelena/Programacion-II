
from dataclasses import dataclass


@dataclass
class Estudiante:

    id: int | None
    nombre: str
    edad: int
    grado: str
    clases: list[str]

    def __init__(self, id: int | None, nombre: str, edad: int, grado: str, clases: list[str]) -> None:
        self.id = id
        self.nombre = nombre
        self.edad = edad
        self.grado = grado
        self.clases = clases
    
    def __str__(self) -> str:
        return f"ID: {self.id}, Nombre: {self.nombre}, Edad: {self.edad}, Grado: {self.grado}, Clases: {self.clases}"
    
    