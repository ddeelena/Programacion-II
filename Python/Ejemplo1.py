

puede_volar = input("Puede volar?")
if puede_volar == "SI" : 
    es_humano = input("Es humano?")
    if es_humano == "SI": 
        tiene_mascara = input("Tiene mascara?")
        if tiene_mascara=="SI" : print("Es ironman")
        else: print("Capitan america")
    else: 
        tiene_mascara = input("Tiene mascara?")
        if tiene_mascara == "SI" : print("Ronan Acuser")
        else:
            print("Eres tÚ")
else:
    print("no sabe")







