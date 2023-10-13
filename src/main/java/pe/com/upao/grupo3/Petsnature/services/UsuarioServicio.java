package pe.com.upao.grupo3.Petsnature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upao.grupo3.Petsnature.Exceptions.ContrasenaIncorrectaException;
import pe.com.upao.grupo3.Petsnature.Exceptions.ContrasenaNoValida;
import pe.com.upao.grupo3.Petsnature.Exceptions.UsuarioNoExisteException;
import pe.com.upao.grupo3.Petsnature.Exceptions.UsuarioYaExisteException;
import pe.com.upao.grupo3.Petsnature.models.Usuario;
import pe.com.upao.grupo3.Petsnature.repositories.UsuarioRepository;

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


        if(!validarContrasena(usuario.getContrasena())){
            throw new ContrasenaNoValida("La contraseña no cumple con los requisitos minimos");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(String correo, String contrasena){

        Optional<Usuario> usuarioOpt=usuarioRepository.findById(correo);
        if(usuarioOpt.isEmpty()){
            throw new UsuarioNoExisteException("Correo no valido vuelva a intentar");
        }

        Usuario usuario=usuarioOpt.get();

        if (usuario.getContrasena().equals(contrasena)){
            throw new ContrasenaIncorrectaException("La contraseña es incorrecta");
        }

        return usuario;
    }

    public static boolean validarContrasena(String contrasena) {
        int simbolos = 0;
        int numeros = 0;

        for (int i = 0; i < contrasena.length(); i++) {
            char caracter = contrasena.charAt(i);

            if ((caracter >= 33 && caracter <= 47) || (caracter >= 58 && caracter <= 64) || (caracter >= 91 && caracter <= 96)) {
                simbolos++;
            }

            if (caracter >= 48 && caracter <= 57) {
                numeros++;
            }
        }

        return simbolos > 0 && numeros > 0 && contrasena.length() > 5;
    }

}
