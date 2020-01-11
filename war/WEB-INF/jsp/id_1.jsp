<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"content="text/html; charset=UTF-8;">
<meta charset="UTF-8" name="apple-itunes-app" content="app-id=414478124,affiliate-data=myAffiliateData app-argument=abc://">
<title>分享</title>

</head>
<script type="text/javascript">
	var appOkHandle;
	var appInfor = ${result}
	var localtion = navigator.userAgent.toLowerCase();
	function initial() {
		if (contains(localtion, 'micromessenger'))//显示微信提示
		{
			document.getElementById("weixinlayout").style.background = "rgba(0,0,0,0.6)";
			document.getElementById("noticeImage").style.display = "";
		} else {
			document.getElementById("downbutton").onclick = appstart;
		}

		document.getElementById("appname").innerText = appInfor.appname;
		document.getElementById("desc").innerText = appInfor.desc;
		
		window.location = appInfor.deeplink;
		showWXLayout();
	}

	function appstart() {
		window.location.href = appInfor.downurl;
	}

	/*检测是否包含*/
	function contains(original, target) {
		if (original.indexOf(target) >= 0)
			return true;
		return false;
	}

	function showWXLayout() {
		var layout = document.getElementById('weixinlayout');
		var closebtn = document.getElementById('closebtn');
		layout.style.display = 'block';
		closebtn.onclick = function() {
			layout.style.background = "";
			document.getElementById("noticeImage").style.display = "none";
		};
	}
</script>
<style>
html, body {
	margin: 0;
	padding: 0;
	-webkit-tap-highlight-color: transparent;
	height: 100%;
	overflow: hidden;
}

#div1body {
	width: 100%;
	text-align: center;
	height: 100%;
}

#appname, #desc {
	color: #4680A5;
}

#appname {
	font-size: 56px;
	display: block;
}

#desc {
	font-size: 40px;
	display: block;
	margin: 10px;
	margin-top: 10px;
	-webkit-box-direction: normal;
}

#foot {
	margin-top: 40%;
	width: 100%;
}

#downbutton {
	margin-top: 15px;
	display: inline-block;
	line-height: 40px;
	border-radius: 3px;
	font-size: 17px;
	color: green;
	text-align: center;
	border: 1px solid green;
	text-decoration: none
}

.weixinlayout {
	display: none;
	left: 0;
	top: 0;
	filter: alpha(opacity = 80);
	width: 100%;
	height: 100%;
	z-index: 100;
}
</style>
<body onload="initial()" id="div1body"style="background-image: url(<%=basePath%>/image/background.jpg);no-repeat">
	<div id="weixinlayout" class="weixinlayout">
		<div style="padding-top: 4%">
			<span id="appname"></span> <span id="desc"></span>
		</div>
		<div id="foot">
			<img id="downbutton" src="<%=basePath%>/image/download.png" />
		</div>
		<div style="position: absolute; left: 34%; top: 4%;">
			<div id="noticeImage" style="display: none;">
				<span id="closebtn" title="关闭"style="float: left; margin-top: 20%; display: block;">×</span> 
				<img src="<%=basePath%>/image/weixinguide.png" />
			</div>
		</div>
	</div>
</body>
</html>