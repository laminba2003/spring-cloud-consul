package com.spring.training.config;

import com.spring.training.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.spring.training.config.Claims.*;

public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        User user = new User(jwt.getClaim(GIVEN_NAME),
                jwt.getClaim(FAMILY_NAME),
                jwt.getClaim(EMAIL));
        List<String> roles = Optional.ofNullable(jwt.getClaimAsStringList(ROLES)).orElse(new ArrayList<>());
        List<GrantedAuthority> authorities = roles.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

}