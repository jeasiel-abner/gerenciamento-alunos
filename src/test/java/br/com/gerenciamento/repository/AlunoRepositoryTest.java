package br.com.gerenciamento.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoRepositoryTest {
    // quatro unitários

    @Autowired
    private AlunoRepository alunoRepository;    

    @AfterEach
    public void limpar(){
        this.alunoRepository.deleteAll();
    }

    @Test
    public void testeSave(){
        Aluno aluno = new Aluno();
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setMatricula("1234");
        aluno.setNome("Luciano");
        aluno.setStatus(Status.ATIVO);
        aluno.setTurno(Turno.MATUTINO);

        this.alunoRepository.save(aluno);
        assertFalse(this.alunoRepository.findAll().isEmpty());
    }

    @Test
    public void findAtivos(){
        Aluno aluno1 = new Aluno();
        aluno1.setCurso(Curso.BIOMEDICINA);
        aluno1.setMatricula("2345");
        aluno1.setNome("Gabriel");
        aluno1.setStatus(Status.ATIVO);
        aluno1.setTurno(Turno.MATUTINO);
        this.alunoRepository.save(aluno1);

        assertFalse(this.alunoRepository.findByStatusAtivo().isEmpty());
    }

    @Test
    public void findInativos(){
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Marcelo");
        aluno1.setStatus(Status.INATIVO);
        aluno1.setCurso(Curso.DIREITO);
        aluno1.setMatricula("1324");
        aluno1.setTurno(Turno.NOTURNO);
        this.alunoRepository.save(aluno1);
        
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Carol");
        aluno2.setStatus(Status.ATIVO);
        aluno2.setCurso(Curso.DIREITO);
        aluno2.setMatricula("4231");
        aluno2.setTurno(Turno.NOTURNO);
        this.alunoRepository.save(aluno2);

        assertEquals(1, this.alunoRepository.findByStatusInativo().size());
    }

    @Test
    public void findNomeIgnoreCase(){
        Aluno aluno = new Aluno();
        aluno.setNome("Beatriz");
        aluno.setStatus(Status.ATIVO);
        aluno.setCurso(Curso.ENFERMAGEM);
        aluno.setMatricula("8423");
        aluno.setTurno(Turno.NOTURNO);
        this.alunoRepository.save(aluno);

        assertFalse(this.alunoRepository.findByNomeContainingIgnoreCase("beatriz").isEmpty());
    }
}
