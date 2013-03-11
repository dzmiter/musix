<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">	
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 75%;
        padding: 19px 29px 29px;
        margin: 20px auto 20px;
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
    <c:import url="links.jsp"></c:import>
  </head>

  <body>
    <div class="container">
    
		<c:import url="header.jsp"></c:import>
		
		<spring:url value="/registration_do" var="url" />
	    <form:form class="form-signin" method="post"
	             action="${url}" commandName="user">
	        <h2 class="form-signin-heading">Registration form</h2>
	        <center><form:errors path="firstName" cssStyle="color:red;"></form:errors></center>
			<form:input type="text" class="input-block-level" placeholder="First name" path="firstName"/>
			<center><form:errors path="lastName" cssStyle="color:red;"></form:errors></center>
			<form:input type="text" class="input-block-level" placeholder="Last name" path="lastName"/>
			<center><form:errors path="email" cssStyle="color:red;"></form:errors></center>
	        <form:input type="text" class="input-block-level" placeholder="Email address" path="email"/>
	      	<center><form:errors path="password" cssStyle="color:red;"></form:errors></center>
	        <form:input type="password" class="input-block-level" placeholder="Password" path="password"/>
	        <center><form:errors path="role" cssStyle="color:red;"></form:errors></center>
	        <input name="repeat" type="password" class="input-block-level" placeholder="Repeat password"
	        	value="${repeat}"/>
	        <button class="btn btn-large btn-primary" type="submit">Register</button>
	    </form:form>

        <c:import url="footer.jsp"></c:import>
        
    </div>

  </body>
</html>



