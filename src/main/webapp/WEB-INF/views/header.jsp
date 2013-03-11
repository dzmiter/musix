<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<form class="navbar-search pull-left">
			  <input type="text" class="search-query" placeholder="Search">
			</form>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="<spring:url value="/"></spring:url>">Home</a></li>
					<c:if test="${myId == null}"><li><a href="registration">Registration</a></li></c:if>
				</ul>
			</div>
			<c:choose>
			<c:when test="${myId == null}">				
				<div class="nav-collapse collapse">
		            <p class="navbar-text pull-right">
		              <a href='<spring:url value="/login"></spring:url>'>Log in</a>
		            </p>	            
          		</div>
	        </c:when>
	        <c:otherwise>
	        	<div class="nav-collapse collapse">
		            <p class="navbar-text pull-right">
		              Logged in as <a href='<spring:url value="/"></spring:url>'>${email}</a>
		              &nbsp; &nbsp; &nbsp;
		              <a href='<spring:url value="/logout"></spring:url>'>Log out</a>
		            </p>	            
          		</div>
	        </c:otherwise>
            </c:choose>
		</div>
	</div>
</div>