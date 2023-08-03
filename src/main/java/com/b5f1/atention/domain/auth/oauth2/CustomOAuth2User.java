package com.b5f1.atention.domain.auth.oauth2;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;
import java.util.UUID;

@Getter
public class CustomOAuth2User extends DefaultOidcUser {
    private UUID id;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            OidcIdToken idToken,
                            OidcUserInfo userInfo,
                            String nameAttributeKey,
                            UUID id) {
        super(authorities, idToken, userInfo, nameAttributeKey);
        this.id = id;
    }
}
