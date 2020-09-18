package jp.co.example.exception;

/**
 * 会員情報登録済みException
 * @author engineer-yone3
 *
 */
public class MemberRegistDuplicationException extends Exception {

	public MemberRegistDuplicationException(String msg) {
		super(msg);
	}

}
