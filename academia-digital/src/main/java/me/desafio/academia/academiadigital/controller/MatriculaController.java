package me.desafio.academia.academiadigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import me.desafio.academia.academiadigital.model.Matricula;
import me.desafio.academia.academiadigital.model.form.MatriculaForm;
import me.desafio.academia.academiadigital.service.impl.MatriculaServiceImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

  @Autowired
  private MatriculaServiceImpl service;

  @PostMapping
  public Matricula create(@Valid @RequestBody MatriculaForm form) {
    return service.create(form);
  }

  @GetMapping
  public ResponseEntity<List<Matricula>> getAll(@RequestParam(value = "bairro", required = false) String bairro) {
    
	  List<Matricula> lista = service.getAll(bairro);
	  
	  return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(lista);
  }

}