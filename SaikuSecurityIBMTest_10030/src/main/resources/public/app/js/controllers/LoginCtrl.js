/**
 * @author Rashmi
 * @date Jan'04 2016
 *
 */

app.controller('LoginCtrl', ['Facebook','$modal','$log', '$scope', '$http', '$location', '$cookieStore', 'RestURL','authFactory',
    function (Facebook,$modal, $log, $scope, $http, $location, $cookieStore, RestURL,authFactory) {

        var self = $scope;

        self.user = {};
        self.init = function () {
            $log.log('Initializing login controller!');
            $cookieStore.remove('back');
        };

        $scope.chopen = true;
		/**
		 * Facebook Login implementation
		 */

        $scope.fbLogin=function(){
            Facebook.login(function(response) {
                if (response.status == 'connected') {
                	console.log("FB connected");
                	$scope.me(response.authResponse.accessToken);
                }else{
                	console.log("FB not connected");
                }
            },{
            	scope: 'email,user_website,user_photos',
            	   return_scopes: true,
            });
        }

        /**
         * fetch FB user email and name
         */
        $scope.me = function(accessToken) {
            Facebook.api('/me?fields=email,name', function(authResponse) {
           	$scope.fbuser = authResponse;
            console.debug("$scope.user.name"+angular.toJson($scope.fbuser));
            checkEmailPassword($scope.fbuser.email);
           });
        };

        /**
         * G+ login implementation
         */
        $scope.gplusLogin = function(callback)
			{
				 $scope.clientId = "24932703999-ve2oh92tsibhau5j0du6skunvi29g7og.apps.googleusercontent.com";
				 gapi.auth.signIn({
		    	      'callback': function(authResult){
		    	    	  $scope.signinCallback(authResult, callback);
		    	      },
		    	      'clientid': $scope.clientId,
		    	      'cookiepolicy': 'single_host_origin',
		    	      'data-accesstype':'offline',
		    	      'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email',
		    	      'data-requestvisibleactions': 'http://schemas.google.com/AddActivity'
		    	    });
			}

			$scope.signinCallback= function(authResult, callback) {

				if (authResult['status']['signed_in']) {
					gapi.client.load('plus','v1', function(){
		                var request = gapi.client.plus.people.get({'userId' : 'me'});
		                request.execute(function(response) {
		                    var email = '';
						    if(response['emails'])
						    {
						        for(i = 0; i < response['emails'].length; i++)
						        {
						            if(response['emails'][i]['type'] == 'account')
						            {
						                email = response['emails'][i]['value'];
						            }
						        }
						    }
						    if($scope.chopen==true)
						    	checkEmailPassword(email);
		                    //send response to server
		                    //gotoServer(email,response.displayName,'gplus');
		                });
		            });

				} else if (authResult['error']) {
					console.log("G+ NOT CONNECTED");
				  }
			}

		/**
		 * This method checks if email address of signed in FB user exists or not,
		 * if not then it will ask for the same in a modal window.
		 */
		$scope.modalData ={};
		$scope.fb={};
		function checkEmailPassword(email){
			$scope.chopen=false;
			$scope.$apply(function() {
				if(email == null || email == undefined){
				    	var tempdata = {"name":name,"type":type};
				    	var modalInstance = $modal.open({
				            animation: true,
				            templateUrl: 'app/views/en/template/get_email.html',
				            controller: 'FBLoginModalCtrl',
				            resolve: {
				                data: function () {
				                  return angular.copy(tempdata); // deep copy
				                },
				              }
				        });

				    modalInstance.result.then(function (dataFromModal) {
				    	$scope.modalData = dataFromModal;
				    	$scope.fb.email = $scope.modalData.email;
						$scope.fb.password = $scope.modalData.password;
						gotoServer($scope.fb.email, $scope.fb.password);
				      }, function () {
				        $log.info('Modal dismissed at: ' + new Date());
				      });

				} else {
				   	var tempdata = {"name":name,"type":""};
			    	var modalInstance = $modal.open({
			            animation: true,
			            templateUrl: 'app/views/en/template/get_password.html',
			            controller: 'FBLoginModalCtrl',
			            resolve: {
			                data: function () {
			                  return angular.copy(tempdata); // deep copy
			                },
			              }
			        });

			    modalInstance.result.then(function (dataFromModal) {
			    	$scope.modalData = dataFromModal;
			    	$scope.fb.email = email;
					$scope.fb.password = $scope.modalData.password;
					//$scope.fb.type= $scope.modalData.type;
					gotoServer($scope.fb.email, $scope.fb.password);
			      }, function () {
			        $log.info('Modal dismissed at: ' + new Date());
			      });
				}
              });

		}

		$scope.socialUserDetail = {}; 
	    $scope.socialUserDetail = { 
			"username": "", 
			"password": "password", 
		};

		/**
	     * after social sign in go to server for spring security and creation of new user if not exist
	     */
	    function gotoServer(email, password) {
	    	console.log(email+password);
	    	$scope.socialUserDetail.username = email;
	    	$scope.socialUserDetail.password = password;
	    	
	      
	    	$http.post(RestURL.baseURL+'/user/createsocialuser',$scope.socialUserDetail)
	        .success(function (data) {
	          $log.log("Valid login: ", JSON.stringify(data));
	          
	            $location.url("/login-success");
	        }).error(function (error) {
	        	console.log("ERROR WITH SOCIAL SIGNIN"+JSON.stringify(error));
	      });
	    }

        self.init();

    }]);
