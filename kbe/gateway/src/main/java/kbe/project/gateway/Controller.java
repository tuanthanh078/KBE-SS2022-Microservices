package kbe.project.gateway;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class Controller {

    @GetMapping("/")
    public String index(Principal principal) {
        String principal_str = principal.toString();
        int start = principal_str.indexOf(", preferred_username=") + 21;
        int end = principal_str.indexOf(",", start);
        String username = principal_str.substring(start, end);
        return username;
    }

}
