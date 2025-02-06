class estudiante :
    def __init__(self, nombre, edad, carrera):
        self.nombre = nombre
        self.edad = edad
        self.carrera = carrera
    def __str__(self):
        return "Nombre: " + self.nombre + ", Edad: " + str(self.edad) + ", Carrera: " + self.carrera
