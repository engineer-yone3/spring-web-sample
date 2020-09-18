package jp.co.example.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.entity.MemberEntity;
import jp.co.example.exception.MemberRegistDuplicationException;
import jp.co.example.mapper.MemberMapper;

@Component
public class MemberServiceImpl implements MemberService {

	@Autowired
	protected MemberMapper memberMapper;

	@Autowired
	protected PasswordEncoder passwordEncorder;

	/**
	 * 会員情報登録
	 * @param regEntity
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@Override
	public MemberEntity RegistMenber(MemberEntity regEntity) throws Exception {

		List<MemberEntity> members = memberMapper.getMembers(regEntity.getUserEmail());

		if (members != null) {
			if (members.size() > 0) {
				for (MemberEntity member : members) {
					if (regEntity.getUserEmail().equals(member.getUserEmail())) {
						throw new MemberRegistDuplicationException("会員登録済みです");
					}
				}
			}
		}

		String userId = memberMapper.getMaxUserId();
		if (StringUtils.isEmpty(userId)) {
			regEntity.setUserId("0000000001");
		} else {
			Integer userIdInt = Integer.valueOf(userId);
			userIdInt++;
			regEntity.setUserId(String.format("%010d", userIdInt));
		}
		memberMapper.registNewMember(regEntity);

		return regEntity;

	}

	/**
	 * 会員情報確認(ログイン確認)
	 * @param eMail
	 * @param password
	 * @return
	 */
	@Override
	public MemberEntity checkMember(String eMail, String password) {

		try {
			List<MemberEntity> members = memberMapper.doLogin(eMail);

			if (members == null) {
				return null;
			}

			if (members.size() == 0) {
				return null;
			}

			if (members.size() > 1) {
				return null;
			}

			String dbPassword = members.get(0).getPwd();
			if (!passwordEncorder.matches(password, dbPassword)) {
				return null;
			}

			return members.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 最終ログイン時刻更新
	 * @param userId
	 */
	@Transactional
	@Override
	public void updateLastLogin(String userId) {

		try {
			memberMapper.updateLastLogin(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会員情報取得(単一・ユーザーIDで検索)
	 * @param userId
	 * @return
	 */
	@Override
	public MemberEntity getMember(String userId) {
		return memberMapper.getMemberById(userId);
	}

	/**
	 * 会員退会(論理削除)
	 * @param userId
	 */
	@Transactional
	@Override
	public void delMember(String userId) {
		try {
			memberMapper.deleteMember(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会員情報更新
	 * @param entity
	 * @return
	 */
	@Transactional
	@Override
	public MemberEntity modifyMember(MemberEntity entity) {

		try {

			MemberEntity after = entity.clone(entity);
			after.setUserId(entity.getUserId());
			if (StringUtils.isEmpty(entity.getPwd())) {
				MemberEntity before = getMember(entity.getUserId());
				after.setPwd(before.getPwd());
			} else {
				after.setPwd(passwordEncorder.encode(entity.getPwd()));
			}

			memberMapper.modifyMember(after);

			return after;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
