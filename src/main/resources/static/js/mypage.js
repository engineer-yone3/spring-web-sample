/**
 * マイページ画面用js
 */
$(function() {

	$('#logoutlink').click(function() {

		swal({
			title: "Logout",
			text: "ログアウトします。よろしいですか？",
			icon: "warning",
			buttons : true,
			closeOnClickOutside: false
		}).then((result) => {
			if(result) {
				location.href = '/spring-web-sample/logout';
			} else {
				return;
			}
		});

	});

	$('#member-modify-link').click(function() {
		location.href = '/spring-web-sample/modify';
	});

	$('#member-delete-link').click(function() {
		location.href = '/spring-web-sample/memberdelconfirm';
	});

	$('#goBackMypagebtn').click(function() {
		location.href = '/spring-web-sample/mypage';
	});

});