/**
 *
 * @author Geppetto Generated Code</br>
 * Date Created: </br>
 * @since  </br>
   build:   </p>
 *
 * code was generated by the Geppetto System </br>
 * Geppetto system Copyright - NewPortBay LLC </br>
 * The generated code is free to use by anyone</p>
 *
 *
 *
*/

var app = angular.module('SaikuSecurityIBMTest', ['ngRoute','ui.bootstrap','facebook','ngCookies','rzModule']);

   // configure our routes
   app.config(['$routeProvider','FacebookProvider','$httpProvider',function($routeProvider,FacebookProvider,$httpProvider) {
	   $httpProvider.defaults.withCredentials = true;
       $routeProvider
            .when('/login', {
                         templateUrl : 'app/views/en/login-en.html',
          controller: 'LoginCtrl'
                     })

            .when('/login-success', {
                         controller: 'LoginCtrlSuccess'
                         ,template: ' '
                     })

            .when('/landing_page-en', {
                         templateUrl : 'app/views/en/landing_page-en.html'
                     })

            .when('/app_level', {
                         controller: 'AplicationLevelCtrl', cache: false
                         ,template: ' '
                     })
            .when('/home', {
                 		templateUrl : 'app/views/en/home.html',
                		controller : 'HomeCtrl',
                		allow:'ROLE_ADMIN,ROLE_USER'
                	})
            	.when('/report', {
                		templateUrl : 'app/views/en/embed/test.html',
                		controller : 'EmbedCtrl',
                		allow:'ROLE_ADMIN,ROLE_USER'
                		//controller : 'EmbedCtrlDhina'
                	})
                	/*.when('/forReport', {
                		templateUrl : 'app/views/en/forReport.html',
                		controller : 'forReportCtrl',
                	})*/
                	.when('/admin', {
                		templateUrl : 'app/views/en/admin.html',
                		allow:'ROLE_ADMIN'
                	})
                	.when('/empty', {
                		templateUrl : 'app/views/en/empty.html',
                		controller : 'Admincrtl',
                		allow : 'ROLE_ADMIN'
                			
                	}).when('/error', {
                		templateUrl : 'app/views/en/error.html',
                	}).when('/logout',{
                	templateUrl : 'app/views/en/login-en.html',
                        controller: 'LogoutCtrl'
                	}).when('/accessDenied', {
                		templateUrl : 'app/views/en/accessDenied.html',
                	})

          .otherwise('app_level');

      FacebookProvider.init('1619632164961533');
   }]).run([ "$log","$rootScope", "$location", function($log,$rootScope, $location) {
	   $rootScope.userData = {};
   	$rootScope.i = 0;
   	$rootScope.isLogIn = false;
   	
   	
   	$rootScope.serverRoleValidation = function(toStaterolesAllowList){
   		var checkO = angular.equals({}, $rootScope.userData);
   		console.log('rootscope ddat after close dtra',angular.toJson($rootScope.userData));
   		console.log('checkO data=>',angular.toJson(checkO));
   	/*if($rootScope.isLogIn){*/
   		if(!checkO){
   			console.log('toStaterolesAllowList data=>',angular.toJson(toStaterolesAllowList));
   		//var uirole = toStaterolesAllowList[$rootScope.i];
   		var frontRole = toStaterolesAllowList;
   		console.log('frontRole dsata',angular.toJson(frontRole));
   		// If the current UI side has role but server side doesnt have role
			// this gonna be
			// not executed
   		if(!angular.equals({}, $rootScope.userData)){
   		var roleListOfCurrentUser = $rootScope.userData.authorities[$rootScope.i].authority; 
   		//console.log('roleListOfCurrentUser dsata',angular.toJson(roleListOfCurrentUser));
   		console.log('rolesList=>',angular.toJson(roleListOfCurrentUser));
   		$rootScope.adimnbutton=true;
   	 if((roleListOfCurrentUser == 'ROLE_USER')){
	    	console.log(".roleListOfCurrentUser-->--",$rootScope.roleListOfCurrentUser)
	    	$rootScope.adimnbutton=false;
	    }
   		
   		for(var uiRole= 0; uiRole< frontRole.length; uiRole++){
   			console.log('frontRole=>',angular.toJson(frontRole));
   			var testVar =frontRole.indexOf(roleListOfCurrentUser) !== -1;// _.contains(roleListOfCurrentUser,frontRole);
   			//console.log('testVar=>',angular.toJson(testVar));
   			if(testVar){
   				console.log('Is Present');
   				return true;
   			}
   		}
   	}
   		return false;
   }
   		else{
   	return true;
   }		
  };
	   
  $rootScope.$on('$routeChangeStart', function (event,next, current) {
	  
	  console.log($location.host());
      console.log('Next-- hasRole',angular.toJson(next.hasRole));
      console.log('Next-- url',angular.toJson(next.allow));
      var currentUrl = $location.path();
      console.log('currentUrl---------------------',angular.toJson(currentUrl));
      if(currentUrl != "/accessDenied	" && next.allow){
            if (!$rootScope.serverRoleValidation(next.allow)) {
              event.preventDefault();
              //$state.go('error');
              $location.url('/accessDenied');
            }
     }
  });
 }]);

