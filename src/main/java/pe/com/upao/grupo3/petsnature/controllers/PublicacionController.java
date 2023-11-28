package pe.com.upao.grupo3.petsnature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pe.com.upao.grupo3.petsnature.models.Comentario;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.serializers.*;
import pe.com.upao.grupo3.petsnature.services.ComentarioService;
import pe.com.upao.grupo3.petsnature.services.PublicacionService;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.services.UsuarioService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@RestController
@CrossOrigin(origins = "https://petsnature-frontend.netlify.app")
@Tag(name = "Publicacion")
public class PublicacionController{
    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ComentarioService comentarioService;

    @Value("${publicaciones.imagenes}")
    private String rutaImagenes;

    /*@PostMapping("/descubre/{id}/crear_publicacion")
    public PublicacionCreadaSerializer crearPublicacion(@RequestParam("publicacion") String datosJson, @PathVariable Long id, @RequestParam(value = "imagen",required = false) MultipartFile img) throws IOException {
        Usuario usuario = usuarioService.encontrarUsuario(id);
        Publicacion publicacion;
        ObjectMapper objectMapper = new ObjectMapper();
        CrearPublicacionSerializer crearPublicacionSerializer = objectMapper.readValue(datosJson, CrearPublicacionSerializer.class);
        if (img !=null){;
            String rutaImg= rutaImagenes + File.separator +img.getOriginalFilename();
            img.transferTo(new File(rutaImg));
            crearPublicacionSerializer.setImg(rutaImg);
        }
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
    }*/

    @PostMapping("/descubre/{id}/crear_publicacion")
    public PublicacionCreadaSerializer crearPublicacion(@RequestBody CrearPublicacionSerializer crearPublicacionSerializer, @PathVariable Long id) throws IOException {
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

    @GetMapping("descubre/{categoria}/{tipoMascota}/{raza}/{tema}")
    public List<PublicacionSerializer> filtroPublicacion(@PathVariable String categoria, @PathVariable String tipoMascota, @PathVariable String raza,@PathVariable String tema) throws IOException{
        List<PublicacionSerializer> publicaciones=publicacionService.filtrarPublicaciones(categoria,tipoMascota,raza,tema);
        return publicaciones;
    }
}