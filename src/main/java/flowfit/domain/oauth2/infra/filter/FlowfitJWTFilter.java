package flowfit.domain.oauth2.infra.filter;



import flowfit.domain.oauth2.infra.exception.DuplicateLoginException;
import flowfit.domain.oauth2.infra.exception.InvalidAccessTokenException;
import flowfit.domain.user.domain.entity.Role;
import flowfit.global.jwt.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class FlowfitJWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final List<String> excludedPaths;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if(requestURI.contains("/api/oauth2/login") || requestURI.contains("/api/oauth2/callback")) {
            String accessToken = jwtUtil.getAccessTokenFromHeaders(request);
            if(jwtUtil.jwtVerify(accessToken, "access")) {
                throw new DuplicateLoginException();
            }
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtUtil.getAccessTokenFromHeaders(request);
        log.info(accessToken);

        if(accessToken == null || accessToken.equals("undefined") || accessToken.equals("null")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(!jwtUtil.jwtVerify(accessToken, "access")) {
            throw new InvalidAccessTokenException();
        }

        String userId = jwtUtil.getId(accessToken);
        log.info(userId);
        Role role = jwtUtil.getRole(accessToken);


        GrantedAuthority authority = new SimpleGrantedAuthority(role.getKey());
        log.info(role.getKey());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, Collections.singleton(authority));
        log.info(role.name());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("인증 설정 완료: {}", SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return excludedPaths.stream()
                .anyMatch(pattern ->
                        new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}
