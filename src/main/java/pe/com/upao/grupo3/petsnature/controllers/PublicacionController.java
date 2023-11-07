package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.petsnature.exceptions.CategoriaNoExistenteException;
import pe.com.upao.grupo3.petsnature.models.Informacion;
import pe.com.upao.grupo3.petsnature.models.Pregunta;
import pe.com.upao.grupo3.petsnature.models.Recomendacion;
import pe.com.upao.grupo3.petsnature.serializers.*;
import pe.com.upao.grupo3.petsnature.services.PublicacionServicio;
import pe.com.upao.grupo3.petsnature.models.Publicacion;

import java.util.List;


@RestController
public class PublicacionController{
    @Autowired
    private PublicacionServicio publicacionServicio;

    @PostMapping("descubre/crear_publicacion/{categoria}")
    public PublicacionCreadaSerializer crearPublicacion(@RequestBody PublicacionSerializer publicacionSerializer,@PathVariable String categoria){
        if (categoria.equals("Informacion")){
            Informacion informacion = new Informacion(publicacionSerializer.getUsuario(),publicacionSerializer.getTema(),publicacionSerializer.getTipoMascota(),
                    publicacionSerializer.getImg(),publicacionSerializer.getContenido(),publicacionSerializer.getEnlace(),publicacionSerializer.getTitulo(),publicacionSerializer.getDescripcion());
            publicacionServicio.crearPublicacion(informacion);
            return new InformacionCreadaSerializer(publicacionServicio.obtenerFecha(informacion.getId()),publicacionServicio.obtenerHora(informacion.getId()),
                    categoria,publicacionSerializer.getUsuario().getCorreo(),publicacionSerializer.getTema(),publicacionSerializer.getTipoMascota(),publicacionSerializer.getEnlace(),
                    publicacionSerializer.getContenido(),publicacionSerializer.getImg(),publicacionSerializer.getTitulo(),publicacionSerializer.getDescripcion());
        } else if (categoria.equals("Pregunta")) {
            Pregunta pregunta = new Pregunta(publicacionSerializer.getUsuario(),publicacionSerializer.getTema(),publicacionSerializer.getTipoMascota(),
                    publicacionSerializer.getImg(),publicacionSerializer.getContenido(),publicacionSerializer.getEnlace());
            publicacionServicio.crearPublicacion(pregunta);
            return new PreguntaCreadaSerializer(publicacionServicio.obtenerFecha(pregunta.getId()),publicacionServicio.obtenerHora(pregunta.getId()),
                    categoria,publicacionSerializer.getUsuario().getCorreo(),publicacionSerializer.getTema(),publicacionSerializer.getTipoMascota(),publicacionSerializer.getEnlace(),
                    publicacionSerializer.getContenido(),publicacionSerializer.getImg());
        } else if (categoria.equals("Recomendacion")) {
            Recomendacion recomendacion = new Recomendacion(publicacionSerializer.getUsuario(),publicacionSerializer.getTema(),publicacionSerializer.getTipoMascota(),
                    publicacionSerializer.getImg(),publicacionSerializer.getContenido(),publicacionSerializer.getEnlace());
            publicacionServicio.crearPublicacion(recomendacion);
            return new RecomendacionCreadaSerializer(publicacionServicio.obtenerFecha(recomendacion.getId()),publicacionServicio.obtenerHora(recomendacion.getId()),
                    categoria,publicacionSerializer.getUsuario().getCorreo(),publicacionSerializer.getTema(),publicacionSerializer.getTipoMascota(),publicacionSerializer.getEnlace(),
                    publicacionSerializer.getContenido(),publicacionSerializer.getImg());
        }

        throw new CategoriaNoExistenteException("La categoria no existe");

    }

    @GetMapping("descubre/{categoria}")
    public List<Publicacion> filtroCategoria(@PathVariable String categoria){
        return publicacionServicio.filtrarporCategoria(categoria);
    }
}