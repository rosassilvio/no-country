package com.nocountry.servicesImpl;

import com.nocountry.models.Usuario;
import com.nocountry.models.UsuarioRol;
import com.nocountry.repository.RolRepository;
import com.nocountry.repository.UsuarioRepository;
import com.nocountry.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository repository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;

        this.rolRepository = rolRepository;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario, Set<UsuarioRol> usuarioRols) throws Exception {

        Usuario usuarioLocal= usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya está registrado");
        }
        else {
            for(UsuarioRol usuarioRol : usuarioRols){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRols().addAll(usuarioRols);
            usuarioLocal=usuarioRepository.save(usuario);
        }

        return usuarioLocal;
    }

    @Override
    public Usuario getUsuario(String usuario) {
        return usuarioRepository.findByUsername(usuario);
    }

    @Override
    public void deleteUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public ResponseEntity<Usuario> update(Usuario usuario) {
            Usuario obj =usuarioRepository.save(usuario);
            return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
    }
}
