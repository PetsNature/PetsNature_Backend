package pe.com.upao.grupo3.petsnature.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.com.upao.grupo3.petsnature.exceptions.CategoriaNoExistenteException;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.repositories.PublicacionRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PublicacionServicioTest {
    @Mock
    private PublicacionRepository publicacionRepository;

    @Mock
    private Publicacion publicacion;

    @Mock
    private List<Publicacion> publicaciones;

    @InjectMocks
    private PublicacionServicio publicacionServicio;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    //Escenario Exitoso de crear Publicacion
    @Test
    public void testCrearPublicacion(){
        when(publicacionRepository.save(publicacion)).thenReturn(publicacion);

        Publicacion result = publicacionServicio.crearPublicacion(publicacion);

        assertEquals(publicacion,result);
    }

    //Escenario Exitoso de filtrar por categoria
    @Test
    public void testFiltrarPorCategoria(){
        String categoria="Informacion";

        when(publicacionRepository.findAllByCategoria(categoria)).thenReturn(publicaciones);

        List<Publicacion> result = publicacionServicio.filtrarporCategoria(categoria);

        assertEquals(publicaciones,result);
    }

    /*//Escenario Alternativo de filtrar por categoria: La categoria no existe
    @Test
    public void testFiltrarPorCategoriaA(){

        String categoria="Servicio";

        when(publicacionRepository.findAllByCategoria(categoria)).thenReturn(publicaciones);
        when(publicaciones.isEmpty()).thenReturn(true);

        assertThrows(CategoriaNoExistenteException.class, ()->{
            publicacionServicio.filtrarporCategoria(categoria);
        });
    }*/
}
