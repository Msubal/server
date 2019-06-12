
	var scheduleApp = angular.module('scheduleApp',[]);
	
	scheduleApp.constroller("scheduleCtrl",function($scope, $http){
		
		$scope.initSchedule = function(num){
			$scope.num = num;
			$scope.refreshSchedule();
		};
		
		$scope.refreshSchedule = function(){
			$http.get('/adminSystem/buildings/schedules/'+$scope.num).then(
					function successCallback(response){
						$scope.cart = response.data;
					});
		};
		
		$scope.addSchedule = function(num){
			$scope.setCsrfToken();
			
			$http.put('adminSystem/buildings/schedules/'+num).then(
					function successCallback(){
						alert("건물추가 성공!");
					},function errorCallback(){
						alert("건물 추가 실패!!");
					});
		};
		
		$scope.removeSchedule = function(num){
			$scope.setCsrfToken();
			
			$http({
				method: 'DELETE',
				url:'/adminSystem/buildings/schedule/'+num
			}).then(function successCallback(){
				alert("삭제 완료!!");
			},function errorCallback(response){
				alert(response.date+"실패!!");
			});
		}
		
		$scope.setCsrfToken = function(){
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			
			$http.defaults.headers.common[csrfHeader] = csrfToken;
		};
		
	});