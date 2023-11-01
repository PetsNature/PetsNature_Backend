package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.petsnature.services.PublicacionServicio;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionCreadaSerializer;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer;
import java.util.List;


@RestController
public class PublicacionController{
    @Autowired
    private PublicacionServicio publicacionServicio;

    @PostMapping("descubre/crear_publicacion")
    public PublicacionCreadaSerializer crearPublicacion(@RequestBody PublicacionSerializer publicacionSerializer){
        publicacionServicio.crearPublicacion(new Publicacion(publicacionSerializer.getUsuario(),publicacionSerializer.getCategoria(),publicacionSerializer.getTema(),publicacionSerializer.getMascota(),publicacionSerializer.getRaza(),
                publicacionSerializer.getTitulo(),publicacionSerializer.getDescripcion(),publicacionSerializer.getInfo(),publicacionSerializer.getImg()));
        return new PublicacionCreadaSerializer(publicacionSerializer.getUsuario().getCorreo(),publicacionSerializer.getCategoria(),publicacionSerializer.getTema(),publicacionSerializer.getMascota(),publicacionSerializer.getRaza(),
                publicacionSerializer.getTitulo(),publicacionSerializer.getDescripcion(),publicacionSerializer.getInfo(),publicacionSerializer.getImg());
    }

    @GetMapping("descubre/preguntas")
    public List<Publicacion> filtroCategoria(){
        return publicacionServicio.filtrarporCategoria("Pregunta");
    }
}
