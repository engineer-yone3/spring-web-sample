package jp.co.example.form;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * セッションForm
 * @author engineer-yone3
 *
 */
@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionForm implements Serializable {

	/* ユーザーID */
	private String userId;

	/* メールアドレス */
	private String userMail;

	/* 最終ログイン時刻 */
	private Timestamp lastLogin;

}
