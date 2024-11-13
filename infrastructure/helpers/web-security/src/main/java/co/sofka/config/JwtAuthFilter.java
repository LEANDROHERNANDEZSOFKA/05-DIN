package co.sofka.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Verifica que el encabezado contenga el token en formato "Bearer ..."
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae el token y el usuario
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // Si el usuario está presente en el token y el contexto de seguridad está vacío
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Extrae los roles o authorities del token
            var authoritiesClaims = jwtService.extractAllClaims(jwt).get("roles");
            var authorities =
                    authoritiesClaims != null ?
                            AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaims.toString()) :
                            AuthorityUtils.NO_AUTHORITIES;

            // Construye un UserDetails usando el email y los roles obtenidos
            UserDetails userDetails = User.withUsername(userEmail)
                    .password("") // No es necesario el password aquí
                    .authorities(authorities)
                    .build();

            // Verifica solo la coincidencia del usuario, omitiendo la expiración del token
            if (userEmail.equals(userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establece la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
