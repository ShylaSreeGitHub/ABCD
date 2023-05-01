package in.projects.loginandregistration.Service;

import in.projects.loginandregistration.Model.User;
import in.projects.loginandregistration.web.Dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);
}
