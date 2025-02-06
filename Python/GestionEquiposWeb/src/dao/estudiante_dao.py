from typing import List, Optional, Tuple
from mysql.connector import Error
from src.config.database import DatabaseConnection
from src.models.estudiante import Estudiante


class estudiante_dao:
    def __init__(self):
        self.db = DatabaseConnection()

    def get_all(self) -> List[Estudiante]:
        try:
            self.db.cursor.execute("SELECT * FROM estudiante")
            result = self.db.cursor.fetchall()
            return [Estudiante(row["id"], row["nombre"], row["edad"], row["grado"], row["clases"]) for row in result]
        except Error as e:
            print(e)
            return []

    def get_by_id(self, id: int) -> Optional[Estudiante]:
        try:
            self.db.cursor.execute(
                "SELECT * FROM estudiante WHERE id = %s", (id,))
            result = self.db.cursor.fetchone()
            return Estudiante(*result) if result else None
        except Error as e:
            print(e)
            return None

    def create(self, estudiante: Estudiante) -> Tuple[bool, int]:
        try:
            self.db.cursor.execute(
                "INSERT INTO estudiante (nombre,edad,grado, clases) VALUES ( %s, %s, %s,%s)", (estudiante.nombre, estudiante.edad, estudiante.grado, ",".join(estudiante.clases)))
            self.db.commit()
            return True, self.db.cursor.lastrowid
        except Error as e:
            print(e)
            return False, 0


    def update(self, estudiante: Estudiante) -> bool:
        try:
            self.db.cursor.execute(
                "UPDATE estudiante SET nombre = %s, edad = %s, grado = %s, clases = %s WHERE id = %s", (estudiante.nombre, estudiante.edad, estudiante.grado, ",".join(estudiante.clases), estudiante.id))
            self.db.commit()
            return True
        except Error as e:
            print(e)
            return False


    def delete(self, id: int) -> bool:
        try:
            self.db.cursor.execute("DELETE FROM estudiante WHERE id = %s", (id,))
            self.db.commit()
            return True
        except Error as e:
            print(e)
            return False
