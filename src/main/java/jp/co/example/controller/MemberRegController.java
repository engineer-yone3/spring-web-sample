package jp.co.example.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.entity.MemberEntity;
import jp.co.example.exception.MemberRegistDuplicationException;
import jp.co.example.form.RegistForm;
import jp.co.example.form.SessionForm;
import jp.co.example.service.MemberService;

/**
 * 会員登録Controller
 * @author engineer-yone3
 *
 */
@Controller
public class MemberRegController {

	@Autowired
	Validator validator;

	@Autowired
	MemberService memberService;

	@Autowired
	PasswordEncoder passwordEncorder;

	@Autowired
	MessageSource messageSource;

	@Autowired
	SessionForm session;

	/**
	 * 新規会員登録 入力処理
	 * @param model
	 * @return registinput.html
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String regist(Model model) {

		model.addAttribute("registForm", new RegistForm());
		return "registinput";
	}

	/**
	 * 新規会員登録 確認処理
	 * @param form
	 * @param errors
	 * @param model
	 * @return registconfirm.html
	 */
	@RequestMapping(value = "/registconfirm", method = RequestMethod.POST)
	public String confirm(@ModelAttribute("registForm") RegistForm form, Errors errors, Model model) {

		Object[] groups = new ArrayList<Object>(Arrays.asList(MemberCheckGroupA.class)).toArray();
		ValidationUtils.invokeValidator(validator, form, errors, groups);

		String zip = form.getZip();
		String todofuken = form.getTodofuken();
		String shikugun = form.getShikugun();

		if (StringUtils.isEmpty(zip) || StringUtils.isEmpty(todofuken) || StringUtils.isEmpty(shikugun)) {
		} else {
			if (126 < zip.length() + todofuken.length() + shikugun.length()) {
				errors.rejectValue("shikugun", "AddressCheckValidator.registForm.shikugun",
						"zip, todofuken, shikugun length error");
			}
		}

		if (errors.hasErrors()) {
			return "registinput";
		}

		String pwdMask = "";
		for (int i = 0; i < form.getPwd().length(); i++) {
			pwdMask = pwdMask + "*";
		}

		model.addAttribute("registForm", form);
		model.addAttribute("pwdMask", pwdMask);

		return "registconfirm";
	}

	/**
	 * 新規会員登録 登録処理
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return registcomplete.html
	 */
	@RequestMapping(value = "/registcomplete", method = RequestMethod.POST)
	public String complete(@ModelAttribute("registForm") @Validated RegistForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "registinput";
		}

		try {

			MemberEntity registEntity = new MemberEntity();
			registEntity.setUserEmail(form.getUserMail());
			registEntity.setUserName(form.getName());
			registEntity.setUserNameKana(form.getKana());
			registEntity.setTel(form.getTel());
			String address1 = form.getZip() + "," + form.getTodofuken() + "," + form.getShikugun();
			registEntity.setAddress1(address1);
			registEntity.setAddress2(form.getAddress());

			String password = passwordEncorder.encode(form.getPwd());
			registEntity.setPwd(password);

			MemberEntity registed = memberService.RegistMenber(registEntity);

			// create session
			session.setUserId(registed.getUserId());
			session.setUserMail(registed.getUserEmail());
			session.setLastLogin(new Timestamp(System.currentTimeMillis()));

		} catch (MemberRegistDuplicationException me) {

			FieldError error = new FieldError(bindingResult.getObjectName(), "registForm.userMail",
					messageSource.getMessage("Duplicate.registForm.userMail", null, Locale.JAPANESE));
			bindingResult.addError(error);
			return "registinput";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "registcomplete";
	}

}
