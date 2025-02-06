from typing import Any
import mysql.connector
from mysql.connector import Error
from mysql.connector.cursor import MySQLCursor

class DatabaseConnection:
    """Clase para gestionar la conexión a la base de datos"""
    _instance = None

    def __new__(cls) -> 'DatabaseConnection':
        """Implementa el patrón Singleton"""
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

    def __init__(self) -> None:
        """Inicializa las credenciales de conexión y establece la conexión"""
        if not hasattr(self, "conn") or self.conn is None:
            self.config = {
                'host': 'localhost',
                'user': 'root',
                'password': 'Naranjo_0312',
                'database': 'inventario'
            }
            self.conn = None
            self.cursor = None
            self.connect()  # 🔹 Intentar conectar en la inicialización

    def connect(self) -> None:
        """Establece la conexión con la base de datos"""
        try:
            print("🔄 Intentando conectar a MySQL...")  # Depuración
            self.conn = mysql.connector.connect(**self.config)

            if self.conn.is_connected():
                self.cursor = self.conn.cursor(dictionary=True)
                print("✅ Conexión exitosa.")  # Depuración
            else:
                print("⚠️ No se pudo conectar a MySQL.")  # Depuración
                self.conn = None
                self.cursor = None

        except Error as e:
            print(f"❌ Error al conectar a MySQL: {e}")
            self.conn = None
            self.cursor = None  # Para evitar problemas

    def disconnect(self) -> None:
        """Cierra la conexión con la base de datos"""
        if self.conn and self.conn.is_connected():
            if self.cursor:
                self.cursor.close()
            self.conn.close()
            print("🔌 Conexión cerrada.")  # Depuración

    def get_cursor(self) -> MySQLCursor:
        """Retorna el cursor asegurando que la conexión está activa"""
        if not self.conn or not self.conn.is_connected():
            print("🔄 Conexión no encontrada. Intentando reconectar...")
            self.connect()

        if self.cursor is None:
            raise Exception("❌ Error: No se pudo obtener un cursor.")

        return self.cursor

    def commit(self) -> None:
        """Confirma los cambios en la base de datos"""
        if not self.conn or not self.conn.is_connected():
            print("⚠ Conexión perdida. Intentando reconectar...")
            self.connect()
        
        if self.conn and self.conn.is_connected():
            self.conn.commit()
        else:
            raise Exception("❌ No se pudo confirmar la transacción porque no hay conexión con la base de datos.")

