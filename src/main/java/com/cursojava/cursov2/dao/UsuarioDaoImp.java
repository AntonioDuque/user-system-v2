package com.cursojava.cursov2.dao;

import com.cursojava.cursov2.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements  UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Usuario getUsuario(Long id) {
        boolean check = entityManager.find(Usuario.class,id) == null;
        if(check){
           throw new RuntimeException("Usuario con el id " + id + " no existe");
        }
        return  entityManager.find(Usuario.class,id);
    }

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }



    @Override
    //Evitar inyecciones SQL
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista= entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();

        /*Si el email no existe en la bd la lista va a volver vacía y al tratar de obtener el primer valor esto va a devolver un null
         y al buscar el password de un null va a lanzar un NullPointerException*/
        if(lista.isEmpty()){
            return null;
        }
                                //Primer elemento
        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }


        return null;

    }


}
