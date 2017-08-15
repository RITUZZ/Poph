var app = angular.module('db', []);


app.controller('SubmitController',function($scope,$http,$rootScope){
	$scope.username="USERNAME";
		$scope.submit=function(){
			
			console.log("submit");
			
			var loginDetails = {
					username:$scope.username,
					password:$scope.password
				};
			$http({
			  url: 'Login',
			  method: 'POST',
			  data:loginDetails,
			  datatype:'JSON'
			})
			.then(function success(data){
				
				console.log("Success");
				data=data.data
				if(data.status==true){
					$scope.View.url="HTML/dashboard.html";
					console.log("inside if")
				}
				else
					alert("Wrong Username or Password");
				console.log($scope.View.url);
			},function error(data){
				console.log("error");
			});
		}
	});


var deal,instruments,counterParty,endingPosition;

app.controller("main-controller",function($scope,$http){

	$scope.View={};
	$scope.View.subUrl="HTML/dash-tradeoverview.html";
	$scope.View.url="HTML/dashboard.html ";
	
	
	$scope.navoptions=function(obj){
		
		var temp = angular.element(".dash-nav>.navbar-nav>li").removeClass('active');
		var type = angular.element(obj.target.parentNode).attr('navtype');
		if(type=='1')
			{
				$scope.View.subUrl="HTML/dash-tradeoverview.html";
			}
		else if(type=='2')
			{
				$scope.View.subUrl="HTML/dash-tableview.html";
			}
		else if(type=='3')
		{
			$scope.View.subUrl="HTML/dash-options.html";
		}
		 $(obj.target.parentNode).addClass('active');
	};
	/*
	$http({
		  url: 'Tables/Deal?offset=0&limit=1500',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			deal=data.data;
		},function error(data){
			console.log("error");
		});
	
	$http({
		  url: 'Tables/Counterparty',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			counterParty=data.data;
		},function error(data){
			console.log("error");
		});
	
	
	$http({
		  url: 'Tables/Instruments',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			instruments=data.data;
		},function error(data){
			console.log("error");
		});
		
	$http({
		  url: 'Tables/EndingPosition',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			endingPosition=data.data;
		},function error(data){
			console.log("error");
		});
		*/
});