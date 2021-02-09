package com.adstate.todoliste.datatype;

import lombok.*;

@Data
@Setter
@Generated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest {
  private String userName;
  private String password;
}
