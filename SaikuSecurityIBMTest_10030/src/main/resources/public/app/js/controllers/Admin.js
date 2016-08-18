
app.controller("Admincrtl", [
		'$scope',
		'$rootScope',
		'$location',
		'$window',
		'$q',
		'$http',
		'$log',
		'$timeout',
		function($scope, $rootScope, $location, $window, $q, $http, $log,
				$timeout) {

			$log.log("Inside admin controller");

			$scope.init = function() {
				$scope.loading = true;
				$http({
					url : "http://localhost:8080/saiku/rest/saiku/session/",
					method : "POST",
					data : $("#registerSubmit").serialize(),
					headers : {
						"content-type" : "application/x-www-form-urlencoded",
						'Accept' : 'text/plain, */*; q=0.01'
					}
				}).success(function(data) {

					window.open("http://localhost:8080/");
					$scope.loading = false;
					$location.url('/admin');
				}).error(function(data) {
					$log.log("Internal Server Error", angular.toJson(data));
					$scope.loading = false;
					window.open("http://localhost:8080/");
					$location.url('/admin');
				});
			}

			$scope.init();

		} ]);
