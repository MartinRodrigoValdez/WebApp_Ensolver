
			function moveDataToModalConfirmationDeleteToDoTask(element) {
				element.id;
				var id=element.id;
				id=id.replace("removeToDoTask","");
				var title =document.getElementById("ToDoTask"+id+"title").innerHTML;
				/* var description= document.getElementById("ToDoTask"+id+"description").innerHTML; */
				$('#deleteToDoTask').modal('show'); 
				document.getElementById("removeToDoTaskModalName").innerHTML=title;
				/* document.getElementById("removeToDoTaskModalDescription").innerHTML=description; */
				document.getElementById("removeToDoTaskModalId").value=id;
			}
	
			
	