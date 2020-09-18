package jp.co.example.service;

import org.springframework.stereotype.Service;

import jp.co.example.entity.MemberEntity;

/**
 * 会員サービス
 * @author engineer-yone3
 *
 */
@Service
public interface MemberService {

	/**
	 * 会員情報登録
	 * @param regEntity
	 * @return
	 * @throws Exception
	 */
	public MemberEntity RegistMenber(MemberEntity regEntity) throws Exception;

	/**
	 * 会員情報確認(ログイン確認)
	 * @param eMail
	 * @param password
	 * @return
	 */
	public MemberEntity checkMember(String eMail, String password);

	/**
	 * 最終ログイン時刻更新
	 * @param userId
	 */
	public void updateLastLogin(String userId);

	/**
	 * 会員情報取得(単一・ユーザーIDで検索)
	 * @param userId
	 * @return
	 */
	public MemberEntity getMember(String userId);

	/**
	 * 会員退会(論理削除)
	 * @param userId
	 */
	public void delMember(String userId);

	/**
	 * 会員情報更新
	 * @param entity
	 * @return
	 */
	public MemberEntity modifyMember(MemberEntity entity);

}
