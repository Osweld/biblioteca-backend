package sv.edu.ues.bibliotecabackend.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.auth.models.AuthUser;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;
import sv.edu.ues.bibliotecabackend.models.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    private final UsuarioRepository userRepository;

    public UserDetailsServiceImpl(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username).orElseThrow(() -> new  UsernameNotFoundException("The username:"+username+" does not exist"));
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getPersona().getRol().getRol()));
        //if(authorities.isEmpty()) throw new UsernameNotFoundException("the user has no assigned roles");

        return new AuthUser(user.getId(),user.getUsername(),user.getPassword(),true,true,true,true,authorities);
    }
}
