package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.petsnature.exceptions.CategoriaNoExistenteException;
import pe.com.upao.grupo3.petsnature.models.Informacion;
import pe.com.upao.grupo3.petsnature.models.Pregunta;
import pe.com.upao.grupo3.petsnature.models.Recomendacion;
import pe.com.upao.grupo3.petsnature.services.PublicacionServicio;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionCreadaSerializer;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer;
import java.util.List;


@RestController
public class PublicacionController{
    @Autowired
    private PublicacionServicio publicacionServicio;

    @PostMapping("descubre/crear_publicacion/{categoria}")
    public PublicacionCreadaSerializer crearPublicacion(@RequestBody PublicacionSerializer publicacionSerializer,@PathVariable String categoria){
        if (categoria.equals("Informacion")){
            Informacion informacion = new Informacion(publicacionSerializer.getUsuario(),publicacionSerializer.getTema(),publicacionSerializer.getMascota(),publicacionSerializer.getRaza(),
                    publicacionSerializer.getImg(),publicacionSerializer.getTitulo(),publicacionSerializer.getDescripcion(),publicacionSerializer.getInfo());
            publicacionServicio.crearPublicacion(informacion);
        } else if (categoria.equals("Pregunta")) {
            Pregunta pregunta = new Pregunta(publicacionSerializer.getUsuario(),publicacionSerializer.getTema(),publicacionSerializer.getMascota(),publicacionSerializer.getRaza(),
                    publicacionSerializer.getImg(),publicacionSerializer.getInfo());
            publicacionServicio.crearPublicacion(pregunta);
        } else if (categoria.equals("Recomendacion")) {
            Recomendacion recomendacion = new Recomendacion(publicacionSerializer.getUsuario(),publicacionSerializer.getTema(),publicacionSerializer.getMascota(),publicacionSerializer.getRaza(),
                    publicacionSerializer.getImg(),publicacionSerializer.getInfo());
            publicacionServicio.crearPublicacion(recomendacion);
        }
        else {
            throw new CategoriaNoExistenteException("La categoria no existe");
        }
        return new PublicacionCreadaSerializer(categoria,publicacionSerializer.getUsuario().getCorreo(),publicacionSerializer.getTema(),publicacionSerializer.getMascota(),publicacionSerializer.getRaza(),
                publicacionSerializer.getTitulo(),publicacionSerializer.getDescripcion(),publicacionSerializer.getInfo(),publicacionSerializer.getImg());
    }

    @GetMapping("descubre/{categoria}")
    public List<Publicacion> filtroCategoria(@PathVariable String categoria){
        return publicacionServicio.filtrarporCategoria(categoria);
    }
}