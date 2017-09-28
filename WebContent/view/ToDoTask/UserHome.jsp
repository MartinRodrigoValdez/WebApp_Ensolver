<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:base title="User-Home">
	<jsp:attribute name="head">
	
	<script src="${pageContext.request.contextPath}/resources/js/ensolverScripts.js" type="text/javascript"></script>
		
	
<script>
		
function moveDataToModalConfirmationSwitchToDoTask(element) {
	element.id;
	var id=element.id;
	id=id.replace("checkTask","");
	
	var status =document.getElementById("checkTask"+id).checked;
	document.getElementById("statusModal").value=status;
	
	document.getElementById("idToDoTaskToSwitch").value=id;
}

function restoreStatus(){
	var id =document.getElementById("idToDoTaskToSwitch").value;
	if(document.getElementById("checkTask"+id).checked == 0){
		document.getElementById("checkTask"+id).checked=1;
	}else{
		document.getElementById("checkTask"+id).checked=0;
	}
	
	
}

function ShowOnlyDontDone(element){
	var status= element.checked;
	if (status){
		$(".doneToDoTask").hide();
	}else{
		$(".doneToDoTask").show();
	}
	
}
			
			
		</script> 







	</jsp:attribute>




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
		<div class="col-md-5 bot toDoList ">	
		
		<h2>My to-do list</h2>
		<c:if test="${!empty toDoList}">
			<label class="checkbox-inline"><input class="check" type="checkbox"  name="onlyDontDone" onclick="ShowOnlyDontDone(this)"> Show only don't done</label>
		</c:if>				
		
			<%-- <h3 >${restaurant.name}</h3>
					${restaurant.category}
				${restaurant.category.benefit} --%>
				
		
		
				
			<c:if test="${empty toDoList}">
				<div class="listOfTask ">
					<h4>to-do list empty</h4>
				</div>
			</c:if> 
		
			<c:if test="${!empty toDoList}">
				<div class="listOfTask listScroll">
					<c:forEach items="${toDoList}" var="toDoTask">
						
						<div class="<c:if test="${toDoTask.done}"> doneToDoTask </c:if> <c:if test="${not toDoTask.done}"> dontDoneToDoTask </c:if> ">
						<input id="checkTask${toDoTask.idToDoTask}" data-toggle="modal" data-target="#switchStatusToDoTask"	class="check" type="checkbox"  name="doneList" <c:if test="${toDoTask.done}"> checked="checked" </c:if> id="switchToDoTask${toDoTask.idToDoTask}"  onclick="moveDataToModalConfirmationSwitchToDoTask(this)">
									
						<label id="ToDoTask${toDoTask.idToDoTask}title">${toDoTask.title }</label>
						
						<a href="#"  class="fa fa-trash blackStyleLink" title="Delete to-do task" id="removeToDoTask${toDoTask.idToDoTask}" value="Delete" name="removeToDoTask" onclick="moveDataToModalConfirmationDeleteToDoTask(this)">Delete</a>
						
						<a href="#" ng-click="beginSearch(${toDoTask.idToDoTask})" class="fa fa-pencil blackStyleLink" title="Edit to-do task" id="editToDoTask${toDoTask.idToDoTask}" value="Edit" name="editToDoTask" data-toggle="modal" data-target="#editToDoTask">Edit</a>
					<!-- 	onclick="moveDataToModalConfirmationEditToDoTask(this)" -->
						</div>
					</c:forEach>
				</div>
			</c:if> 
		
		<br>
		<div class="form-group">
		<a href="#" class="blackContrastStyleLink"><span class="fa fa-md fa-plus-square" data-toggle="modal" data-target="#modNewToDoTask"> Create new to-do item</span></a>
		</div>
			
		<!-- <a href="#" id="2" ng-click="beginSearch($event)">{{texto1}} </a> -->
			
	
		</div> 
		
		
		
		
	
	
	
	
	
		<!-- 									TO-DO TASK									 -->
			
			
			
			<!-- ADD TO-DO TASK -->
			<div class="modal fade" id="modNewToDoTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header headers blackHeaderModalStyle">
							<h5 class="modal-title" id="exampleModalLabel">New to-do Task</h5>
						</div>
						<div class="modal-body">
							<c:if test="${not empty error}">
								<c:if test="${not empty error['ERROR NEW TO-DO TASK']}">
				    				<div>
				    					<label>${error['ERROR NEW TO-DO TASK']}</label>
				   					</div>
				    			</c:if>
				    		</c:if>
							<form:form action="SaveNewToDoTask/" method="POST" modelAttribute="newToDoTask" id="" class="">
							<div class="form-group">
								<form:label class="control-label col-sm-3" path="title">Title: </form:label>
								<div class="col-sm-8">
									<form:input class="form-control" path="title" id="title"	placeholder="Title of to-do" required="true" minlength="2"/>
									<br>
   								</div> 
							</div>
							
							 <div class="form-group">
   								<form:label class="control-label col-sm-3" path="dueDate">Due date (dd/mm/yy): </form:label>
   								<div class="col-sm-8">
    								<form:input pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])[- /.](0[1-9]|1[012])[- /.][0-9]{2}"  class="form-control" path="dueDate" id="dueDate" placeholder="Due date of to-do:" required="true"/>
    								<br>
   								</div> 
  							</div>
							
							
							
							<%--  <div class="form-group">
   								<form:label class="control-label col-sm-2" path="dueDate">Due date (dd/mm/aa): </form:label>
   								<div class="col-sm-6">
    								<form:input class="form-control" path="dueDate" id="dueDate" placeholder="Due date of to-do:" required="true"/>
    								<br>
   								</div> 
  							</div> --%>
							
							
							
							<div class="form-group">
								<form:label class="control-label col-sm-3" path="description">Description: </form:label>
								<div class="col-sm-8">
									<form:textarea class="form-control" path="description" id="description" placeholder="Description of to-do" required="true" minlength="5" maxlength="500"/>
									<br>
   								</div> 
							</div>
							
							<div class="form-group checkbox-position">
								<label class="checkbox-inline"><form:checkbox  class="check" name="done" path="done" />Done</label>
							
							</div>
							
							<form:hidden path="userOwner.idUser" id="user" />
			
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<button type="submit" class="btn btn-default"> Save</button> 
							</div>
						</form:form>
					</div>
				</div>
			</div>
			</div>
			
			<!-- EDIT TO-DO TASK -->
			<div class="modal fade" id="editToDoTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header headers blackHeaderModalStyle">
							<h5 class="modal-title" id="exampleModalLabel">Edit to-do task</h5>
						</div>
						
						<div class="modal-body">
							<c:if test="${not empty error}">
								<c:if test="${not empty error['ERROR IN MODAL EDIT TO-DO TASK']}">
				    				<div>
				    					<label>${error['ERROR IN MODAL EDIT TO-DO TASK']}</label>
				   					</div>
				    			</c:if>
				    		</c:if>
								<form:form action="/WebAppEnsolver/ToDoTask/EditToDoTask" method="POST" modelAttribute="toDoTask" id="" class="">
							<div>	
								<input type="hidden" name="idToDoTaskToEdit" id="editToDoTaskModalId"/>
								 <p>You are about to change this to-do:</p>
								<!--  <div>
									<label>Title: </label><label id="editToDoTaskModalName"  >{{toDoDetails.title}}</label><br>
									<label>Description: </label><label id="editToDoTAskModalDescription">{{toDoDetails.description}}</label><br>
									<label>Due date: </label><label id="editToDoTAskModalDescription">{{toDoDetails.dueDate.dayOfMonth}}/{{toDoDetails.dueDate.monthValue}}/{{toDoDetails.dueDate.year}}</label><br>
									<label class="checkbox-inline"><input ng-model="toDoDetails.done" class="check" type="checkbox" name="doneEdit" disabled/>Done</label>
									
								</div> -->
								<input type="hidden" name="editToDoTaskModalNameOldInput" id="editToDoTaskModalNameOldInput"/>
								<input type="hidden" name="editToDoTaskModalDescriptionOldInput" id="editToDoTaskModalDescriptionOldInput"/>
								<div>
									<div class="form-group">
										<label class="control-label col-sm-3">Title: </label>
										<div class="col-sm-8">
											<form:input ng-model="toDoDetailsEdit.title" type="text" class="form-control" name="editToDoTaskModalNameNewInput" id="editToDoTaskModalNameNewInput" required="true" minlength="2" path="title"/>
											<br>
   										</div> 
									</div>
									
									 <div class="form-group">
   										<form:label class="control-label col-sm-3" path="dueDate">Due date (dd/mm/aa): </form:label>
   										<div class="col-sm-8">
    										<form:input pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])[- /.](0[1-9]|1[012])[- /.][0-9]{2}" class="form-control"  path="dueDate" id="dueDate" placeholder="Due date of to-do:" ng-model="dateOfTask" required="true"/>
    										
    										<br>
   										</div> 
  									</div>
									
									<div class="form-group">
										<label class="control-label col-sm-3">Description: </label>
										<div class="col-sm-8">
											<form:textarea type="textarea" ng-model="toDoDetailsEdit.description" class="form-control" name="editToDoTaskModalDescriptionNewInput" id="editToDoTaskModalDescriptionNewInput" required="true" minlength="5" maxlength="500" path="description"/>
											<br>
   										</div> 
									</div>
									
									<div class="form-group checkbox-position">
										<label class="checkbox-inline"><form:checkbox ng-model="toDoDetailsEdit.done" class="check"  name="doneEdit" path="done"/>Done</label>
									</div>
									
								</div>
								<form:hidden path="idToDoTask" ng-model="toDoDetailsEdit.idToDoTask" value="{{toDoDetailsEdit.idToDoTask}}"/>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							<button type="submit" class="btn btn-default"> Save </button> 
							
									
						</div>
							</form:form>
						
							
					</div>
				</div>
			</div>
			
			<!-- DELETE TO-DO TASK -->
			<div class="modal fade" id="deleteToDoTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header headers blackHeaderModalStyle">
							<h5 class="modal-title" id="exampleModalLabel">Delete to-do task</h5>
							
						</div>
						<div class="modal-body">
							<div>	
								<form:form action="/WebAppEnsolver/ToDoTask/DeleteToDoTask" method="POST" modelAttribute="toDoTask" id="" class="">
								<input type="hidden" name="idToDoTaskToDelete" id="removeTfoDoTaskModalId"/>
								<p>You are about to delete this to-do:</p>
								<label id="removeToDoTaskModalName"></label><br>
								<label id="removeToDoTaskModalDueDate"></label><br>
								<label id="removeToDoTaskModalDescription"> </label><br>
								
								<label id="removeToDoTaskStatus"></label><br>
								
								<form:hidden id="removeToDoTaskModalId" path="idToDoTask"/>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							<button type="submit" class="btn btn-default"> Delete  </button> 
						</div>
							</form:form>
					</div>
				</div>
			</div>
			
			<!-- SWITCH TO-DO TASK -->
			<div class="modal fade" id="switchStatusToDoTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header headers blackHeaderModalStyle">
							<h5 class="modal-title" id="exampleModalLabel">Switch to-do Task</h5>
						</div>
						<div class="modal-body">
							<div>	
								<form:form action="/WebAppEnsolver/ToDoTask/SwitchToDoTask" method="POST" modelAttribute="toDoTask" id="" class="">
								<input type="hidden" name="idToDoTaskToSwitch" id="SwitchToDoTaskModalId"/>
								<p>You are about to change the status of this to-do:</p>
								<!-- <label id="switchToDoTaskModalName"></label><br>
								<label id="switchToDoTaskModalDueDate"></label><br>
								<label id="switchToDoTaskModalDescription"></label><br>
								<label id="switchToDoTaskStatus"></label><br> -->
								
								
								<form:hidden id="statusModal" path="done"/>
								<form:hidden id="idToDoTaskToSwitch" path="idToDoTask"/>
			
			
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" onclick="restoreStatus()" >Cancel</button>
								<button type="submit" class="btn btn-default"> Confirm </button> 
								
							</div>
						</form:form>	
					</div>
				</div>
			</div>
			</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</jsp:attribute>


	



	



<jsp:attribute name="scriptsForShowModalWhenHaveErrors">
	<c:if test="${not empty error}">
		<c:if test="${not empty error['ERROR NEW FOODDISH']}">
		<script type="text/javascript">
   	 		$(window).on('load',function(){
        		$('#modNewFoodDish').modal('show');
    		});
		</script> 
		</c:if>
	</c:if>



	<c:if test="${not empty error}">
		<c:if test="${not empty error['ERROR IN MODAL CHANGE RESPONSIBLES']}">
		<script type="text/javascript">
   	 		$(window).on('load',function(){
        		$('#modResponsibles').modal('show');
    		});
		</script> 
		</c:if>
	</c:if>
	
	
	<c:if test="${not empty error}">
		<c:if test="${not empty error['ERROR IN MODAL CHANGE FOODDISH']}">
		<script type="text/javascript">
   	 		$(window).on('load',function(){
   	 			//element=document.getElementById("changeFoodDish"+${pageContext.request.getParameter("idFoodDish")});
   	 			element=document.getElementById("changeFoodDish"+${error['idFoodDish']});
   	 			moveDataToModalConfirmationChangeFoodDish(element);
        		/* $('#changeFoodDish').modal('show'); */
   	 		});
		</script> 
		</c:if>
	</c:if>
	
	<c:if test="${not empty error}">
		<c:if test="${not empty error['ERROR IN MODAL SHARE FOODDISH']}">
		<script type="text/javascript">
   	 		$(window).on('load',function(){
   	 			//element=document.getElementById("changeFoodDish"+${pageContext.request.getParameter("idFoodDish")});
   	 			element=document.getElementById("shareFoodDish"+${error['idFoodDish']});
   	 			moveDataToModalConfirmationShareFoodDish(element);
        		/* $('#changeFoodDish').modal('show'); */
   	 		});
		</script> 
		</c:if>
	</c:if>
	

</jsp:attribute>
</mt:base>