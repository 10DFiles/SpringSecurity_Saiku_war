app.controller("MenuCtrl", [ '$scope', '$location', '$window','$log',
                    function($scope, $location, $window,$log) {
    $scope.handle_url = function (url_name){
        var language = $window.navigator.language || $window.navigator.userLanguage;
        language_part1 = language.split("-")[0];
        $location.path(url_name + '-' + language_part1);
    }
    

	$log.log("Inside menu controller");
    $scope.report = function (){
    	$location.url('/report')
    }
    
    $scope.admin = function (){
    	$log.log("ADMIN");
    	$scope.loading = true;
    	$http({
			url:"http://localhost:8080/saiku/rest/saiku/session/",
			method:"POST",
			data :$("#registerSubmit").serialize(),
			headers : {
				"content-type":"application/x-www-form-urlencoded",
					'Accept':'text/plain, */*; q=0.01'
			}
		}).success (function(data) {
			$scope.loading = false;
			window.open("http://localhost:8080/");
					//$location.url('/en-US/customer');
			}).error(function(data) {
				$log.log("Internal Server Error", angular.toJson( data));
		});
    };
    
}]);
