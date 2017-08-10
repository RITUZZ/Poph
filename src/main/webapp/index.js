var app = angular.module('db', []);

app.controller('SubmitController',function($scope,$http){
		$scope.submit=function(){
			
			console.log("submit");
			
			var loginDetails = {
					username:$scope.username,
					password:$scope.password
				};
			var root = '';
			$http({
			  url: root + 'Login',
			  data: loginDetails,
			  dataType: 'json',
			  method: 'POST'
			})
			.then(function success(data){
				
				console.log("Success");
				data=true;
				
				if(data==true)
					{
						var myEl = angular.element( document.querySelector( '#loginform' ) );
						myEl.remove();
					}
			});
		}
	});
