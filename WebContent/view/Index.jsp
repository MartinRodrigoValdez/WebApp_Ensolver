<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags" %>



<mt:base title="Index">

<jsp:attribute name="errorInContent">
 <c:if test="${not empty error }">
  <c:if test="${not empty error['ERROR IN CONTENT'] }">
<label>${error['ERROR IN CONTENT'] }</label>
</c:if>
</c:if>
</jsp:attribute>

<jsp:attribute name="messageInContent">
 <c:if test="${not empty message }">
<label>${message}</label>
</c:if>
</jsp:attribute> 

	<jsp:attribute name="content">
		<div class="col-md-9 bot">
			<h2 class="ratatouille">Welcome to Ensolver to-do Task</h2>
			  
			
			  <form:form name='loginForm' action="/WebAppEnsolver/User/LogIn/" modelAttribute="UserToLog" method='POST'>
						<div class="form-group">
							<form:label class="control-label col-sm-3" path="name">User name: </form:label>
							<div class="col-sm-8">
								<form:input path="name" type="text" class="form-control" id="iusername" name="username"	placeholder="name user" required="true"/>
								<br>
   							</div> 
						</div>
						<div class="form-group">
							<form:label class="control-label col-sm-3" path="password">Password: </form:label>
							<div class="col-sm-8">
								<form:password path="password" minlength="6" maxlength="20" class="form-control" id="password" name="password" placeholder="Password" required="true"/>
								<br>
   							</div> 
						</div>
					</div>	
					
				    <div class="form-group">
   						<div class="col-sm-6 col-sm-offset-6">
				    		<button type="submit" class="btn btn-default"> Log me in</button> 
				    	</div>
				    </div>
			</form:form>
		</div>
	</jsp:attribute>

</mt:base>
