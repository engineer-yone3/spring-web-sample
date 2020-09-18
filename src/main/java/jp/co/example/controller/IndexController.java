package jp.co.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.form.LoginForm;

/**
 * ログイン画面Controller
 * @author engineer-yone3
 *
 */
@Controller
public class IndexController {

	/**
	 * ログイン画面表示
	 * @param model
	 * @return index.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("loginForm", new LoginForm());

		return "index";
	}

}
