package pe.com.upao.grupo3.petsnature.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upao.grupo3.petsnature.exceptions.ContrasenaIncorrectaException;
import pe.com.upao.grupo3.petsnature.exceptions.ContrasenaNoValidaException;
import pe.com.upao.grupo3.petsnature.exceptions.UsuarioNoExisteException;
import pe.com.upao.grupo3.petsnature.exceptions.UsuarioYaExisteException;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario){

        Optional<Usuario> usuarioOptional=usuarioRepository.findById(usuario.getCorreo());

        if (usuarioOptional.isPresent()){
            throw new UsuarioYaExisteException("El correo indicado ya esta registrado");
        }


        if(!usuario.validarContrasena()){
            throw new ContrasenaNoValidaException("La contraseña no cumple con los requisitos minimos");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(String correo, String contrasena){

        Optional<Usuario> usuarioOpt=usuarioRepository.findById(correo);
        if(usuarioOpt.isEmpty()){
            throw new UsuarioNoExisteException("Correo no valido vuelva a intentar");
        }

        Usuario usuario=usuarioOpt.get();

        if (!usuario.getContrasena().equals(contrasena)){
            throw new ContrasenaIncorrectaException("La contraseña es incorrecta");
        }

        return usuario;
    }

    public Optional<Usuario> encontrarUsuario(String correo){
        return usuarioRepository.findById(correo);
    }

    @Transactional
    public void cambiarImgPerfil(String correo, String img){

        Usuario usuario = usuarioRepository.findById(correo).orElse(null);

        if (usuario != null) {
            usuario.setImgPerfil(img);
        }
    }


}
