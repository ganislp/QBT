package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.modals.ApplicationProperties;
import co.za.quickbuyticketcomponent.modals.AuthorizedUserResponse;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * Created by gVadlamuri on 2/2/2018.
 */

@Component
public class SecurityTokenService {

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    UserProfileService userProfileService;

    public AuthorizedUserResponse generateSecurityToken(UserProfile userProfile) {
        AuthorizedUserResponse authorizedUserResponse = new AuthorizedUserResponse();
        authorizedUserResponse.setEmail(userProfile.getEmail());
        authorizedUserResponse.setAccountType(userProfile.getAccountType().getAccountTypeDesc());
        authorizedUserResponse.setIsActive(userProfile.getIsActive());
        authorizedUserResponse.setFirstname(userProfile.getFirstName());
        return createToken(authorizedUserResponse);
    }


    private AuthorizedUserResponse createToken(AuthorizedUserResponse authorizedUserResponse) {
        Claims claims = Jwts.claims().setSubject(authorizedUserResponse.getEmail());
        claims.put("accountType", authorizedUserResponse.getAccountType());
        claims.put("email", authorizedUserResponse.getEmail());
        //   claims.put("roles", List<String>);
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(applicationProperties.getApiKey());
        // byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("hggfdcgfgfgdfdx");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        String id = UUID.randomUUID().toString().substring(12);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setIssuer("Amortidasation")
                .signWith(signatureAlgorithm, signingKey);

        //long expMillis = nowMillis + applicationProperties.getTokenExpireTime();
        long expMillis = nowMillis + applicationProperties.getExpireTime();
        Date exp = new Date(expMillis);
        claims.setExpiration(new Date(expMillis));
        builder.setExpiration(exp);
        builder.setClaims(claims);
        //Builds the JWT and serializes it to a compact, URL-safe string
        authorizedUserResponse.setAccess_token(builder.compact());
        authorizedUserResponse.setExpireTime(new Date(expMillis));
        return authorizedUserResponse;
    }
}
