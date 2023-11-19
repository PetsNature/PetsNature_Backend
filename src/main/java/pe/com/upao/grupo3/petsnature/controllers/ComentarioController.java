package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.upao.grupo3.petsnature.models.Comentario;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.serializers.ComentarioCreadoSerializer;
import pe.com.upao.grupo3.petsnature.serializers.ComentarioSerializer;
import pe.com.upao.grupo3.petsnature.services.ComentarioService;
import pe.com.upao.grupo3.petsnature.services.UsuarioService;

import java.util.List;

@RestController
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/responder/{idC}/{idA}")
    public ComentarioCreadoSerializer responder(@RequestBody String contenido, @PathVariable Long idC,@PathVariable Long idA){
        Comentario comentario=comentarioService.findById(idC);
        Usuario usuario=usuarioService.encontrarUsuario(idA);
        Comentario respuesta=new Comentario(contenido,usuario,comentario);
        comentarioService.comentar(respuesta);

        return new ComentarioCreadoSerializer(respuesta.getContenido(),respuesta.getId(),respuesta.getUsuario().getId(),null,respuesta.getComentarioPadre().getId());
    }

    @GetMapping("/{id}/ver_respuestas")
    public List<ComentarioSerializer> verRespuesta(@PathVariable Long id){
        Comentario comentario=comentarioService.findById(id);
        return comentarioService.findAllByComentarioPadre(comentario);
    }
}

