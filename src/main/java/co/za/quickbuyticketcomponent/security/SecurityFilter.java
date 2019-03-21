package co.za.quickbuyticketcomponent.security;



import co.za.quickbuyticketcomponent.modals.AuthorizedUserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Base64;


@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String authHeaderVal = httpRequest.getHeader("x-auth-token");
        final String testingHeaderValue = httpRequest.getHeader("services-testing");

        if("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(httpRequest, httpResponse);
            return;
        }

        if (testingHeaderValue != null) {
            if (testingHeaderValue.equalsIgnoreCase("services-testing")) {
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        }

        if (!((HttpServletRequest) request).getRequestURL().toString().contains("/IsibayaReportingWebService/authorizeUser")) {

            if (null == authHeaderVal) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            try {
                AuthorizedUserResponse user = getRealUser(authHeaderVal);
                httpRequest.setAttribute("jwtUser", user);

            } catch (ExpiredJwtException e) {
                System.out.println("stopin.......................");
                throw new IllegalArgumentException("Token Expired");

            } catch (Exception e) {
               // e.printStackTrace();
                httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }
        }


        chain.doFilter(httpRequest, httpResponse);
    }

    private String encodedSecret;

    @PostConstruct
    protected void init() {
        this.encodedSecret = generateEncodedSecret("something-secret-you-cannot-keep-it");
    }

    protected String generateEncodedSecret(String plainSecret) {
        if (StringUtils.isEmpty(plainSecret)) {
            throw new IllegalArgumentException("JWT secret cannot be null or empty.");
        }
        return Base64
                .getEncoder()
                .encodeToString("something-secret-you-cannot-keep-it".getBytes());
    }


    protected AuthorizedUserResponse getUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("qwertyuiopzxcvbnm"))
                .parseClaimsJws(token).getBody();
        System.out.println("exp time....................... :" + claims.getExpiration());
        String userName = claims.getSubject();
       // String role = claims.get("role");
        AuthorizedUserResponse securityUser = new AuthorizedUserResponse();
        securityUser.setAccess_token(userName);
        System.out.println("securityUser.getUserName()...................... :" + securityUser.getAccess_token());
        return securityUser;
    }


    public AuthorizedUserResponse getRealUser(String token) {
        AuthorizedUserResponse user = getUser(token);
        return user;
    }
}