package com.nocountry.services;

import com.nocountry.models.Usuario;
import com.nocountry.models.UsuarioRol;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    public Usuario saveUsuario(Usuario usuario, Set<UsuarioRol>usuarioRols)throws Exception;
    public Usuario getUsuario(String usuario);
    public void deleteUsuario(Long usuarioId);

    public List<Usuario> getAllUsuarios();

    public ResponseEntity<Usuario> update (Usuario usuario );


    }



