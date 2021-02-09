package com.adstate.todoliste.controllers;


import com.adstate.todoliste.datatype.AuthenticateRequest;
import com.adstate.todoliste.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class GenerateJwtToken {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AuthenticationManager authenticationManager;

  protected final Logger log = LoggerFactory.getLogger(getClass());

  /**
   *
   * This api should be accessible to any one.
   * @param authenticateRequest
   * @return  Encrupted jwt token.
   * @throws Exception
   */

  @PostMapping("/authenticate")
  public String generateToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
    log.info("User: " + authenticateRequest.getUserName());
    log.info("Pwd: " + authenticateRequest.getPassword());

    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authenticateRequest.getUserName(), authenticateRequest.getPassword())
      );
    } catch (Exception e) {
      throw new Exception((" Error -- Invalid user and/or password ..."));
    }
    return jwtUtils.generateToken(authenticateRequest.getUserName());
  }
}
