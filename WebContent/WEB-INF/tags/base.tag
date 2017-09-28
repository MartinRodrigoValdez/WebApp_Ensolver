<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="title" required="true" rtexprvalue="true" %>
<%@ attribute name="content" fragment="true" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="errorInContent" fragment="true" %>
<%@ attribute name="messageInContent" fragment="true" %>
<%@ attribute name="scriptsForShowModalWhenHaveErrors" fragment="true" %>



<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
	
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	
	<!-- Fonts CSS -->
	<link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Fjalla+One" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Arvo" rel="stylesheet">
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-social.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css">
	
	<!-- Our CSS -->
	
	<link rel="stylesheet" media="(min-width: 750px)"  href="<%=request.getContextPath()%>/resources/css/custom.css">
	<link rel="stylesheet" media="(max-width: 750px ) and ( min-width: 350px)"  href="<%=request.getContextPath()%>/resources/css/customMobile.css">
	<link rel="stylesheet" media="(max-width: 350px )"  href="<%=request.getContextPath()%>/resources/css/customMobileXtreme.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/simple-sidebar.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/googlemapStyle.css">
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
	
	
	<!-- Scripts -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery224.js"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/angularApp.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/app.js"></script>
	
	<jsp:invoke fragment="head"></jsp:invoke>
	<title>${title}</title>

</head>

<body ng-app="app">
			  
	<nav class="navbar navbar-default navbar-fixed-top navbar-background">

		<div class="col-md-offset-1">
			<div class="">
			 	<!-- SEARCH MODAL -->
				<a class="navbar-brand ">Ensolver</a>
			</div>
			<div id="navbar" class="">
				<ul class="nav navbar-nav navbar-buttons-position rigthStyle navOptions">
					<c:if test="${empty userLoged }">
						<li><a href="<%=request.getContextPath()%>/User/SignUp" class="navVarButtonsContrastStyle"><span class="glyphicon fa-md glyphicon-user"></span>Sign up</a></li>
					</c:if>
					<c:if test="${not empty userLoged }">
					<li class="blackContrastStyle">	Logued as <c:out value="${userLoged}"></c:out> </li>
					<li><a href="<%=request.getContextPath()%>/User/LogOut" class="navVarButtonsContrastStyle"><span class="glyphicon fa-md glyphicon-log-out"></span>Log out</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	
	<!--  col-md-6 col-md-offset-2-->
	<div class="content" ng-controller="toDoDetailsController">
		<div class="error">
			<jsp:invoke fragment="errorInContent"></jsp:invoke>
		</div>
		
		<div class="message">
			<jsp:invoke fragment="messageInContent"></jsp:invoke>
		</div>

		<jsp:invoke fragment="content"></jsp:invoke>
		
		
			<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
		
		
	</div>
	










<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>



<jsp:invoke fragment="scriptsForShowModalWhenHaveErrors"></jsp:invoke>


</body>
</html>