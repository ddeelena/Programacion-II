
estado : bool = True

#Cambiar el estado de un semaforo
def cambiarEstado (estado):
    if estado :
        print("El semaforo verde, puede avanzar")
        estado = True
        if estado :
            print("El semaforo esta en amarillo, baje la velocidad ")
            estado = True
            if estado :
                print ("Detenga e vehiculo, el semaforo esta en rojo")
            else:
                print("El semaforo esta en amarillo")
        else:
            print("El semaforo esta en verde, avance")
    else:
        print("El vehiculo debe estar detenido")
            
#Enciende el semaforo
def encenderSemaforo(estado):
    print("El semaforo esta encendido")
    confirmacion = input("desea apagarlo?")
    if confirmacion == "si":
        estado = False

    if estado :
        print("El semaforo esta encendido")
        cambiarEstado(estado)
        confirmacion = input("desea apagarlo?")
        if confirmacion == "si" :
            estado = False
            print("Semaforo apagado")
    else:
        print("El semaforo esta apagado")
        confirmacion = input("desea encenderlo?")
        if confirmacion == "si" :
            estado = True
            cambiarEstado(estado)


#Que tipo de dato ingreso la persona
def tipoDeDato (tipo_dato):
    print(type(tipo_dato))



#Palabra es palindroma, 
def esPalidroma (cadena : str):
    for i in len(cadena) :
        cadena[i]


# Extraer una palabra de un texto
def extraerPalabra(frase : str):
    descomprimida = frase.split()
    print(f"La frase que escribiste tiene {len(descomprimida)} palabras")
    n_palabra = int(input("Ingrese el n° de la palabra que desea\n"))
    if n_palabra <= len(descomprimida) :
        print(descomprimida[n_palabra-1])
    else:
        print("No ingresaste un numero de palabra valido")

#extraerPalabra(input("Escriba una frase:\n"))

def comprarClaro (precio : float):
    servicioLista : str = "1:Paquete \n 2:Minutos \n 3:Redes \n 4: Salir"
    comprar : bool = True
    while comprar :
        print(servicioLista)
        servicio : int = int(input("Cual desea comprar"))
        match servicio:
            case 1: 
                precio += 4000
                print("Servicio de paquete comprado")
            case 2:
                precio += 3000
                print("Servicio de minutos comprado")
            case 3:
                precio += 3000
                print("Servicio de redes comprado")
            case 4:
                break
    
    return precio


def comprarMovistar (precio : float):
    servicioLista : str = "1:Paquete \n 2:Minutos \n 3:Redes \n 4: Salir"
    comprar : bool = True
    while comprar :
        print(servicioLista)
        servicio : int = int(input("Cual desea comprar"))
        match servicio:
            case 1: 
                precio += 4000
                print("Servicio de paquete comprado")
            case 2:
                precio += 3000
                print("Servicio de minutos comprado")
            case 3:
                precio += 3000
                print("Servicio de redes comprado")
            case 4:
                break
    
    return precio

def comprarTigo (precio : float):
    servicioLista : str = "1:Paquete \n 2:Minutos \n 3:Redes \n 4: Salir"
    comprar : bool = True
    while comprar :
        print(servicioLista)
        servicio : int = int(input("Cual desea comprar"))
        match servicio:
            case 1: 
                precio += 4000
                print("Servicio de paquete comprado")
            case 2:
                precio += 3000
                print("Servicio de minutos comprado")
            case 3:
                precio += 3000
                print("Servicio de redes comprado")
            case 4:
                comprar = False
    
    return precio

def comprarServicio():
    print("Bienvenido !!")
    precio : float = 0
    operadores_lista = "1: Claro \n 2: Movistar \n 3: Tigo \n 4:Salir"

    comprar : bool = True
    while comprar :
        print(operadores_lista)
        operador : int = int(input("Que operador desea comprar\n\n"))
        match operador:
            case 1 :
                precio += comprarClaro(precio)
            case 2: 
                precio+= comprarMovistar(precio)
            case 3: 
                precio+= comprarTigo(precio)
            case 4: 
                print(f"\nEl valor de su compra es de: {precio}")
                comprar = False

comprarServicio()
