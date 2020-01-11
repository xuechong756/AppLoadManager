<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<script type="text/javascript">

	function initial()
	{
		var infor = navigator.userAgent.toLowerCase();
		var os = "";
		if(contains(infor, "iphone")||contains(infor, "ipad"))
			os = "2";
		else if(contains(infor, "android"))
			os= "1";
		window.location = "core?"+${query}+"&os="+os;
	}
	/*检测是否包含*/
	function contains(original, target) {
		if (original.indexOf(target) >= 0)
			return true;
		return false;
	}
</script>
</head>
<body onload="initial()">
</body>
</html>