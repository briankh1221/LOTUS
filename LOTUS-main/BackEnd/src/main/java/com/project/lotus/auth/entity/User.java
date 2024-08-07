package com.project.lotus.auth.entity;

import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.common.BaseTimeEntity;
import com.project.lotus.common.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {

    @Schema(description = "이용자 인덱스", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Schema(description = "아이디", example = "testUserId")
    @NotNull
    @Column(unique = true)
    private String id;

    @Schema(description = "비밀번호", example = "testUserPassword")
    @NotNull
    private String password;

    @Schema(description = "이용자 이름", example = "testUserName")
    @NotNull
    private String name;

    @Schema(description = "이용자 전화번호", example = "01012345678")
    @NotNull
    private String phone;

    @Schema(description = "이용자 이메일", example = "testUser@test.com")
    @NotNull
    @Column(unique = true)
    private String email;

    @Schema(description = "이용자 프로필 이미지")
    private String image;

    @Schema(description = "이용자 권한", example = "ROLE_USER")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    // SignupDto.Request -> User(Entity)로 변환 *24.01.24 jihyun
    public static User from(SignupDto.Request signupDto, String encodedPassword, Role role) {
        return User.builder()
                .id(signupDto.getId())
                .password(encodedPassword)
                .name(signupDto.getName())
                .phone(signupDto.getPhone())
                .email(signupDto.getEmail())
                .image(signupDto.getImage())
                .role(role)
                .build();
    }

    public void modifyUser(SignupDto.Request signupDto, String encodedPassword) {

        if (signupDto.getPassword() != null) {
            this.password = encodedPassword;
        }
        if (signupDto.getName() != null) {
            this.name = signupDto.getName();
        }
        if (signupDto.getPhone() != null) {
            this.phone = signupDto.getPhone();
        }
        if (signupDto.getImage() != null) {
            this.image = signupDto.getImage();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(new String[]{String.valueOf(role)})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
