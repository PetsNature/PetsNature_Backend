package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.petsnature.models.Comentario;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.serializers.*;
import pe.com.upao.grupo3.petsnature.services.ComentarioService;
import pe.com.upao.grupo3.petsnature.services.PublicacionService;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.services.UsuarioService;

import java.util.List;


@RestController
public class PublicacionController{
    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("descubre/{id}/crear_publicacion")
    public PublicacionCreadaSerializer crearPublicacion(@RequestBody CrearPublicacionSerializer crearPublicacionSerializer, @PathVariable Long id){
        Usuario usuario = usuarioService.encontrarUsuario(id);
        Publicacion publicacion;
        if (crearPublicacionSerializer.getRazaAnimal()==null){
            publicacion=new Publicacion(usuario, crearPublicacionSerializer.getCategoria(),crearPublicacionSerializer.getTema(),
                    crearPublicacionSerializer.getTipoMascota(),crearPublicacionSerializer.getImg(),crearPublicacionSerializer.getContenido(),
                    crearPublicacionSerializer.getEnlace());
            publicacionService.crearPublicacion(publicacion);
        }
        else {
            publicacion=new Publicacion(usuario, crearPublicacionSerializer.getCategoria(),crearPublicacionSerializer.getTema(),
                    crearPublicacionSerializer.getTipoMascota(),crearPublicacionSerializer.getImg(),crearPublicacionSerializer.getContenido(),
                    crearPublicacionSerializer.getEnlace(),crearPublicacionSerializer.getRazaAnimal());
            publicacionService.crearPublicacion(publicacion);
        }


        return new PublicacionCreadaSerializer(publicacion.getFecha(),publicacion.getHora(),publicacion.getCategoria(),
                publicacion.getUsuario().getId(),publicacion.getTema(),publicacion.getTipoMascota(),publicacion.getEnlace(),
                publicacion.getContenido(),publicacion.getImg(),publicacion.getReacciones(),publicacion.getRaza());
    }

    /*@GetMapping("descubre/{categoria}")
    public List<PublicacionSerializer> filtroCategoria(@PathVariable String categoria){
        return publicacionService.filtrarporCategoria(categoria);
    }*/

    @GetMapping("descubre/{id}/mis_publicaciones")
    public List<PublicacionSerializer> misPublicaciones(@PathVariable Long id){
        Usuario usuario= usuarioService.encontrarUsuario(id);
        return publicacionService.verMisPublicaciones(usuario);
    }

    @PostMapping("descubre/{idU}/comentar/{idP}")
    public ComentarioCreadoSerializer comentar(@RequestBody String contenido,@PathVariable Long idU,@PathVariable Long idP){
        Usuario usuario=usuarioService.encontrarUsuario(idU);
        Publicacion publicacion=publicacionService.encontrarPublicacion(idP);
        Comentario comentario=new Comentario(contenido,usuario,publicacion);
        comentarioService.comentar(comentario);
        return new ComentarioCreadoSerializer(comentario.getContenido(),comentario.getId(),comentario.getUsuario().getId(),comentario.getPublicacion().getId(),null);
    }
    @GetMapping("descubre/{id}/ver_comentarios")
    public List<ComentarioSerializer> verComentarios(@PathVariable Long id){
       Publicacion publicacion=publicacionService.encontrarPublicacion(id);
       return comentarioService.findAllByPublicacion(publicacion);
    }

    @PutMapping("descubre/{id}/Reaccionar")
    public int reaccionarPublicacion(@PathVariable Long id){
        return publicacionService.reaccionarPublicacion(id);
    }
    /*@GetMapping("descubre/{categoria}/{tipoMascota}")
    public List<PublicacionSerializer> filtroTipoMascota(@PathVariable String categoria, @PathVariable String tipoMascota){
        return publicacionService.filtrarPorTipoMascota(categoria,tipoMascota);
    }

    @GetMapping("descubre/{categoria}/{tipoMascota}/{raza}")
    public List<PublicacionSerializer> filtroRaza(@PathVariable String categoria, @PathVariable String tipoMascota, @PathVariable String raza){
        return publicacionService.filtrarPorTipoMascotaYRaza(categoria,tipoMascota,raza);
    }

    @GetMapping("descubre/{categoria}/{tipoMascota}/{raza}/{tema}")
    public List<PublicacionSerializer> filtroTema(@PathVariable String categoria, @PathVariable String tipoMascota, @PathVariable String raza,@PathVariable String tema){
        return publicacionService.filtrarporTema(categoria,tipoMascota,raza,tema);

    }*/

    @GetMapping("descubre/{categoria}/{tipoMascota}/{raza}/{tema}")
    public List<PublicacionSerializer> filtroPublicacion(@PathVariable String categoria, @PathVariable String tipoMascota, @PathVariable String raza,@PathVariable String tema){
        return publicacionService.filtrarPublicaciones(categoria,tipoMascota,raza,tema);
    }
}