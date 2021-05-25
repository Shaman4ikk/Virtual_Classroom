package security;

import entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reprository.UserRepository;

@Service
@Transactional(readOnly = true)
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    UserDTO userDTO = new UserDTO();

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        userDTO = UserRepository.login(login);
        return (UserDetails) userDTO;
    }
}
