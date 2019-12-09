<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="샘플 게시판 로그인 페이지">
    <meta name="author" content="dev-jsa">
    <title>Please sign in</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" >
    <style type="text/css">
    	body {
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #eee;
		}
		
		.form-signin {
		  max-width: 330px;
		  padding: 15px;
		  margin: 0 auto;
		}
		.form-signin .form-signin-heading,
		.form-signin .checkbox {
		  margin-bottom: 10px;
		}
		.form-signin .checkbox {
		  font-weight: 400;
		}
		.form-signin .form-control {
		  position: relative;
		  box-sizing: border-box;
		  height: auto;
		  padding: 10px;
		  font-size: 16px;
		}
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		.form-signin input[type="email"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		.form-signin input[type="password"] {
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
    
    
    </style>
  </head>
  <body>
     <div class="container">
      <form class="form-signin" >
        <h2 class="form-signin-heading">로그인</h2>
		<div class="alert alert-dange hide" role="alert"></div>        
		<p>
          <label for="email" class="sr-only">Email</label>
          <input type="email" id="email" name="email" class="form-control" placeholder="Email" required autofocus>
        </p>
        <p>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <button class="btn btn-lg btn-primary btn-block login" >로그인</button>
        	<img alt="카카오 로그인" class="btn btn-block kakaoLogin" src="/static/img/btn_kaka.png">
        <p>
        	<input type="button" class="btn btn-lg btn-primary btn-block register" value="회원가">
        </p>	
      </form>
</div>
</body>
<script type="text/javascript" src="/webjars/jquery/dist/jquery.min.js"></script>
</html>