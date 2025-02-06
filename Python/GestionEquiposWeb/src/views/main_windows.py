import os
import sys

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "../..")))

import tkinter as tk
from tkinter import messagebox, simpledialog
from src.dao.estudiante_dao import estudiante_dao
from src.models.estudiante import Estudiante

class MainApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Gestión de Estudiantes")
        self.root.geometry("400x400")
        
        self.dao = estudiante_dao()
        
        tk.Label(root, text="Menú de Estudiantes", font=("Arial", 14)).pack(pady=10)
        
        tk.Button(root, text="Listar Estudiantes", command=self.list_students).pack(fill=tk.X, pady=5)
        tk.Button(root, text="Buscar Estudiante", command=self.find_student).pack(fill=tk.X, pady=5)
        tk.Button(root, text="Crear Estudiante", command=self.create_student).pack(fill=tk.X, pady=5)
        tk.Button(root, text="Actualizar Estudiante", command=self.update_student).pack(fill=tk.X, pady=5)
        tk.Button(root, text="Eliminar Estudiante", command=self.delete_student).pack(fill=tk.X, pady=5)
        tk.Button(root, text="Salir", command=root.quit).pack(fill=tk.X, pady=5)
    
    def list_students(self):
        students = self.dao.get_all()
        if students:
            student_list = "\n".join(str(s) for s in students)
            messagebox.showinfo("Lista de Estudiantes", student_list)
        else:
            messagebox.showinfo("Lista de Estudiantes", "No hay estudiantes registrados")
    
    def find_student(self):
        id = simpledialog.askinteger("Buscar Estudiante", "Ingrese el ID del estudiante:")
        if id is not None:
            student = self.dao.get_by_id(id)
            if student:
                messagebox.showinfo("Estudiante Encontrado", str(student))
            else:
                messagebox.showerror("Error", "Estudiante no encontrado")
    
    def create_student(self):
        nombre = simpledialog.askstring("Crear Estudiante", "Nombre:")
        edad = simpledialog.askinteger("Crear Estudiante", "Edad:")
        grado = simpledialog.askstring("Crear Estudiante", "Grado:")
        clases = simpledialog.askstring("Crear Estudiante", "Clases (separadas por coma):").split(",")
        if nombre and edad and grado:
            student = Estudiante(None, nombre, edad, grado, clases)
            success, _ = self.dao.create(student)
            if success:
                messagebox.showinfo("Éxito", "Estudiante creado exitosamente")
            else:
                messagebox.showerror("Error", "No se pudo crear el estudiante")
    
    def update_student(self):
        id = simpledialog.askinteger("Actualizar Estudiante", "Ingrese el ID del estudiante:")
        if id is not None:
            student = self.dao.get_by_id(id)
            if student:
                nombre = simpledialog.askstring("Actualizar Estudiante", "Nuevo Nombre:", initialvalue=student.nombre)
                edad = simpledialog.askinteger("Actualizar Estudiante", "Nueva Edad:", initialvalue=student.edad)
                grado = simpledialog.askstring("Actualizar Estudiante", "Nuevo Grado:", initialvalue=student.grado)
                clases = simpledialog.askstring("Actualizar Estudiante", "Nuevas Clases:", initialvalue=",".join(student.clases)).split(",")
                student = Estudiante(id, nombre, edad, grado, clases)
                success = self.dao.update(student)
                if success:
                    messagebox.showinfo("Éxito", "Estudiante actualizado exitosamente")
                else:
                    messagebox.showerror("Error", "No se pudo actualizar el estudiante")
            else:
                messagebox.showerror("Error", "Estudiante no encontrado")
    
    def delete_student(self):
        id = simpledialog.askinteger("Eliminar Estudiante", "Ingrese el ID del estudiante:")
        if id is not None:
            success = self.dao.delete(id)
            if success:
                messagebox.showinfo("Éxito", "Estudiante eliminado exitosamente")
            else:
                messagebox.showerror("Error", "No se pudo eliminar el estudiante")

if __name__ == "__main__":
    root = tk.Tk()
    app = MainApp(root)
    root.mainloop()
