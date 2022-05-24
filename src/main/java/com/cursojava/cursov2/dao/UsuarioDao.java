package com.cursojava.cursov2.dao;

import com.cursojava.cursov2.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioDao{

    List<Usuario> getUsuarios();

    Usuario getUsuario(Long id);


    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
