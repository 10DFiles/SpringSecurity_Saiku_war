app.controller("LogoutCtrl", [ '$scope', '$location', '$window','$log',
                    function($scope, $location, $window,$log) {

	$log.log("Inside logout controller");
    $scope.init = function (){
    	$location.url('/login')
    }
    $scope.init();
}]);
