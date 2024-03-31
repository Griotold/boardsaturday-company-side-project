package com.bizplus.boardsaturday.application.request.member;

import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberRequest {
    @Email
    @NotBlank(message = "이메일 입력은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수 입력값입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "비밀번화 확인 입력은 필수 입력값입니다.")
    @Size(min = 6, message = "비밀번호 확인 입력은 최소 6자 이상이어야 합니다.")
    private String passwordConfirm;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    public CreateMemberRequest(String email,
                               String password,
                               String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member toEntity(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(this.password);
        return new Member(this.name,
                this.email,
                encodedPassword,
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING);
    }

    // 비밀번호와 비밀번호확인 다르면 true
    public boolean isPasswordMismatch() {
        return !password.equals(passwordConfirm);
    }
}
