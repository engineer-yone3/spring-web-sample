/**
 * 会員情報入力画面用js
 */
$(function() {

	$('#isPasswordVisible').change(function() {
		if ($(this).prop('checked')) {
			$('#pwd').attr('type', 'text');
		} else {
			$('#pwd').attr('type', 'password');
		}
	});

	$(document).ready(function() {

		var errorElement = $('.form-error');

		if(errorElement.length) {
			if(errorElement.find('p:contains("Eメールアドレス")').length) {
				$('#userMail').addClass('input-error');
			}
			if(errorElement.find('p:contains("パスワード")').length) {
				$('#pwd').addClass('input-error');
			}
			if(errorElement.find('p:contains("氏名")').length) {
				if(errorElement.find('p:contains("氏名")').length > 1) {
					var name1 = errorElement.find('p:contains("氏名")')[0];
					var name2 = errorElement.find('p:contains("氏名")')[1];
					if($(name1).text().startsWith('氏名(カナ)')) {
						$('#kana').addClass('input-error');
					} else {
						$('#name').addClass('input-error');
					}
					if($(name2).text().startsWith('氏名(カナ)')) {
						$('#kana').addClass('input-error');
					} else {
						$('#name').addClass('input-error');
					}
				} else {
					if(errorElement.find('p:contains("氏名(カナ)")').length) {
						$('#kana').addClass('input-error');
					} else {
						$('#name').addClass('input-error');
					}
				}
			}
			if(errorElement.find('p:contains("電話番号")').length) {
				$('#tel').addClass('input-error');
			}
			if(errorElement.find('p:contains("郵便番号")').length) {
				$('#zip').addClass('input-error');
			}
			if(errorElement.find('p:contains("都道府県")').length) {
				$('#todofuken').addClass('input-error');
			}
			if(errorElement.find('p:contains("市区郡")').length) {
				$('#shikugun').addClass('input-error');
			}
			if(errorElement.find('p:contains("以降の住所")').length) {
				$('#address').addClass('input-error');
			}

		}
	});

	$('#mypagebtn').click(function() {
		location.href = '/spring-web-sample/mypage';
	});

	$('#goBackMypagebtn').click(function() {
		location.href = '/spring-web-sample/mypage';
	});

	$('#goback-membermod-input-btn').click(function() {
		location.href = '/spring-web-sample/modify';
	});

});