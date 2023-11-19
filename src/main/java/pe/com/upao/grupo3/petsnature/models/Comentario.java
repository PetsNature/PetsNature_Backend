package pe.com.upao.grupo3.petsnature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private Date fecha;

    @Column(nullable = false)
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "comentario_padre_id")
    private Comentario comentarioPadre;

    @OneToMany(mappedBy = "comentarioPadre")
    private List<Comentario> respuestas;

    public Comentario(String contenido, Usuario usuario, Publicacion publicacion) {
        this.fecha= new Date();
        this.contenido = contenido;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public Comentario(String contenido, Usuario usuario, Comentario comentarioPadre) {
        this.fecha= new Date();
        this.contenido = contenido;
        this.usuario = usuario;
        this.comentarioPadre = comentarioPadre;
    }
}
