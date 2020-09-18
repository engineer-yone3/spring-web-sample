package jp.co.example.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * ログイン用Form
 * @author engineer-yone3
 *
 */
@Data
public class LoginForm implements Serializable {

	/* ユーザーID(メールアドレス) */
	@NotBlank
	@Email
	@Size(max = 64)
	private String userMail;

	/* パスワード */
	@NotBlank
	@Size(min = 6)
	private String pwd;

}
