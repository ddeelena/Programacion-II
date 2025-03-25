package edu.co.sistemagestionempleos.controller;

import edu.co.sistemagestionempleos.model.Empresa;
import edu.co.sistemagestionempleos.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresaService.getAllEmpresas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Integer id) {
        return empresaService.getEmpresaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa) {
        return empresaService.createEmpresa(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Integer id, @RequestBody Empresa empresa) {
        return empresaService.updateEmpresa(id, empresa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> deleteEmpresa(@PathVariable Integer id) {
        return empresaService.deleteEmpresa(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{username}")
    public ResponseEntity<Integer> obtenerEmpresaPorUsername(@PathVariable String username) {
        return empresaService.obtenerEmpresaIdPorUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

