app.controller("LogoutCtrl", [ '$scope', '$location', '$window','$log','$rootScope',
                    function($scope, $location, $window,$log,$rootScope) {

	$log.log("Inside logout controller");
    $scope.init = function (){
    	$rootScope.userData = "";
    	$location.url('/login')
    }
    $scope.init();
}]);
