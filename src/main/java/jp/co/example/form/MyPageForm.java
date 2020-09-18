package jp.co.example.form;

import lombok.Data;

/**
 * マイページ表示Form
 * @author engineer-yone3
 *
 */
@Data
public class MyPageForm {

	/* ユーザーID(DB用採番) */
	private String userId;

	/* Eメールアドレス(フロント用ユーザーID) */
	private String userEmail;

	/* データ登録日 */
	private String regDate;

	/* データ更新日 */
	private String updDate;

	/* データ論理削除フラグ */
	private String isDelete;

	/* データ削除日 */
	private String delDate;

	/* 氏名 */
	private String userName;

	/* 氏名カナ */
	private String userNameKana;

	/* 住所(郵便番号) */
	private String zip;

	/* 住所(都道府県) */
	private String todofuken;

	/* 住所(市区郡) */
	private String shikugun;

	/* 住所(以降の住所) */
	private String address2;

	/* 電話番号 */
	private String tel;

	/* パスワード */
	private String pwd;

	/* 最終ログイン時刻 */
	private String lastLogin;

}
