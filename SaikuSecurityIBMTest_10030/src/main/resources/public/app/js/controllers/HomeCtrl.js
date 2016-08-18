/**
 * @author Rashmi
 * @date Jan'04 2016
 *
 */

app.controller('HomeCtrl', ['Facebook','$modal','$log', '$scope', '$http', '$location', '$cookieStore', 'RestURL','authFactory','$rootScope',
    function (Facebook,$modal, $log, $scope, $http, $location, $cookieStore, RestURL,authFactory,$rootScope) {

        var self = $scope;

        self.user = {};
        self.init = function () {
            $log.log('Initializing Home controller!');
           // $cookieStore.remove('back');
            $http.get('http://localhost:8080/SaikuSecurityIBMTest_10030/user/getUserDetail')
            .success(function(data){
            	console.log('data form usetr Logged info ',angular.toJson(data));
            	$rootScope.userData= data;//angular.toJson(data);
            	$rootScope.duplic($rootScope.userData);
            	console.log('data of $rootScope ',$rootScope.userData);
            }).error(function(data){
            	console.log('data of error page',angular.toJson(data));
            });
        };
        self.init();

    }]);
