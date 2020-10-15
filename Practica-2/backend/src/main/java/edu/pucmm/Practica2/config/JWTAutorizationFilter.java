package edu.pucmm.Practica2.config;

import edu.pucmm.Practica2.entities.User;
import edu.pucmm.Practica2.repositories.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAutorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    private UserRepository userRepository;

    @Value("${token_jwt}")
    String SECRET = "llavesecretaaquiasdasdadadsasdasasdasdasdas1231231asdasdsaasdasdasdasd";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("entrando al filtro de autorización local....");
        System.out.println("Servlet Path: "+request.getServletPath());
        System.out.println("Query String: "+request.getQueryString());
        if(request.getServletPath().startsWith("/api/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println(request.getHeader("Authorization"));
        if(request.getServletPath().startsWith("/api/")){
            Claims claims = validateToken(request);
            if( claims != null){
                String username = claims.getSubject();
                User user = userRepository.findByEmail(username);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwtToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    /**
     *
     * @param request
     * @return
     */
    private Claims validateToken(HttpServletRequest request) {
        System.out.println("El SECRET es: " + SECRET);
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        try {
             return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody();
        } catch (MalformedJwtException e) {
        } catch (ExpiredJwtException e) {
        } catch (UnsupportedJwtException e) {
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    /**
     *
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {

        List authorities = (List) claims.get("roles");
        List<SimpleGrantedAuthority> listaAuto = new ArrayList<>();
        listaAuto.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,listaAuto);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    /**
     *
     * @param request
     * @param res
     * @return
     */
    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        System.out.println(authenticationHeader);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }

}
