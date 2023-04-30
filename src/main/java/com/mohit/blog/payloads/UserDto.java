package com.mohit.blog.payloads;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
  private int id;
  @NotEmpty
  @Size(min=4,message="Username must be min of 4 character")
  private String name;
  @Email
  private String email;
  @NotEmpty
  @Size(min=3,max=10,message="password must be min 3 and maximum 10")
  private String password;//@Pattern use for regex expression
  @NotEmpty
  private String about;
}
