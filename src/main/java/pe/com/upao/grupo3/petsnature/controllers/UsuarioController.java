package pe.com.upao.grupo3.petsnature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.com.upao.grupo3.petsnature.exceptions.UsuarioNoExisteException;
import pe.com.upao.grupo3.petsnature.serializers.InicioSesionSerializer;
import pe.com.upao.grupo3.petsnature.serializers.UsuarioIniciadoSerializer;
import pe.com.upao.grupo3.petsnature.serializers.UsuarioPerfilSerializer;
import pe.com.upao.grupo3.petsnature.serializers.UsuarioSerializer;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.services.UsuarioService;
import pe.com.upao.grupo3.petsnature.util.EncryptionUtil;
import pe.com.upao.grupo3.petsnature.util.JwtTokenUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "https://petsnature-frontend.netlify.app")
public class UsuarioController {
    private Usuario usuarioAuten;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    private UsuarioService usuarioService;
    @Value("${perfil.foto.directorio}")
    private String directorioFotos;

    @PostMapping("/login2")
    public ResponseEntity<UsuarioIniciadoSerializer> login2(@RequestBody InicioSesionSerializer inicioSesionSerializer) throws Exception{
        Usuario user = usuarioService.iniciarSesion(inicioSesionSerializer.getCorreo(), inicioSesionSerializer.getContrasena());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(inicioSesionSerializer.getCorreo());

        final String token = jwtTokenUtil.generateToken(userDetails);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(new UsuarioIniciadoSerializer(user.getId(),user.getCorreo(), user.getNombre(),user.getImgPerfil(), EncryptionUtil.encrypt(token)));
        }
    }

    @PostMapping("/login")
    public UsuarioIniciadoSerializer login(@RequestBody InicioSesionSerializer inicioSesionSerializer){
        usuarioAuten= usuarioService.iniciarSesion(inicioSesionSerializer.getCorreo(),inicioSesionSerializer.getContrasena());
        return new UsuarioIniciadoSerializer(usuarioAuten.getId(), usuarioAuten.getCorreo(), usuarioAuten.getNombre(), usuarioAuten.getImgPerfil(),"aca no hay token :(");
    }

    @GetMapping("/home/logout")
    public Usuario logout(){
        Usuario authenUser=usuarioAuten;
        usuarioAuten=null;
        return authenUser;
    }


    @GetMapping("/{id}/perfil")
    public UsuarioPerfilSerializer verPerfil(@PathVariable Long id){
        return usuarioService.verPerfil(id);
    }

    @PostMapping("/registro")
    public Usuario registro(@RequestBody UsuarioSerializer usuarioSerializer){
        Usuario usuario= new Usuario(usuarioSerializer.getCorreo(),usuarioSerializer.getNombre(),usuarioSerializer.getContrasena());
        usuarioService.registrarUsuario(usuario);
        return usuario;
    }

    @PostMapping("/{id}/subir-foto")
    public String subirFoto(@PathVariable Long id, @RequestParam("imgPerfil") MultipartFile foto) throws IOException {
        Usuario usuario = usuarioService.encontrarUsuario(id);
        if (usuario != null && !foto.isEmpty()) {
            String rutaFoto = directorioFotos + File.separator + foto.getOriginalFilename();
            foto.transferTo(new File(rutaFoto));
            usuarioService.cambiarImgPerfil(id,rutaFoto);
        }
        return "redirect:/perfil/" + id;
    }

    @GetMapping("/usuarios")
    public List<InicioSesionSerializer> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
