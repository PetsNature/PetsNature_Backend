package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "categoria")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE")
    private Date fecha;
    @Column(columnDefinition = "TIME")
    private Time hora;
    @Column(insertable = false, updatable = false)
    private String categoria;
    @ManyToOne
    @JoinColumn(name = "correo_u",nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "tema", nullable = false)
    private Tema tema;
    @ManyToOne
    @JoinColumn(name = "tipo_mascota", nullable = false)
    private TipoMascota tipoMascota;
    private String img;
    @Column(nullable = false)
    private String contenido;
    @Column
    private String enlace;
//constructor//
    public Publicacion(Usuario usuario, Tema tema, TipoMascota tipoMascota, String img, String contenido, String enlace) {
        fecha=new Date();
        hora=new Time(System.currentTimeMillis());
        this.usuario = usuario;
        this.tema = tema;
        this.tipoMascota=tipoMascota;
        this.img=img;
        this.contenido=contenido;
        this.enlace=enlace;
    }

    public Publicacion() {
    }

}
