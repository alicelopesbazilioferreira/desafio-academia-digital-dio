package me.desafio.academia.academiadigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import me.desafio.academia.academiadigital.model.Aluno;
import me.desafio.academia.academiadigital.model.AvaliacaoFisica;
import me.desafio.academia.academiadigital.model.form.AlunoForm;
import me.desafio.academia.academiadigital.service.impl.AlunoServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @Autowired
  private AlunoServiceImpl service;

  @PostMapping
  public Aluno create(@Valid @RequestBody AlunoForm form) {
    return service.create(form);
  }

  @GetMapping("/avaliacoes/{id}")
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
    return service.getAllAvaliacaoFisicaId(id);
  }

  @GetMapping
  @Cacheable(value = "getAll")
  public ResponseEntity<List<Aluno>> getAll(@RequestParam(value = "dataDeNascimento", required = false)
                                  String dataDeNacimento){
	  List<Aluno> lista = service.getAll(dataDeNacimento);
	  return ResponseEntity.ok()
			  //.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
			  .body(lista);
  }
  
  @GetMapping("/cancel")
  @CacheEvict(value = "getAll", allEntries = true)
  public String cancel() {
	  System.out.println("Limpando cache");
	  return "Cache Cancelado";
  }


}