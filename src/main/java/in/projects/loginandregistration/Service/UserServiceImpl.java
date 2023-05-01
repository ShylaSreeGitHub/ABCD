package in.projects.loginandregistration.Service;

import in.projects.loginandregistration.Model.Role;
import in.projects.loginandregistration.Model.User;
import in.projects.loginandregistration.Repository.UserRepository;
import in.projects.loginandregistration.web.Dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

//    @Autowired
//    private UserRepository userRepository;
//    or

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getFirstName()
        , userRegistrationDto.getLastName(), userRegistrationDto.getEmail()
        , passwordEncoder.encode(userRegistrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
         return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByemail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
//        return (UserDetails) user;
        return new org.springframework.security.core.userdetails.User(user.getFirstName(),
                user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
