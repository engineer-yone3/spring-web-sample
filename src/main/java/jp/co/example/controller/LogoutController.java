package jp.co.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.example.form.SessionForm;

/**
 * ログアウト処理Controller
 * @author engineer-yone3
 *
 */
@Controller
public class LogoutController {

	@Autowired
	SessionForm session;

	/**
	 * ログアウト処理
	 * @param model
	 * @param status
	 * @return index(redirect)
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, SessionStatus status) {

		status.setComplete();
		session = null;
		return "redirect:/";
	}

}
