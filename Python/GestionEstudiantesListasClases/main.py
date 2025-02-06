from curso import Curso
from estudiantes import estudiante

curso = Curso()


while True:
    print("1. Agregar estudiante")
    print("2. Eliminar estudiante")
    print("3. Mostrar estudiantes")
    print("4. Salir")
    opcion = input("Ingrese una opción: ")

    if opcion == "1":
        nombre = input("Ingrese el nombre del estudiante: ")
        edad = int(input("Ingrese la edad del estudiante: "))
        carrera = input("Ingrese la carrera del estudiante: ")
        try:
            estudiante = estudiante(nombre, edad, carrera)
            curso.agregarEstudiante(estudiante)
            print(f"\nEstudiante agregado correctamente\n{estudiante}")
        except:
            print("Error al agregar el estudiante")

    elif opcion == "2":
        nombre = input("Ingrese el nombre del estudiante a eliminar: ")
        estudiante = curso.buscarEstudiante(nombre)
        print(f"\nEstudiante {estudiante} elimnado correcatamente\n")
        if estudiante == None:
            print("Estudiante no encontrado")
        else:
            curso.eliminarEstudiante(estudiante)
    
    elif opcion == "3":
        curso.mostrarEstudiantes()
    elif opcion == "4":
        break

