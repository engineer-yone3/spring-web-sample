package jp.co.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 会員情報Entity
 * @author engineer-yone3
 *
 */
@Getter
@Setter
@Entity
@Table("t_member")
public class MemberEntity implements Cloneable {

	/* ユーザーID(DB用採番) */
	@Id
	@Column("user_id")
	private String userId;

	/* Eメールアドレス(フロント用ユーザーID) */
	@Column("user_email")
	private String userEmail;

	/* データ登録日 */
	@Column("reg_date")
	private Timestamp regDate;

	/* データ更新日 */
	@Column("upd_date")
	private Timestamp updDate;

	/* データ論理削除フラグ */
	@Column("is_delete")
	private String isDelete;

	/* データ削除日 */
	@Column("del_date")
	private Timestamp delDate;

	/* 氏名 */
	@Column("user_name")
	private String userName;

	/* 氏名カナ */
	@Column("user_name_kana")
	private String userNameKana;

	/* 住所(郵便番号・都道府県・市区郡) */
	@Column("address1")
	private String address1;

	/* 住所(以降の住所) */
	@Column("address2")
	private String address2;

	/* 電話番号 */
	@Column("tel")
	private String tel;

	/* パスワード */
	@Column("pwd")
	private String pwd;

	/* 最終ログイン時刻 */
	@Column("last_login")
	private Timestamp lastLogin;

	public MemberEntity clone(MemberEntity before) throws CloneNotSupportedException {
		MemberEntity after = (MemberEntity) super.clone();
		after.setUserId(null);

		return after;
	}

}
