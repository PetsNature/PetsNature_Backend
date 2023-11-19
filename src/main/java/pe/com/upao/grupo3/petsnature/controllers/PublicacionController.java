package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.petsnature.exceptions.UsuarioNoExisteException;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.serializers.*;
import pe.com.upao.grupo3.petsnature.services.PublicacionServicio;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.services.UsuarioServicio;

import java.util.List;
import java.util.Optional;


@RestController
public class PublicacionController{
    @Autowired
    private PublicacionServicio publicacionServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("descubre/{id}/crear_publicacion")
    public PublicacionCreadaSerializer crearPublicacion(@RequestBody CrearPublicacionSerializer crearPublicacionSerializer, @PathVariable Long id){
        Usuario usuario =usuarioServicio.encontrarUsuario(id);
        Publicacion publicacion=new Publicacion(usuario, crearPublicacionSerializer.getCategoria(),crearPublicacionSerializer.getTema(),
                crearPublicacionSerializer.getTipoMascota(),crearPublicacionSerializer.getImg(),crearPublicacionSerializer.getContenido(),
                crearPublicacionSerializer.getEnlace());

        publicacionServicio.crearPublicacion(publicacion);

        return new PublicacionCreadaSerializer(publicacion.getFecha(),publicacion.getHora(),publicacion.getCategoria(),
                publicacion.getUsuario().getId(),publicacion.getTema(),publicacion.getTipoMascota(),publicacion.getEnlace(),
                publicacion.getContenido(),publicacion.getImg(),publicacion.getReacciones());
    }

    @GetMapping("descubre/{categoria}")
    public List<PublicacionSerializer> filtroCategoria(@PathVariable String categoria){
        return publicacionServicio.filtrarporCategoria(categoria);
    }

    @GetMapping("descubre/{id}/mis_publicaciones")
    public List<PublicacionSerializer> misPublicaciones(@PathVariable Long id){
        Usuario usuario=usuarioServicio.encontrarUsuario(id);
        return publicacionServicio.verMisPublicaciones(usuario);
    }
}