var app=angular.module('app',[]);

function RemoteResource($http,$q,baseUrl) {
	
    this.list = function() {
        var defered = $q.defer();
        var promise = defered.promise;

};
} 


function RemoteResourceProvider() {
	var _baseUrl;
	this.setBaseUrl=function(baseUrl) {
		_baseUrl=baseUrl;
	};
	this.$get=['$http','$q',function($http,$q) {
		return new RemoteResource($http,$q,_baseUrl);
	}];
};
	 
app.provider("remoteResource",RemoteResourceProvider);

app.constant("baseUrl", "http://localhost:8080/WebAppEnsolver");
app.config(['baseUrl', 'remoteResourceProvider',function(baseUrl, remoteResourceProvider) {
	   remoteResourceProvider.setBaseUrl(baseUrl);
}
]);


app.controller('toDoDetailsController',['remoteResource','$scope','$http',function(remoteResource,$scope,$http){
	
	$scope.texto1="1";
	$scope.texto2="angularJS";
	$scope.toDoDetails= [];
	$scope.toDoDetailsEdit= [];
	$scope.idToDoTask=null;
	$scope.dateOfTask="";
	

	$scope.searchUsers = function() {
	        $http({method: 'GET', url: 'http://localhost:8080/WebAppEnsolver/ToDoTask/FindToDoTaskDetails/'+$scope.idToDoTask}).then(function(response) {
	          $scope.toDoDetails = response.data;
	          $scope.toDoDetailsEdit = response.data;
	          $scope.dateOfTask="";
	          if($scope.toDoDetailsEdit.dueDate.dayOfMonth<10){
	        	  $scope.dateOfTask="0";
	          }
	          $scope.dateOfTask=$scope.dateOfTask+$scope.toDoDetailsEdit.dueDate.dayOfMonth+"/";
	          if($scope.toDoDetailsEdit.dueDate.monthValue<10){
	        	  $scope.dateOfTask=$scope.dateOfTask+"0";
	          }
	          $scope.dateOfTask=$scope.dateOfTask+$scope.toDoDetailsEdit.dueDate.monthValue+"/"+((($scope.toDoDetailsEdit.dueDate.year)+"").slice(2));
	          
	          
	          
	          /*$scope.dateOfTask=$scope.toDoDetailsEdit.dueDate.dayOfMonth+"/"+$scope.toDoDetailsEdit.dueDate.monthValue+"/"+((($scope.toDoDetailsEdit.dueDate.year)+"").slice(2));*/
	        /*  alert( $scope.dateOfTask);*/
	        }, function(response) {
	        	$scope.toDoDetails = response.data || 'Request failed';
	      });
	};
	
	
	$scope.beginSearch=function(id){
		$scope.idToDoTask=id;
		$scope.searchUsers();
	}
	
	$scope.switchTask=function(id){
		alert("estamos");
	}
	
	
	
	
}])


