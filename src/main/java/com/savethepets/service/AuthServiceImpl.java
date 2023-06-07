package com.savethepets.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.savethepets.config.JwtConfig;
import com.savethepets.dto.TokenInfoDTO;
import com.savethepets.entity.User;
import com.savethepets.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final JwtConfig jwtConfig;
	
	@Override
	public TokenInfoDTO kakaoLogin(String code) {
		
		String accessToken = getAccessToken(code);
		String kakaoAccount = getKakaoAccount(accessToken);
		User user = new User();
		user.setUserId(kakaoAccount);
		userRepository.save(user);
		String token = generateToken(kakaoAccount);
		return new TokenInfoDTO(token, validateToken(token) != null ? true : false);
	}
	
	@Override
    public String generateToken(String userId) {
        
        Date now = new Date();
        Key key = new SecretKeySpec(jwtConfig.getKey().getBytes(), "HmacSHA384");
        
        return Jwts.builder().signWith(key).setSubject(userId).setIssuedAt(now)
        		.setExpiration(new Date(now.getTime()+jwtConfig.getExpiration())).compact();
    }

    @Override
    public String validateToken(String token) {

    	Key key = new SecretKeySpec(jwtConfig.getKey().getBytes(), "HmacSHA384");
    	try {
    		Claims claim = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    		return claim.getSubject();
    	}
    	catch (JwtException e) {
    		return null;
    	}
    }
	
	private String getAccessToken(String code)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "{888ae80006297d9788f3792efe410a57}");
        params.add("redirect_uri", "http://localhost:3000/oauth/kakao");
        params.add("code", code);

        RestTemplate template = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = template.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, request, String.class);
        
        JSONObject data = new JSONObject(response.getBody());
        return data.getString("access_token");
	}
	
	private String getKakaoAccount(String accessToken)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		RestTemplate template = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, request, String.class);

        JSONObject data = new JSONObject(response.getBody());

        return data.getJSONObject("kakao_account").getString("email");
	}
	

}
