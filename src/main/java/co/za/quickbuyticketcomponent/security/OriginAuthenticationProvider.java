package co.za.quickbuyticketcomponent.security;

import java.util.ArrayList;

import co.za.quickbuyticketcomponent.utils.Messages;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OriginAuthenticationProvider  implements AuthenticationProvider {
 
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
  
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

       //TODO LDAP call
        if (name.equals("raju") && password.equals("raju"))
        	return new UsernamePasswordAuthenticationToken(name, password, new ArrayList());
        else
        	throw new BadCredentialsException(Messages.BAD_CREDENTIALS);
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}

 