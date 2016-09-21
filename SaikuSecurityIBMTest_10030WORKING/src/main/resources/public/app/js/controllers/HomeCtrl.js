/**
 * @author Rashmi
 * @date Jan'04 2016
 *
 */

app.controller('HomeCtrl', ['Facebook','$modal','$log', '$scope', '$http', '$location', '$cookieStore', 'RestURL','authFactory','$rootScope',
    function (Facebook,$modal, $log, $scope, $http, $location, $cookieStore, RestURL,authFactory,$rootScope) {

        var self = $scope;

        self.user = {};
       //var getuserDetails = "http://localhost:9090/SaikuSecurityIBMTest_10030WORKING/getDetails/getUserDetail";
      // var getadminDetails = "http://localhost:9090/SaikuSecurityIBMTest_10030WORKING/user/hello";
       // var getuserDetails = "http://52.88.1.3:8080/SaikuSecurityIBMTest_10030/user/getUserDetail";
      self.init = function () {
            $log.log('Initializing Home controller!');
            
            	/* $http.get(getadminDetails)
                 .success(function(data){
                	 console.log("data----user/hello---",angular.toJson(data));
                	 if(data != null){
                		 console.log('Inside the login data ')
                			 console.warn('PRINTING THE HOME HOME');
                	 }else{
                		 console.log('Object returning format is wrong ')
                	 }
                 }).error(function(data){
                	 //console.error('PRINTING THE HOME HOME');
                	 //$location.url('/accessDenied');
                	 if(data.responseDenied == 'accessDenied'){
            			 console.error('PRINTING  accessDenied');
            			 $location.url('/accessDenied');
            		 }	
            		 console.log(data)
            	 });*/
        };
        self.init();

    }]);
