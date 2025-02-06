from typing import List, Tuple, Set, Dict, Any

Lista_productos : List[str]= ["Pan", "Leche", "Huevos"]
Lista_precio_tuplas : Tuple[Any]= [('Pan', 1.5), ('Leche', 2.0), ('Huevos', 3.0)]
Lista_diccionarios = [{'nombre': 'Pan', 'precio': 1.5, 'categoria': 'Alimentos'}]
clientes : List[Dict[str,str]] = [{
    "nombre": "juan",
    "direccion": "calle 1",
    "gmail": "juan56@sjuss.com"
    },{
    "nombre": "pedro",
    "direccion": "calle 2",
    "gmail": "pedro@djso.ocns"
    }]

clientesEspera : List[str] = ["Juan", "Pedro", "Maria", "Carlos"]
clientesAtendidos : List[str] = []

def reverso (lista : List[any])-> List[any]:
    lista.reverse()
    return  lista # reversed() invierte la lista
  #   return lista[::-1]  # [::-1] invierte la lista

#print(reverso(Lista_productos))

def sumarH (Lista_precio_tuplas : tuple[any])-> tuple[any] :
    nueva_tupla = tuple()
    for i in Lista_precio_tuplas:
        nueva_tupla += (i[0], i[1]+1,"H")  #No se puede modidifcar una tupla, entonces se crea una nueva y se reasigna
    return nueva_tupla

#print(sumarH(Lista_precio_tuplas))

def reversarTupla (Lista_reverse : tuple[any])-> tuple[any]:
    return Lista_reverse[::-1]

#print(reversarTupla(sumarH(Lista_precio_tuplas)))

def buscarCliente (nombre : str, lista : dict[str,str] )-> str:
    for i in lista:
        if i["nombre"] == nombre:
            return i
    return "No se encontro el cliente"

#print(buscarCliente("juan",clientes))

def colaClientes (espera: List[Dict[str,str]]) -> None:
    atentidos : List[str] = []

    while True:
        i: int =0
        opcion : int = int(input("Ingrese 1: Para atenter a un cliente \n Ingrese 2: "
              "Para añadir un nuevo cliente a la cola \n 3: Para salir\n"))

        if opcion == 1:
            if len(espera) == 0:
                print("No hay clientes en espera")
            else:
                print(f"Atendiendo a {espera[i]}")
                atentidos.append(espera[i])
                espera.pop(i)
                i += 1
                print(f"Clientes por atender: {len(espera)}")

        elif opcion == 2:
            nombre = input("Ingrese el nombre del cliente")
            direccion = input("Ingrese la direccion del cliente")
            gmail = input("Ingrese el gmail del cliente")

            espera.append(nombre, direccion, gmail)

        elif opcion == 3:
            break



colaClientes(clientes)