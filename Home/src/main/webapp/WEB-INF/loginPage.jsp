<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>New Log In Route</h1>
		<h4>Login:</h4>
			<form action="/users/login" method="post">
				<p>
					<label>Email
				 		<input type="text" name="email"/>
				 	</label>		
			 	</p>
			 	<p>
			 		<label>Password	
			 			<input type="password" name="password"/>
			 		</label>		
			 	</p>
			 	<input type="submit" class="btn btn-default" value="Login"/>
			 </form> 
	</body>
</html>