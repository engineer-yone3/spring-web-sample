package jp.co.example.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import jp.co.example.entity.MemberEntity;
import jp.co.example.form.RegistForm;
import jp.co.example.form.SessionForm;
import jp.co.example.service.MemberService;

/**
 * 会員変更Controller
 * ログイン後の処理であるためセッションを確認し
 * セッションエラーの場合はログイン画面にリダイレクトさせる
 * @author engineer-yone3
 *
 */
@Controller
public class MemberModController {

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
	 * 会員情報変更 入力処理
	 * @param model
	 * @return modifyinput.html
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model) {

		// check session
		if (StringUtils.isEmpty(session.getUserId())) {
			return "redirect:/";
		}

		try {
			MemberEntity entity = memberService.getMember(session.getUserId());

			if (null == entity) {
				// missing login
				return "redirect:/";
			}

			RegistForm displayForm = new RegistForm();

			displayForm.setUserMail(entity.getUserEmail());
			displayForm.setName(entity.getUserName());
			displayForm.setKana(entity.getUserNameKana());
			String[] addressList = entity.getAddress1().split(",");
			displayForm.setZip(addressList[0]);

			displayForm.setTodofuken(addressList[1]);
			displayForm.setShikugun(addressList[2]);
			displayForm.setAddress(entity.getAddress2());
			displayForm.setTel(entity.getTel());

			model.addAttribute("modifyForm", displayForm);

			return "modifyinput";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 会員情報変更 確認処理
	 * @param form
	 * @param errors
	 * @param model
	 * @return modifyconfirm.html
	 */
	@RequestMapping(value = "/modifyconfirm", method = RequestMethod.POST)
	public String modifyConfirm(@ModelAttribute("modifyForm") RegistForm form, Errors errors, Model model) {

		Object[] groups;
		if (StringUtils.isEmpty(form.getPwd())) {
			groups = new ArrayList<Object>(Arrays.asList(MemberCheckGroupB.class)).toArray();
		} else {
			groups = new ArrayList<Object>(Arrays.asList(MemberCheckGroupA.class)).toArray();
		}
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
			return "modifyinput";
		}

		String pwdMask = "";
		if (StringUtils.isEmpty(form.getPwd())) {
			pwdMask = "【変更なし】";
		} else {
			for (int i = 0; i < form.getPwd().length(); i++) {
				pwdMask = pwdMask + "*";
			}
		}

		model.addAttribute("modifyForm", form);
		model.addAttribute("pwdMask", pwdMask);

		return "modifyconfirm";
	}

	/**
	 * 会員情報変更 更新処理
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return modifycomplete.html
	 */
	@RequestMapping(value = "/modifycomplete", method = RequestMethod.POST)
	public String modifyComplete(@ModelAttribute("registForm") RegistForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "registinput";
		}

		try {

			MemberEntity modifyEntity = new MemberEntity();
			modifyEntity.setUserId(session.getUserId());
			modifyEntity.setUserEmail(form.getUserMail());
			modifyEntity.setUserName(form.getName());
			modifyEntity.setUserNameKana(form.getKana());
			modifyEntity.setTel(form.getTel());
			String address1 = form.getZip() + "," + form.getTodofuken() + "," + form.getShikugun();
			modifyEntity.setAddress1(address1);
			modifyEntity.setAddress2(form.getAddress());
			modifyEntity.setPwd(form.getPwd());

			MemberEntity modifyed = memberService.modifyMember(modifyEntity);

			// セッションを更新
			session.setUserMail(modifyed.getUserEmail());

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "modifycomplete";
	}

}
