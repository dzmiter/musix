<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Sign in</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="resources/css/bootstrap.min.css" rel="stylesheet">	
	<link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="resources/img/icon.png" type="image/x-icon" />
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
  </head>

  <body>

    <div class="container">
    
      <c:import url="header.jsp"></c:import>

      <spring:url value="/login" var="url" />			
	  <form:form class="form-signin" method="post"
				action="${url}" commandName="user">
	      <h2 class="form-signin-heading">Please sign in</h2>
	      <center><form:errors path="email" cssStyle="color:red;"/></center>
		  <form:input type="text" class="input-block-level" placeholder="Email address" path="email"/>
		  <center><form:errors path="password" cssStyle="color:red;"/></center>
		  <form:input type="password" class="input-block-level" placeholder="Password" path="password"/>
		  <label class="checkbox">
          	<input name="remember-me" type="checkbox" value="remember-me" <c:if test="${rememberme != null}"> 
          		checked </c:if> /> Remember me
          </label>
		  <button class="btn btn-large btn-primary" type="submit">Sign in</button>		  
	  </form:form>
	  
	  <c:import url="footer.jsp"></c:import>

    </div> <!-- /container -->

  </body>
</html>