package com.book.springboot.config.auth;


import com.book.springboot.config.auth.dto.OAuthAttributes;
import com.book.springboot.config.auth.dto.SessionUser;
import com.book.springboot.domain.user.User;
import com.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.
                getClientRegistration().getRegistrationId(); /*registrationId : 현재 진행중인 서비스를 구분하는 코드. 이후 네이버 연동 시
        구글 로그인인지 네이버 로그인인지 구분하기 위해 사용*/
        String userNameAttributeName = userRequest.
                getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName(); /*userNameAttributesName: OAuth2로그인 진행 시 키가 되는 필드값.
                primary키와 같은 의미. 이후 네이버와 구글 동시 로그인을 지원할 때 사용*/

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName,
                        oAuth2User.getAttributes());

        //OAuthAttributes: OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스. 네이버 등 다른 소셜 로그인도 이 클래스 사용.

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
//SessionUser: 세션에 사용자 정보를 저장하기 위한 Dto클래스.

        return new DefaultOAuth2User(Collections.singleton(new
                SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user=userRepository.findByEmail(attributes.getEmail())
                .map(entity->entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
