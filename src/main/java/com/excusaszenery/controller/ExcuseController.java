package com.excusaszenery.controller;

import com.excusaszenery.dto.ExcuseRequestDto;
import com.excusaszenery.model.Excuse;
import com.excusaszenery.service.ExcuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/excuses")
@CrossOrigin(origins = "*") // Para permitir pruebas desde Postman/Angular
public class ExcuseController {
    @Autowired
    private ExcuseService excuseService;

    @PostMapping
    public ResponseEntity<Excuse> create(@RequestBody ExcuseRequestDto dto){
        return ResponseEntity.ok(excuseService.createExcuse(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExcuse(@PathVariable Integer id){
        boolean deleted = excuseService.deleteExcuse(id);
        return deleted ? ResponseEntity.ok("Excusa eliminada") : ResponseEntity.badRequest().body("Excusa no encontrada");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Excuse> getOne(@PathVariable Integer id){
        return excuseService.getExcuseById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Excuse>> getAll(){
        return ResponseEntity.ok(excuseService.getAllExcuses());
    }

    @PatchMapping("/{id}/toggle-status")
    public  ResponseEntity<String> toggleStatus(@PathVariable Integer id){
        boolean updated = excuseService.toggleExcuseStatus(id);
        return updated ? ResponseEntity.ok("Visibilidad cambiada") : ResponseEntity.badRequest().body("Error al cambiar la visibilidad de la excusa");
    }

}
