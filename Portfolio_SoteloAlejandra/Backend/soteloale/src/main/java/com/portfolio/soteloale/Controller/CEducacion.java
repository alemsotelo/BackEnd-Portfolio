/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.soteloale.Controller;

import com.portfolio.soteloale.Dto.DtoEducacion;
import com.portfolio.soteloale.Entity.Educacion;
import com.portfolio.soteloale.Security.Controller.Mensaje;
import com.portfolio.soteloale.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class CEducacion {

    @Autowired
    SEducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoeducacion) {
        if (StringUtils.isBlank(dtoeducacion.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existsByNombreEdu(dtoeducacion.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("Nombre de educacion ya existente"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoeducacion.getNombreEdu(), dtoeducacion.getDescripcionEdu());
        sEducacion.save(educacion);

        return new ResponseEntity(new Mensaje("Educacion agregada"), HttpStatus.OK);
        }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoeducacion) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existsByNombreEdu(dtoeducacion.getNombreEdu()) && sEducacion.getByNombreEdu(dtoeducacion.getNombreEdu()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre de educación ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoeducacion.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("Ese campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEdu(dtoeducacion.getNombreEdu());
        educacion.setDescripcionEdu((dtoeducacion.getDescripcionEdu()));

        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educación actualizada"), HttpStatus.OK);

    }
}
