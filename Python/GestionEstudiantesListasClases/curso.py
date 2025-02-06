from estudiantes import estudiante

class Curso:

    def  __init__(self):
        self.estudiantes = []

    def agregarEstudiante(self, estudiante):
        self.estudiantes.append(estudiante)

    def eliminarEstudiante(self, estudiante):
        if estudiante in self.estudiantes:  # Verificar antes de eliminar
            self.estudiantes.remove(estudiante)
        else:
            print("\nEl estudiante no está en el curso.")
    

    def mostrarEstudiantes(self):
        try:
            for estudiante in self.estudiantes:
                print(estudiante)
        except:
            print("No hay estudiantes en el curso")
    

    def __str__(self):
        return "Estudiantes: " + str(self.estudiantes)
    
    def buscarEstudiante(self, nombre):
        for estudiante in self.estudiantes:
            if estudiante.nombre == nombre:
                return estudiante
        return None