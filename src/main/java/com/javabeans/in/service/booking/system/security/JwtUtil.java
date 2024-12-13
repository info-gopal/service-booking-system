package com.javabeans.in.service.booking.system.security;

import java.security.Key;
import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	public static final String SECRET = "931b3b0a33d11f3ed7568022bdc9105c637dcd1def892ec611f7a793db3a483c95c450d32e8becee425ef9f3f86f8555e856a0acd9c4b861e6e2d91bc6310057";

	private String createToken(Map<String, Object> claims, String userName) {

		return Jwts.builder().setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(SignatureAlgorithm.HS256, getSignkey()).compact();
	}

	private Key getSignkey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);

	}

	public String generateToken(String username) {

		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);

	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSignkey()).parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Date extractExpriration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);

	}

	private Boolean isTokenExpired(String token) {
		return extractExpriration(token).before(new Date());
	}

	public Boolean validationToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
