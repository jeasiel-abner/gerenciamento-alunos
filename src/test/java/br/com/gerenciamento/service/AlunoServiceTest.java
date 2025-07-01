package br.com.gerenciamento.service;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;
//import jakarta.validation.ConstraintViolationException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoServiceTest {

    @Autowired
    private ServiceAluno serviceAluno;

    /**
    @Test
    public void getById() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Vinicius");
        aluno.setTurno(Turno.NOTURNO);
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(Status.ATIVO);
        aluno.setMatricula("123456");
        this.serviceAluno.save(aluno);

        Aluno alunoRetorno = this.serviceAluno.getById(1L);
        Assert.assertTrue(alunoRetorno.getNome().equals("Vinicius"));
    }

    @Test
    public void salvarSemNome() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setTurno(Turno.NOTURNO);
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(Status.ATIVO);
        aluno.setMatricula("123456");
        Assert.assertThrows(ConstraintViolationException.class, () -> {
                this.serviceAluno.save(aluno);});
    }

    */

    // os quatro unitários da atividade

    @Test
    public void findAllVazio(){
        assertTrue("findAll não retornou vazio, existem alunos no bd", this.serviceAluno.findAll().isEmpty());
    }

    @Test
    public void deletarAluno(){
        Aluno aluno = new Aluno();
        aluno.setNome("Marcos");
        aluno.setId(1L);
        aluno.setMatricula("222222");
        aluno.setStatus(Status.INATIVO);
        aluno.setTurno(Turno.MATUTINO);
        aluno.setCurso(Curso.DIREITO);
        this.serviceAluno.save(aluno);
        
        assertFalse(this.serviceAluno.findAll().isEmpty());
        assertEquals(1L, this.serviceAluno.getById(1L).getId());
        this.serviceAluno.deleteById(1L);
        assertTrue(this.serviceAluno.findAll().isEmpty());
    }

    @Test
    public void acharInexistente(){
        assertThrows(NoSuchElementException.class, () -> {this.serviceAluno.getById(2L);});
    }

    @Test
    public void findByAtivo(){
        Aluno aluno = new Aluno();
        aluno.setNome("Luana");
        aluno.setId(1L);
        aluno.setMatricula("012345");
        aluno.setStatus(Status.ATIVO);
        aluno.setTurno(Turno.MATUTINO);
        aluno.setCurso(Curso.ENFERMAGEM);
        this.serviceAluno.save(aluno);

        assertFalse(this.serviceAluno.findByStatusAtivo().isEmpty());
        assertTrue(this.serviceAluno.findByStatusInativo().isEmpty());

        //apenas para não influenciar os outros testes, caso necessário
        this.serviceAluno.deleteById(1L);
    }
}