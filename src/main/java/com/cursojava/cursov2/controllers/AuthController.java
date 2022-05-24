package com.cursojava.cursov2.controllers;

import com.cursojava.cursov2.dao.UsuarioDao;
import com.cursojava.cursov2.models.Usuario;
import com.cursojava.cursov2.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    //@RequestMapping(value = "api/login", method = RequestMethod.POST)
    @PostMapping(value ="api/login")
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

}
