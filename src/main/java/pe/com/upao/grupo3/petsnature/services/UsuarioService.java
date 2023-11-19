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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario){

        Optional<Usuario> usuarioOptional=usuarioRepository.findByCorreo(usuario.getCorreo());

        if (usuarioOptional.isPresent()){
            throw new UsuarioYaExisteException("El correo indicado ya esta registrado");
        }


        if(!usuario.validarContrasena()){
            throw new ContrasenaNoValidaException("La contraseña no cumple con los requisitos minimos");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(String correo, String contrasena){

        Optional<Usuario> usuarioOpt=usuarioRepository.findByCorreo(correo);
        if(usuarioOpt.isEmpty()){
            throw new UsuarioNoExisteException("Correo no valido vuelva a intentar");
        }

        Usuario usuario=usuarioOpt.get();

        if (!usuario.getContrasena().equals(contrasena)){
            throw new ContrasenaIncorrectaException("La contraseña es incorrecta");
        }

        return usuario;
    }

    public Usuario encontrarUsuario(Long id){
        Usuario usuario =usuarioRepository.findById(id).orElse(null);

        if (usuario==null){
            throw new UsuarioNoExisteException("Este usuario no existe");
        }
        return usuario;
    }

    @Transactional
    public void cambiarImgPerfil(Long id, String img){

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setImgPerfil(img);
        }
    }


}
