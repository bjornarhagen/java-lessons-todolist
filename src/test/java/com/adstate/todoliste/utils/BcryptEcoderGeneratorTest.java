package com.adstate.todoliste.utils;


import com.adstate.todoliste.TodolisteApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BcryptEcoderGeneratorTest extends TodolisteApplicationTests {

 private BCryptPasswordEncoder bcryptEcoderGenerator;

  @Test
  public void encodeString() {

    String password = "celina1509";
    bcryptEcoderGenerator = new BCryptPasswordEncoder();
    String encodedPassword = bcryptEcoderGenerator.encode(password);
    System.out.println("\n *******************************");
    System.out.println(encodedPassword);
    System.out.println("\n *******************************");
    Assert.assertNotNull(encodedPassword);
  }
}
