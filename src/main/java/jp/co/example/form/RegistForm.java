package jp.co.example.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jp.co.example.controller.MemberCheckGroupA;
import jp.co.example.controller.MemberCheckGroupB;
import lombok.Data;

/**
 * 会員登録・更新入力Form
 * 会員変更時、パスワード未入力の場合
 * 既存値を維持。パスワードチェックしない
 * @author engineer-yone3
 *
 */
@Data
public class RegistForm implements Serializable {

	/* ユーザーID(メールアドレス) */
	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	@Email(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	@Size(max = 64, groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String userMail;

	/* パスワード */
	@NotBlank(groups = MemberCheckGroupA.class)
	@Size(min = 6, groups = MemberCheckGroupA.class)
	private String pwd;

	/* 氏名 */
	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	@Size(max = 10, groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String name;

	/* 氏名(カナ) */
	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	@Size(max = 20, groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String kana;

	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	@Size(max = 20, groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String tel;

	/* 郵便番号 */
	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	@Size(max = 7, groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String zip;

	/* 都道府県 */
	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String todofuken;

	/* 市区群 */
	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String shikugun;

	/* 丁目・番地以降の住所 */
	@NotBlank(groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	@Size(max = 64, groups = { MemberCheckGroupA.class, MemberCheckGroupB.class })
	private String address;

}
