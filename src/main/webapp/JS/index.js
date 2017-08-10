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
				data=true;
				$scope.url="dashboard.html";
				console.log($scope.url);
				if(data==true)
					{
						var myEl = angular.element( document.querySelector( '#loginform' ) );
						myEl.remove();
					}
			});
		}
	});

app.controller("main-controller",function($scope){
	$scope.url="HTML/dashboard.html ";
});