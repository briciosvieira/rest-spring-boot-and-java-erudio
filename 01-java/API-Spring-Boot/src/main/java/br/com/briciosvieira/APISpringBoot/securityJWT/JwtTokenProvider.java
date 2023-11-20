package br.com.briciosvieira.APISpringBoot.securityJWT;

import br.com.briciosvieira.APISpringBoot.vo.v1.security.TokenVO;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey ="secret";

    @Value("${security.jwt.token.secret-key:expire-lenght:360000}")
    private long validityInMilliseconds =360;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm= null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAccessToken(String username, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime()+ validityInMilliseconds);
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getAccessSecondToken(username, roles, now);

        return new TokenVO(username, true,now,validity,accessToken,refreshToken);
    }

    private String getAccessSecondToken(String username, List<String> roles, Date now) {
        return null;
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        return null;
    }
}
