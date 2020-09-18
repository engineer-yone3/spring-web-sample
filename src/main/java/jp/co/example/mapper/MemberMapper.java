package jp.co.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.example.entity.MemberEntity;

/**
 * 会員情報操作Mapper
 * @author engineer-yone3
 *
 */
@Mapper
public interface MemberMapper {

	/**
	 * 会員情報取得(複数・メールアドレスで検索)
	 * @param cond_mail
	 * @return
	 */
	List<MemberEntity> getMembers(String cond_mail);

	/**
	 * 会員情報登録
	 * @param entity
	 */
	void registNewMember(MemberEntity entity);

	/**
	 * ユーザーID最大値取得
	 * @return
	 */
	String getMaxUserId();

	/**
	 * ログイン
	 * @param cond_mail
	 * @return
	 */
	List<MemberEntity> doLogin(String cond_mail);

	/**
	 * 最終ログイン時刻更新
	 * @param userId
	 */
	void updateLastLogin(String userId);

	/**
	 * 会員情報取得(単一・ユーザーIDで検索)
	 * @param userId
	 * @return
	 */
	MemberEntity getMemberById(String userId);

	/**
	 * 会員退会(論理削除)
	 * @param userId
	 */
	void deleteMember(String userId);

	/**
	 * 会員情報更新
	 * @param entity
	 */
	void modifyMember(MemberEntity entity);

}
