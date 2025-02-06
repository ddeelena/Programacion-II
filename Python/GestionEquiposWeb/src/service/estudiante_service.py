from typing import List, Optional, Tuple
from models.estudiante import Estudiante
from dao.estudiante_dao import estudiante_dao

class EstudianteService:
    def __init__(self) -> None:
        self.dao = estudiante_dao()

    def get_all(self) -> List[Estudiante]:
        return self.dao.get_all()

    def get_by_id(self, id: int) -> Optional[Estudiante]:
        return self.dao.get_by_id(id)

    def create(self, estudiante: Estudiante) -> Tuple[bool, int]:
        return self.dao.create(estudiante)

    def update(self, estudiante: Estudiante) -> bool:
        return self.dao.update(estudiante)

    def delete(self, id: int) -> bool:
        return self.dao.delete(id)