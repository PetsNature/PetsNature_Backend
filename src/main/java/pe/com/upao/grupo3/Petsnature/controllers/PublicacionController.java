package pe.com.upao.grupo3.Petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.Petsnature.Serializers.PublicacionSerializer;
import pe.com.upao.grupo3.Petsnature.models.Publicacion;
import pe.com.upao.grupo3.Petsnature.services.PublicacionServicio;

import java.util.List;

@RestController
public class PublicacionController {
    @Autowired
    private PublicacionServicio publicacionServicio;

    @PostMapping("descubre/crear_publicacion")
    public String crearPublicacion(@RequestBody PublicacionSerializer publicacionSerializer){
        publicacionServicio.crearPublicacion(new Publicacion(publicacionSerializer.getUsuario(),publicacionSerializer.getCategoria(),publicacionSerializer.getTema(),publicacionSerializer.getMascota(),publicacionSerializer.getRaza(),
                publicacionSerializer.getTitulo(),publicacionSerializer.getDescripcion(),publicacionSerializer.getInfo(),publicacionSerializer.getImg()));
        return "Publicación creada";
    }

    @GetMapping("descubre/preguntas")
    public List<Publicacion> filtroCategoria(){
        return publicacionServicio.filtrarporCategoria("Pregunta");
    }
}
