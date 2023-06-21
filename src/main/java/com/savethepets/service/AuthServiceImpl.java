package com.savethepets.service;

import java.security.Key;

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

/**
 * Description<br>
 *  - AwsServiceImpl Class : AwsService를 구현한 구현체 클래스<br>
 * <br>
 * Field<br>
 * 	- userRepository : User Table 접근 Repository<br>
 *  - jwtConfig : JWT 관련 Configuration<br>
 * <br>
 * Method <br>
 *  - kakaoLogin : 인가코드로 JWT를 발행하는 메소드 <br>
 * 	- generateToken : 사용자Id로 JWT를 발행하는 메소드 <br>
 *  - validateToken : JWT를 검증하는 메소드 <br>
 *  - getAccessToken : 인가코드로 Access Token을 발행하는 메소드<br>
 *  - getKakaoAccount : Access Token으로 카카오계정을 가져오는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final JwtConfig jwtConfig;
	
	/**
	 * Description<br>
	 *  - kakaoLogin : 인가코드로 JWT를 발행하는 메소드<br>
	 * @param code 카카오서버에서 전달받은 인가코드
	 * @return TokenInfoDTO ({@link com.savethepets.dto.TokenInfoDTO})
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public TokenInfoDTO kakaoLogin(String code) {
		
		String accessToken = getAccessToken(code);
		String kakaoAccount = getKakaoAccount(accessToken);
		if(userRepository.findOne(kakaoAccount) == null)
		{
			User user = new User();
			user.setUserId(kakaoAccount);
			userRepository.save(user);
		}
		String token = generateToken(kakaoAccount);
		return new TokenInfoDTO(token, validateToken(token) != null ? true : false);
	}
	/**
	 * Description<br>
	 *  - generateToken : 사용자Id로 JWT를 발행하는 메소드 <br>
	 * @param userId 사용자 카카오계정
	 * @return JWT
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
    public String generateToken(String userId) {
        Key key = new SecretKeySpec(jwtConfig.getKey().getBytes(), "HmacSHA384");
        return Jwts.builder().signWith(key).setSubject(userId).compact();
    }
	/**
	 * Description<br>
	 *  - validateToken : JWT를 검증하는 메소드 <br>
	 * @param token JWT
	 * @return 사용자 Id
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
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
	/**
	 * Description<br>
	 *  - getAccessToken : 인가코드로 Access Token을 발행하는 메소드<br>
	 * @param token JWT
	 * @return Access Token
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	private String getAccessToken(String code)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "888ae80006297d9788f3792efe410a57");
        params.add("redirect_uri", "http://localhost:3000/oauth/kakao");
        params.add("code", code);

        RestTemplate template = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = template.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, request, String.class);
        
        JSONObject data = new JSONObject(response.getBody());
        return data.getString("access_token");
	}
	/**
	 * Description<br>
	 *  - getKakaoAccount : Access Token으로 카카오계정을 가져오는 메소드 <br>
	 * @param accessToken Access Token
	 * @return 사용자 카카오계정
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
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
