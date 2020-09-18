package jp.co.example.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.entity.MemberEntity;
import jp.co.example.form.LoginForm;
import jp.co.example.form.SessionForm;
import jp.co.example.service.MemberService;

/**
 * ログイン処理Controller
 * @author engineer-yone3
 *
 */
@Controller
public class LoginController {

	@Autowired
	MemberService memberService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	SessionForm session;

	/**
	 * ログイン処理
	 * @param form
	 * @param bindingResult
	 * @return mypage(redirect)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm") @Validated LoginForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "index";
		}

		MemberEntity entity = memberService.checkMember(form.getUserMail(), form.getPwd());

		if (null == entity) {
			// login NG
			FieldError error = new FieldError(bindingResult.getObjectName(), "loginForm.userMail",
					messageSource.getMessage("MissingLogin.loginForm.userMail", null, Locale.JAPANESE));
			bindingResult.addError(error);
			return "index";
		} else {
			// login OK
			session.setUserId(entity.getUserId());
			session.setUserMail(entity.getUserEmail());
			session.setLastLogin(entity.getLastLogin());

			memberService.updateLastLogin(entity.getUserId());
		}

		return "redirect:/mypage";
	}

}
