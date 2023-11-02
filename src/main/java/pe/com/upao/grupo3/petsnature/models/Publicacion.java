package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
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
    @Column(nullable = false)
    private String tema;
    @Column(nullable = false)
    private String mascota;
    private String raza;
    private String img;

    public Publicacion(Usuario usuario, String tema, String mascota, String raza, String img) {
        fecha=new Date();
        hora=new Time(System.currentTimeMillis());
        this.usuario = usuario;
        this.tema = tema;
        this.mascota = mascota;
        this.raza = raza;
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
