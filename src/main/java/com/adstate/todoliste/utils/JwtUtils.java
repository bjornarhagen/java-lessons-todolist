package com.adstate.todoliste.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {

  private String secret = "genieit";

  /**
   *
   * @param token
   * @return
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }


  /**
   *
   * @param token
   * @return
   */
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }


  /**
   *
   * @param token
   * @param claimsResolver
   * @param <T>
   * @return
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   *
   * @param token
   * @return
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  /**
   *
   * @param token
   * @return
   */
  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
   *
   * @param username
   * @return
   */
  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, username);
  }

  /**
   *
   * @param claims
   * @param subject (User name)
   * @return
   */
  private String createToken(Map<String, Object> claims, String subject) {


    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))   // Set expiration for 10 hours.
            .signWith(SignatureAlgorithm.HS256, secret).compact();
  }

  /**
   *
   * @param token
   * @param userDetails
   * @return
   */
  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

}
