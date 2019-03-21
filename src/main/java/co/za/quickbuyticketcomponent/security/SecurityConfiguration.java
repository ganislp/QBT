package co.za.quickbuyticketcomponent.security;

import co.za.quickbuyticketcomponent.security.AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
    private OriginAuthenticationProvider authProvider;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authProvider);
    }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http
          .authorizeRequests()
          .anyRequest().permitAll()
          .and().csrf().disable()
          .httpBasic().disable();

 }
    
    @Override
    public void configure(WebSecurity web) throws Exception
    {
    	web.ignoring()
    	.antMatchers(HttpMethod.OPTIONS);
    }
}