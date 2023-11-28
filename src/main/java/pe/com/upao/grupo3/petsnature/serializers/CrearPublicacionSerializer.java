package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import pe.com.upao.grupo3.petsnature.models.RazaAnimal;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Data
@NoArgsConstructor
public class CrearPublicacionSerializer {
    private Tema tema;
    private TipoMascota tipoMascota;
    private RazaAnimal razaAnimal;
    private String img;
    private String contenido;
    private String categoria;
    private String enlace;

    public CrearPublicacionSerializer(Tema tema, TipoMascota tipoMascota, RazaAnimal razaAnimal, String contenido, String categoria, String enlace,String img) {
        this.tema = tema;
        this.tipoMascota = tipoMascota;
        this.razaAnimal = razaAnimal;
        this.contenido = contenido;
        this.categoria = categoria;
        this.enlace = enlace;
        this.img=img;
    }

}
