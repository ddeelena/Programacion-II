import os
import sys

from mysql.connector import Error
from src.config.database import DatabaseConnection
from src.presentation.menu import Menu



# Agregar la ruta del proyecto al path
current_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.dirname(os.path.dirname(current_dir))
sys.path.append(project_root)

def crear_tabla_estudiante():
    db = DatabaseConnection()
    try:
        db.connect()
        cursor = db.get_cursor()
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS estudiante (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL,
                edad INT NOT NULL,
                grado VARCHAR(20) NOT NULL,
                clases TEXT NOT NULL
            )
        """)
        db.commit()
        print("Tabla creada exitosamente")
    except Error as e:
        print(e)


def main():
    try:
        crear_tabla_estudiante()
        menu = Menu()
        menu.show_menu()
    except Error as e:
        print(e)

if __name__ == "__main__":
    main()