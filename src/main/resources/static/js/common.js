/**
 * ログイン・共通js
 */
var isInputErr = false;
$(function() {
	$('#userMail').blur(function() {

		$('#userMail').removeClass('input-error');
		$('#wrap-email').removeClass('email-err');
		isInputErr = false;

		if(!$('#userMail').val()) {
			return;
		}

		// メールアドレスチェック
		var mail = $('#userMail').val();
		if(!mail.match(/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$/)) {
			$('#userMail').addClass('input-error');
			$('#wrap-email').addClass('email-err');
			isInputErr = true;
		}
	});

	$('#pwd').blur(function() {

		$('#pwd').removeClass('input-error');
		$('#wrap-pwd').removeClass('pwd-err');
		isInputErr = false;

		if(!$('#pwd').val()) {
			return;
		}

		// パスワード桁数チェック
		if($('#pwd').val().length < 6) {
			$('#pwd').addClass('input-error');
			$('#wrap-pwd').addClass('pwd-err');
			isInputErr = true;
		}
	});

	$('#loginSubmit').click(function() {
		if(isInputErr) {
			return false;
		}

		if(!$('#userMail').val() && !$('#pwd').val()) {
			return false;
		}
	});

	$('#registbtn').click(function() {
		// 新規会員登録画面へ
		location.href = '/spring-web-sample/regist';
	});

	$(document).ready(function() {
		var refferstr = document.referrer.split('/');
		if(refferstr[refferstr.length - 1] === 'mypage') {
			swal({
				title: "Logout",
				text: "ログアウトが完了しました",
				icon: "success",
				closeOnClickOutside: false
			}).then((result) => {

			});
		} else if(refferstr[refferstr.length - 1] === 'memberdelconfirm') {
			swal({
				title: "Logout",
				text: "退会処理が完了しました",
				icon: "success",
				closeOnClickOutside: false
			}).then((result) => {

			});
		}
	});
});