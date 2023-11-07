package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.com.upao.grupo3.petsnature.exceptions.UsuarioNoExisteException;
import pe.com.upao.grupo3.petsnature.serializers.InicioSesionSerializer;
import pe.com.upao.grupo3.petsnature.serializers.UsuarioIniciadoSerializer;
import pe.com.upao.grupo3.petsnature.serializers.UsuarioSerializer;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.services.UsuarioServicio;

import java.io.File;
import java.io.IOException;


@RestController
public class UsuarioController {
    private Usuario usuarioAuten;


    @Autowired
    private UsuarioServicio usuarioServicio;
    @Value("${perfil.foto.directorio}")
    private String directorioFotos;

    /*@PostMapping("login")
    public String login( @RequestBody String correo, @RequestBody String contrasena, HttpSession session){
        Usuario usuario=usuarioServicio.iniciarSesion(correo,contrasena);
        session.setAttribute("UsuarioIniciado",usuario);
        return "Inicio de sesion exitoso";
    }*/

    @PostMapping("/login")
    public UsuarioIniciadoSerializer login(@RequestBody InicioSesionSerializer inicioSesionSerializer){
        usuarioAuten=usuarioServicio.iniciarSesion(inicioSesionSerializer.getCorreo(),inicioSesionSerializer.getConstrasena());
        UsuarioIniciadoSerializer uis= new UsuarioIniciadoSerializer(usuarioAuten.getCorreo(), usuarioAuten.getNombre(), usuarioAuten.getImgPerfil());
        //session.setAttribute("UsuarioIniciado",usuario);
        return uis;
    }

    @GetMapping("/home/logout")
    public Usuario logout(){
        Usuario authenUser=usuarioAuten;
        usuarioAuten=null;
        return authenUser;
    }


    @GetMapping("/home/perfil")
    public Usuario mySesion(){
        if (usuarioAuten==null){
            throw new UsuarioNoExisteException("No se ha inicado sesion");
        }
        return usuarioAuten;
    }

    @PostMapping("/registro")
    public UsuarioSerializer registro(@RequestBody UsuarioSerializer usuarioSerializer){
        usuarioServicio.registrarUsuario(new Usuario(usuarioSerializer.getCorreo(),usuarioSerializer.getNombre(),usuarioSerializer.getContrasena(),usuarioSerializer.getImgPerfil()));
        return usuarioSerializer;
    }

    @PostMapping("/{id}/subir-foto")
    public String subirFoto(@PathVariable String id, @RequestParam("imgPerfil") MultipartFile foto) throws IOException {
        Usuario usuario = usuarioServicio.encontrarUsuario(id).orElse(null);
        if (usuario==null){
            return "esta vacia el usuario";
        }
        if (usuario != null && !foto.isEmpty()) {
            String rutaFoto = directorioFotos + File.separator + foto.getOriginalFilename();
            foto.transferTo(new File(rutaFoto));
            usuarioServicio.cambiarImgPerfil(id,rutaFoto);
        }
        return "redirect:/perfil/" + id;
    }
}
