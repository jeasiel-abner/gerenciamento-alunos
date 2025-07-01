package br.com.gerenciamento.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.exception.EmailExistsException;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
    // quatro unitários

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Test
    public void criarELogar() throws Exception{
        Usuario user = new Usuario();
        user.setUser("Nicole");
        user.setEmail("nicole@gmail.com");
        user.setSenha("nihil2");
        String senhaTeste = "nihil2";
        
        this.serviceUsuario.salvarUsuario(user);
        senhaTeste = Util.md5(senhaTeste);

        assertNotNull(this.serviceUsuario.loginUser("Nicole", senhaTeste));
    }

    @Test
    public void criarUsuarioDuplicado() throws Exception{
        Usuario user = new Usuario();
        user.setUser("Nicolas");
        user.setEmail("nicole@gmail.com");
        user.setSenha("aero154");

        this.serviceUsuario.salvarUsuario(user);
        assertThrows(EmailExistsException.class, () -> {this.serviceUsuario.salvarUsuario(user);});
    }

    @Test
    public void logarEmUsuarioInexistente(){
        assertNull(this.serviceUsuario.loginUser("Bullet", "hydro"));
    }

    @Test
    public void senhaInvalida(){
        Usuario user = new Usuario();
        user.setUser("Lupin");
        user.setEmail("ars@gmail.com");
        user.setSenha(null);

        assertThrows(NullPointerException.class, () -> {this.serviceUsuario.salvarUsuario(user);});
    }
}
