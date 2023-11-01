package pe.com.upao.grupo3.Petsnature.controllers;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.Petsnature.Exceptions.UsuarioNoExisteException;
import pe.com.upao.grupo3.Petsnature.Serializers.InicioSesionSerializer;
import pe.com.upao.grupo3.Petsnature.Serializers.UsuarioSerializer;
import pe.com.upao.grupo3.Petsnature.models.Usuario;
import pe.com.upao.grupo3.Petsnature.services.UsuarioServicio;


@RestController
public class UsuarioController {
    private Usuario usuarioAuten;


    @Autowired
    private UsuarioServicio usuarioServicio;

    /*@PostMapping("login")
    public String login( @RequestBody String correo, @RequestBody String contrasena, HttpSession session){
        Usuario usuario=usuarioServicio.iniciarSesion(correo,contrasena);
        session.setAttribute("UsuarioIniciado",usuario);
        return "Inicio de sesion exitoso";
    }*/

    @PostMapping("petsnature/login")
    public String login(@RequestBody InicioSesionSerializer inicioSesionSerializer){
        usuarioAuten=usuarioServicio.iniciarSesion(inicioSesionSerializer.getCorreo(),inicioSesionSerializer.getConstrasena());
        //session.setAttribute("UsuarioIniciado",usuario);
        return "Inicio de sesion exitoso";
    }

    @GetMapping("petsnature/home/logout")
    public String logout(){
        usuarioAuten=null;
        return "Sesion cerrada";
    }


    @GetMapping("petsnature/home/perfil")
    public Usuario mySesion(){
        if (usuarioAuten==null){
            throw new UsuarioNoExisteException("No se ha inicado sesion");
        }
        return usuarioAuten;
    }

    @PostMapping("petsnature/registro")
    public String registro(@RequestBody UsuarioSerializer usuarioSerializer){
        usuarioServicio.registrarUsuario(new Usuario(usuarioSerializer.getCorreo(),usuarioSerializer.getNombre(),usuarioSerializer.getContrasena(),usuarioSerializer.getImgPerfil()));
        return "Registro Exitoso";
    }



}
