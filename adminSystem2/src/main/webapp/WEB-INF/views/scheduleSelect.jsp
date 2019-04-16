<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 공학관의 장소와 시간을 정해주세요</h3>
	<form action = schedule>
		<select name='place'>
			<option value='' selected>-- 선택 --</option>
			<option value='101'>101호</option>
			<option value='102'>102호</option>
			<option value='103'>103호</option>
			<option value='104'>104호</option>
			<option value='105'>105호</option>
			<option value='106'>106호</option>
			<option value='107'>107호</option>
			<option value='108'>108호</option>
			<option value='109'>109호</option>
		</select> 
		<select name='start_time'>
			<option value='' selected>-- 선택 --</option>
			<option value='1'>1교시</option>
			<option value='2'>2교시</option>
			<option value='3'>3교시</option>
			<option value='4'>4교시</option>
			<option value='5'>5교시</option>
			<option value='6'>6교시</option>
			<option value='7'>7교시</option>
			<option value='8'>8교시</option>
			<option value='9'>9교시</option>
			<option value='10'>10교시</option>
		</select> 
		<select name='finish_time'>
			<option value='' selected>-- 선택 --</option>
			<option value='1'>1교시</option>
			<option value='2'>2교시</option>
			<option value='3'>3교시</option>
			<option value='4'>4교시</option>
			<option value='5'>5교시</option>
			<option value='6'>6교시</option>
			<option value='7'>7교시</option>
			<option value='8'>8교시</option>
			<option value='9'>9교시</option>
			<option value='10'>10교시</option>
		</select>
		<input type="submit">
	</form>
</body>
</html>