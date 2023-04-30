package me.desafio.academia.academiadigital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.desafio.academia.academiadigital.model.Aluno;
import me.desafio.academia.academiadigital.model.AvaliacaoFisica;
import me.desafio.academia.academiadigital.model.form.AvaliacaoFisicaForm;
import me.desafio.academia.academiadigital.model.form.AvaliacaoFisicaUpdateForm;
import me.desafio.academia.academiadigital.repository.AlunoRepository;
import me.desafio.academia.academiadigital.repository.AvaliacaoFisicaRepository;
import me.desafio.academia.academiadigital.service.IAvaliacaoFisicaService;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  @Autowired
  private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

    avaliacaoFisica.setAluno(aluno);
    avaliacaoFisica.setPeso(form.getPeso());
    avaliacaoFisica.setAltura(form.getAltura());

    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica get(Long id) {
    return null;
  }

  @Override
  public List<AvaliacaoFisica> getAll() {

    return avaliacaoFisicaRepository.findAll();
  }

  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}