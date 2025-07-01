package br.com.gerenciamento.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {
    // quatro unitários

    @Autowired
    private UsuarioRepository usuarioRepository;

    @AfterEach
    public void limpar(){
        this.usuarioRepository.deleteAll();
    }

    @Test
    public void testeBuscarLogin(){
        Usuario usuario = new Usuario();
        usuario.setEmail("ars12@hotmail.com");
        usuario.setSenha("senelup");
        usuario.setUser("Admin");

        this.usuarioRepository.save(usuario);
        assertEquals("ars12@hotmail.com", this.usuarioRepository.buscarLogin("Admin", "senelup").getEmail());
    }
    
    @Test
    public void testeFindEmail(){
        Usuario usuario = new Usuario();
        usuario.setEmail("agatha@hotmail.com");
        usuario.setSenha("train98");
        usuario.setUser("Administrador");
        this.usuarioRepository.save(usuario);

        assertNotNull(this.usuarioRepository.findByEmail(usuario.getEmail()));
    }

    @Test
    public void testeLoginInexistente(){
        Usuario usuario = new Usuario();
        usuario.setEmail("agatha@hotmail.com");
        usuario.setSenha("train98");
        usuario.setUser("Administrador");
        this.usuarioRepository.save(usuario);

        assertNull(this.usuarioRepository.buscarLogin("Admin", "train98"));
    }

    @Test
    public void testeEmailInexistente(){
        Usuario usuario = new Usuario();
        usuario.setEmail("notanumber@hotmail.com");
        usuario.setSenha("0neplus0ne");
        usuario.setUser("NaN");
        this.usuarioRepository.save(usuario);

        assertNull(this.usuarioRepository.findByEmail("notanumber@gmail.com"));
    }
}
