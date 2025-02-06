class Animal:
    def __init__(self, name, sonido):
        self.name = name
        self.sonido = sonido 

    def hacer_sonido(self):
        print(self.sonido)

    def speak(self):
        raise NotImplementedError("Subclass must implement abstract method")
    