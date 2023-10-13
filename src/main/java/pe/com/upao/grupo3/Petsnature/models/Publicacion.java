package pe.com.upao.grupo3.Petsnature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE")
    private Date fecha;
    @Column(columnDefinition = "TIME")
    private Time hora;
    @ManyToOne
    @JoinColumn(name = "correo_u",nullable = false)
    private Usuario usuario;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String tema;
    @Column(nullable = false)
    private String mascota;
    private String raza;
    private String titulo;
    private String descripcion;
    @Column(nullable = false)
    private String info;
    private String img;

    public Publicacion(Usuario usuario, String categoria, String tema, String mascota, String raza, String titulo, String descripcion, String info, String img) {
        fecha=new Date();
        hora=new Time(System.currentTimeMillis());
        this.usuario = usuario;
        this.categoria = categoria;
        this.tema = tema;
        this.mascota = mascota;
        this.raza = raza;
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.info=info;
        this.img=img;
    }

    public Publicacion() {
    }

    /*private Time obtenerHora() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceBuilder.create().build());
        return jdbcTemplate.queryForObject("SELECT current_time", Time.class);
    }

    private Date obtenerFecha() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceBuilder.create().build());
        return jdbcTemplate.queryForObject("SELECT current_timestamp", Date.class);
    }*/
}
