package gjxt.portal.apigateway.sercurity.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import gjxt.portal.apigateway.sercurity.domain.auth.ResponseUserToken;
import gjxt.portal.apigateway.sercurity.domain.auth.Role;
import gjxt.portal.apigateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.apigateway.sercurity.redis.RedisUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.noggit.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: goose
 * createAt: 2019/4/1
 */
@Component
public class JwtUtils {


	//	private Map<String, String> tokenMap = new ConcurrentHashMap<>(32);
	private Map<String, String> refreshTokenMap = new ConcurrentHashMap<>(32);

	@Resource
	private JwtConfig jwtCfg;

	@Autowired
	private RedisUtil redisUtil;

	private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;


	public UserDetail getUserFromToken(String token) {
		UserDetail userDetail;
		try {
			final Claims claims = getClaimsFromToken(token);
			Long userId = getUserIdFromToken(token);
			String phone=claims.get(JwtConfig.CLAIM_USER_PHONE).toString();
			Set<Role> roles = JSON.parseObject(JSONObject.toJSONString(claims.get(JwtConfig.CLAIM_KEY_ROLE)),
					new TypeReference<Set<Role>>() {}); // 用户角色转换，防止报LinkedHashMap错误使用json转换

			userDetail = new UserDetail(userId,phone,roles);

		} catch (Exception e) {
			userDetail = null;
		}
		return userDetail;
	}

	public Long getUserIdFromToken(String token) {
		Long userId;
		try {
			final Claims claims = getClaimsFromToken(token);
			userId = Long.parseLong(String.valueOf(claims.get(JwtConfig.CLAIM_KEY_USER_ID)));
		} catch (Exception e) {
			userId = (long) 0;
		}
		return userId;
	}

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			created = claims.getIssuedAt();
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	public ResponseUserToken generateTokens(UserDetail userDetail) {
		String accessToken = generateAccessToken(userDetail);
		String refreshToken = generateRefreshToken(userDetail);
//		return new ResponseUserToken(accessToken, refreshToken, new Date(),
//				userDetail.getExpiration(), userDetail);



		return new ResponseUserToken(accessToken, refreshToken, new Date(), 
				jwtCfg.getAccessTokenExpiration(), userDetail);		
	}


	//	public ResponseClientUserToken generateTokens(ClientUserDetail userDetail) {
	//		String accessToken = generateAccessToken(userDetail);
	//		String refreshToken = generateRefreshToken(userDetail);
	//		return new ResponseClientUserToken(accessToken, refreshToken, new Date(), 
	//				jwtCfg.getAccessTokenExpiration(), userDetail);		
	//	}
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (ExpiredJwtException e) {
			expiration = e.getClaims().getExpiration();        	
		} catch (Exception e) {

			expiration = null;
		}
		return expiration;
	}

	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = getCreatedDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
				&& (!isTokenExpired(token));
	}


	public Boolean validateToken(String token, UserDetails userDetails) {
		UserDetail userDetail = (UserDetail) userDetails;
		final long userId = getUserIdFromToken(token);
		final String username = getUsernameFromToken(token);
		//        final Date created = getCreatedDateFromToken(token);
		return (userId == userDetail.getId()
				&& username.equals(userDetail.getUsername())
				&& !isTokenExpired(token)
				//                && !isCreatedBeforeLastPasswordReset(created, userDetail.getLastPasswordResetDate())
				);
	}


	private String generateAccessToken(UserDetail userDetail) {
		Map<String, Object> claims = generateClaims(userDetail);
		if(authoritiesToArray(userDetail.getAuthorities()).size()>0){
			claims.put(JwtConfig.CLAIM_KEY_AUTHORITIES, authoritiesToArray(userDetail.getAuthorities()).get(0));
		}
		claims.put(JwtConfig.CLAIM_KEY_USER_ID, userDetail.getUser().getId());
		claims.put(JwtConfig.CLAIM_USER_PHONE, userDetail.getUser().getPhone());
		return generateAccessToken(userDetail.getUsername(), claims);
	}


	private String generateRefreshToken(UserDetail userDetail) {
		Map<String, Object> claims = generateClaims(userDetail);
		// 只授于更新 token 的权限
		String roles[] = new String[]{JwtConfig.ROLE_REFRESH_TOKEN};
		claims.put(JwtConfig.CLAIM_KEY_AUTHORITIES, JSONUtil.toJSON(roles));
		return generateRefreshToken(userDetail.getUsername(), claims);
	}

	public void putToken(String userName, String token, String refreshToken) {
		//		tokenMap.put(userName, token);
		redisUtil.set(userName, token);
		refreshTokenMap.put(userName, refreshToken);
	}

	public void deleteToken(String userName) {
//		tokenMap.remove(userName);
		redisUtil.delete(userName);
		refreshTokenMap.remove(userName);
	}

	public boolean containToken(String userName, String token) {
		//		if (userName != null && tokenMap.containsKey(userName) && tokenMap.get(userName).equals(token)) {
		if (userName != null && redisUtil.getValueByKey(userName)!=null && redisUtil.getValueByKey(userName).equals(token)) {
			return true;
		}
		return false;
	}

	public boolean containRefreshToken(String userName, String token) {
		if (userName != null && refreshTokenMap.containsKey(userName) && refreshTokenMap.get(userName).equals(token)) {
			return true;
		}
		return false;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(jwtCfg.getSecret())
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateExpirationDate(long expiration) {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	private Map<String, Object> generateClaims(UserDetail userDetail) {
		Map<String, Object> claims = new HashMap<>(16);
		claims.put(JwtConfig.CLAIM_KEY_USER_ID, userDetail.getUser().getId());
		claims.put(JwtConfig.CLAIM_KEY_ROLE, userDetail.getUser().getRoles());
		return claims;
	}


	private List<String> authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
		List<String> list = new ArrayList<>();
		for (GrantedAuthority ga : authorities) {
			list.add(ga.getAuthority());
		}
		return list;
	}

	public String validateBeforeRefresh(String accessToken, String refreshToken)  {
		Claims refreshClaims, accessClaims;
		String username = null;
		try {
			refreshClaims = Jwts.parser()
					.setSigningKey(jwtCfg.getSecret())
					.parseClaimsJws(refreshToken)
					.getBody();
		} catch (ExpiredJwtException e) {
			return null;
		} catch(Exception e) {
			return null;
		}

		String scope = refreshClaims.get(JwtConfig.CLAIM_KEY_AUTHORITIES, String.class);
		if(StringUtils.isBlank(scope) || !scope.contains(JwtConfig.ROLE_REFRESH_TOKEN)) {
			return null;
		}

		try {
			accessClaims = Jwts.parser()
					.setSigningKey(jwtCfg.getSecret())
					.parseClaimsJws(accessToken)
					.getBody();
		} catch (ExpiredJwtException e) {
			accessClaims = e.getClaims();
		} catch(Exception e) {
			return null;
		}

		if(refreshClaims.getSubject().equals(accessClaims.getSubject()) &&
				refreshClaims.getIssuer().equals(accessClaims.getIssuer())) {
			username = refreshClaims.getSubject();
			if(!containToken(username, accessToken) || !containRefreshToken(username, refreshToken)) {
				username = null;
			}
		}

		return username;        
	}

	private String generateRefreshToken(String subject, Map<String, Object> claims) {
		return generateToken(subject, claims, jwtCfg.getRefreshTokenExpiration());
	}

	private String generateAccessToken(String subject, Map<String, Object> claims) {
		return generateToken(subject, claims, jwtCfg.getAccessTokenExpiration());
	}

	private String generateToken(String subject, Map<String, Object> claims, long expiration) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setId(UUID.randomUUID().toString())
				.setIssuer(JwtConfig.ISSUER)
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate(expiration))
				.compressWith(CompressionCodecs.DEFLATE)
				.signWith(SIGNATURE_ALGORITHM, jwtCfg.getSecret())
				.compact();
	}


}
