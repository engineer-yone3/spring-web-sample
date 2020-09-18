package jp.co.example.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import jp.co.example.entity.MemberEntity;
import jp.co.example.form.MyPageForm;
import jp.co.example.form.SessionForm;
import jp.co.example.service.MemberService;

/**
 * 会員削除Controller
 * @author engineer-yone3
 *
 */
@Controller
public class MemberDelController {

	@Autowired
	MemberService memberService;

	@Autowired
	SessionForm session;

	/**
	 * 会員削除確認画面
	 * @param model
	 * @return memberdelconfirm.html
	 */
	@RequestMapping(value = "/memberdelconfirm", method = RequestMethod.GET)
	public String memberdeleteconfirm(Model model) {

		// check session
		if (StringUtils.isEmpty(session.getUserId())) {
			return "redirect:/";
		}

		try {
			MemberEntity entity = memberService.getMember(session.getUserId());

			if (null == entity) {
				return "redirect:/";
			}

			MyPageForm displayForm = new MyPageForm();

			displayForm.setUserId(entity.getUserId());
			displayForm.setUserEmail(entity.getUserEmail());
			displayForm.setUserName(entity.getUserName());
			displayForm.setUserNameKana(entity.getUserNameKana());
			String[] addressList = entity.getAddress1().split(",");
			if (addressList[0].length() > 3) {
				String zip1 = addressList[0].substring(0, 3);
				String zip2 = addressList[0].substring(3);
				displayForm.setZip(zip1 + "-" + zip2);
			} else {
				displayForm.setZip(addressList[0]);
			}

			displayForm.setTodofuken(addressList[1]);
			displayForm.setShikugun(addressList[2]);
			displayForm.setAddress2(entity.getAddress2());
			displayForm.setTel(entity.getTel());

			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			displayForm.setLastLogin(df.format(session.getLastLogin()));

			model.addAttribute("myPageForm", displayForm);

			return "memberdelconfirm";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}

	}

	/**
	 * 会員削除実行->完了処理
	 * @param form
	 * @return logout(redirect)
	 */
	@RequestMapping(value = "/memberdelcomplete", method = RequestMethod.POST)
	public String memberdeletecomplete(@ModelAttribute("myPageForm") MyPageForm form) {

		memberService.delMember(session.getUserId());

		return "redirect:/logout";

	}

}
