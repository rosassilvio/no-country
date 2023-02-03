package com.nocountry.controllers;

import com.nocountry.models.Rol;
import com.nocountry.models.Usuario;
import com.nocountry.models.UsuarioRol;
import com.nocountry.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/usuarios/")
public class UsuarioController {

    @Autowired
    private  UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAll(){
        return usuarioService.getAllUsuarios();
    }

    @PutMapping
    public ResponseEntity<Usuario> update (@RequestBody Usuario usuario ){
        return usuarioService.update(usuario);
    }


    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        return usuarioService.saveUsuario(usuario,usuarioRoles);
    }

    @GetMapping("{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.getUsuario(username);
    }

    @DeleteMapping("{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.deleteUsuario(usuarioId);
    }
}
