package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComentarioCreadoSerializer {
    private String contenido;
    private Long id;
    private Long idAutor;
    private Long idPublicacion;
    private Long idComentario;
}
