var app = angular.module('db', []);

app.controller('SubmitController',function($scope,$http,$rootScope){
	$scope.username="USERNAME";
		$scope.submit=function(){
			
			console.log("submit");
			
			var loginDetails = {
					username:$scope.username,
					password:$scope.password
				};
			var root = '';
			$http({
			  url: root + 'Login',
			  method: 'POST',
			  data:loginDetails,
			  datatype:'JSON'
			})
			.then(function success(data){
				
				console.log("Success");
				console.log(data);
				$scope.View.url="HTML/dashboard.html";
				console.log($scope.View.url);
				$scope.$applyAsync(function(){
					app.setIncludeValue("HTML/dashboard.html");
				});
						
			},function error(data){
				console.log("error");
			});
		}
	});

app.controller("main-controller",function($scope){
	$scope.View={};
	$scope.View.url="HTML/login.html ";
});