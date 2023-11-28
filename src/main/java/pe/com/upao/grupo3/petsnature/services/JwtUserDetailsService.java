package pe.com.upao.grupo3.petsnature.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.repositories.UsuarioRepository;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
    private final UsuarioRepository userRepository;

    public JwtUserDetailsService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        logger.debug("---loadUserByUsername called.---");
        Optional<Usuario> user = userRepository.findByCorreo(correo);
        if(user.isPresent()) {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>(1);
            authorities.add(new SimpleGrantedAuthority("ADMIN"));

            return new User(user.get().getUsername(), user.get().getPassword(),true,true,true,true, authorities);
        } else {
            throw new UsernameNotFoundException("User "+correo+" not found.");
        }
    }


}
