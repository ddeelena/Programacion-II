import os
import sys
from typing import List

from src.dao.estudiante_dao import estudiante_dao
from src.models.estudiante import Estudiante

class Menu:
    def __init__(self) -> None:
        self.dao = estudiante_dao()

    def show_menu(self) -> None:
        while True:
            print("\nMenú de Estudiantes")
            print("1. Listar Estudiantes")
            print("2. Buscar Estudiante por ID")
            print("3. Crear Estudiante")
            print("4. Actualizar Estudiante")
            print("5. Eliminar Estudiante")
            print("6. Salir")
            option = input("Seleccione una opción: ")
            if option == "1":
                self.list_students()
            elif option == "2":
                self.find_student()
            elif option == "3":
                self.create_student()
            elif option == "4":
                self.update_student()
            elif option == "5":
                self.delete_student()
            elif option == "6":
                print("¡Hasta luego!")
                break
            else:
                print("Opción inválida")
    
    def crear_estudiante(self) -> Estudiante:
        nombre = input("Nombre: ")
        edad = int(input("Edad: "))
        grado = input("Grado: ")
        clases = input("Clases (separadas por coma): ").split(",")
        return Estudiante(None, nombre, edad, grado, clases)
    
    def list_students(self) -> None:
        students = self.dao.get_all()
        if students:
            for student in students:
                print(student)
        else:
            print("No hay estudiantes registrados")

    def find_student(self) -> None:
        id = int(input("ID del estudiante: "))
        student = self.dao.get_by_id(id)
        if student:
            print(student)
        else:
            print("Estudiante no encontrado")

    def create_student(self) -> None:
        student = self.crear_estudiante()
        success, _ = self.dao.create(student)
        if success:
            print("Estudiante creado exitosamente")
        else:
            print("Error al crear estudiante")
    
    def update_student(self) -> None:
        id = int(input("ID del estudiante: "))
        student = self.dao.get_by_id(id)
        if student:
            student = self.crear_estudiante()
            student.id = id
            success = self.dao.update(student)
            if success:
                print("Estudiante actualizado exitosamente")
            else:
                print("Error al actualizar estudiante")
        else:
            print("Estudiante no encontrado")
    
    def delete_student(self) -> None:
        id = int(input("ID del estudiante: "))
        success = self.dao.delete(id)
        if success:
            print("Estudiante eliminado exitosamente")
        else:
            print("Error al eliminar estudiante")
    
