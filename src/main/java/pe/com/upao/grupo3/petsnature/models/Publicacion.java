package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE")
    private Date fecha;
    @Column(columnDefinition = "TIME")
    private Time hora;
    @Column(nullable = false)
    private String categoria;
    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
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
    @Column
    private int reacciones=0;

    public Publicacion(Usuario usuario, String categoria,Tema tema, TipoMascota tipoMascota, String img, String contenido, String enlace) {
        fecha=new Date();
        hora=new Time(System.currentTimeMillis());
        this.usuario = usuario;
        this.categoria=categoria;
        this.tema = tema;
        this.tipoMascota=tipoMascota;
        this.img=img;
        this.contenido=contenido;
        this.enlace=enlace;
    }



    public Publicacion() {
    }

}
