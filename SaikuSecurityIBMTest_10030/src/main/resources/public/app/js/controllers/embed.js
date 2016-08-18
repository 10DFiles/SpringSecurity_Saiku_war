app.controller("EmbedCtrl", [ '$scope', '$location', '$window','$log','$rootScope','$timeout',
                    function($scope, $location, $window,$log,$rootScope,$timeout) {
	
	console.log("---in embed");
	
	$scope.selectedAdmin= function(val){
		console.log("val-selectedAdmin--",val);
		console.log($scope.reportsAdminList[val],"saiku"+val );
		$rootScope.displayChart($scope.reportsAdminList[val],"saiku"+val);
		
	}
	
	$scope.selectedUser= function(val){
		console.log("val-selectedUser--",val);
		console.log($scope.reportsUserList[val],"saiku"+val );
		$rootScope.displayChart($scope.reportsUserList[val],"saiku"+val);
	}
	console.log("in embed controller-----",$rootScope.userData);
	
	$scope.init = function(){
		
		console.log("in embed controller-----",$rootScope.userData.authorities[0].authority);
	if($rootScope.userData.authorities[0].authority == "ROLE_ADMIN"){
		//if("ROLE_ADMIN"=="ROLE_ADMIN"){
			$scope.showDropdownadmin = true;
			//$scope.showDropdown = true;
			console.log("if loop dhina ")
	$rootScope.myClient = new SaikuClient({
	    server:"http://localhost:8080/saiku",
	    path: "/rest/saiku/embed",
	    user: "admin",
	    password: "admin"
	});
	}else if ($rootScope.userData.authorities[0].authority == "ROLE_USER"){
			console.log("-----------------");
			$scope.showDropdown = true;
			console.log("if else part ")
	$rootScope.myClient = new SaikuClient({
	    server:"http://localhost:8080/saiku",
	    path: "/rest/saiku/embed",
	    user: "admin",
	    password: "admin"
	});
		}

		$rootScope.displayChart= function (path, htmlDiv){
			console.log("--------------in $rootScope.displayChart---method")
		$rootScope.myClient.execute({
			file: path,
			htmlObject: "#"+htmlDiv,
			render: "chart",
			mode: "line",
			chartDefinition: {
					width: 560,
					colors: ['grey','red','blue'],
					extensionPoints: {
							legend: true,
							legendShape: 'circle',
							legendSize: {width: '100%'},
							legendLabel_textStyle: "#990000",
							legendFont: 'normal 11px "Open Sans"'
					}
			},
			zoom: true
		});
	}
		
		
	$(document).ready(function(){
		console.log("Hello world")
		
	});
	
$scope. listOfReports = [];
$scope. reportsAdminList = [];
$scope. reportsUserList = [];
$scope. adminList = [];
$scope. finalAdminList = [];
$scope. finalSupervisorList = [];
$scope. supervisorList = [];

$scope.finalData=[];
		$.get( "http://localhost:8080/saiku/rest/saiku/api/repository?type=saiku,sdb", function( data ) {
	//$.get("dhinaQuery.json",function(data){
	//	$.get("userAlone.json",function(data){
		 	console.log(data);
			console.log(data.length);
		 	var response = (data);
		 	for(var i=0;i<data.length;i++){
		 		console.log("JSON ==>"+data[i].type);
				// get reports only under homes
		 		if(data[i].type=="FOLDER" && data[i].name=="homes"){
				console.log("data[i].repoObjects ==>"+data[i].repoObjects)
		 			// for file only we generate the saiku reports
					if(data[i].repoObjects && data[i].repoObjects.length>0){
					
						console.log("i am inside"+data[i].repoObjects.length)
						var arr = [];
						arr = (data[i].repoObjects);
						var v=0;
						for (var k=0;k<arr.length;k++){
							console.log("repo name ==>"+arr[0].name);
							console.log("repo name ==>"+arr[1].name);
							// && "ROLE_USER"=="ROLE_ADMIN"
							if(arr[k].name=="home:admin"){
								// retrieve all reports under home:admin folder
								console.log("repo  Objects==>"+k+'-----v---'+v);
								for(var z=0;z<arr[k].repoObjects.length;z++){
									// this is now the list of files and folders under homes/home:admin
									if(arr[k].repoObjects[z]){
										var folderObjects =new Array(arr[k].repoObjects[z].repoObjects);
										console.log("length---",arr[k].repoObjects[z].repoObjects.length)
										for(var x=0;x<arr[k].repoObjects[z].repoObjects.length;x++){
											//console.log(x)
											if(arr[k].repoObjects[z].repoObjects[x]){
												console.log("-----path--",arr[k].repoObjects[z].repoObjects[x].path)
											//	$timeout(function() {
												//$scope.adminList.push(arr[k].repoObjects[z].repoObjects[x].path);
												$scope.reportsAdminList.push(arr[k].repoObjects[z].repoObjects[x].path)
												var name = arr[k].repoObjects[z].repoObjects[x].path.split('/');
												$scope.adminList.push(name[name.length - 1].split('.')[0]);
												//$scope.showDropdownadmin = true;
												//displayChart(arr[k].repoObjects[z].repoObjects[x].path,"saiku"+x);
											//	}, 1000);
											}
										}
									}
										
								}						
								
								
							} //&& "ROLE_USER"=="ROLE_USER"
							else if (arr[k].name=="home:supervisor"){
								console.log("repo name ==>---"+k+"----"+arr[k].name);
								// retrieve all reports under home:dhina folder
								for(var z=0;z<arr[k].repoObjects.length;z++){
									// this is now the list of files and folders under homes/home:dhina
									console.log("---,",angular.toJson(arr[k].repoObjects[z]));
									var name = arr[k].repoObjects[z].path.split('/')
									$scope.reportsUserList.push(arr[k].repoObjects[z].path)
						          //  console.log("dhina----",name[name.length - 1]);
						        	//console.log("dhina---[0]----",name[name.length - 1].split('.')[0]);
						        	$scope.supervisorList.push(name[name.length - 1].split('.')[0]);
									//$scope.supervisorList.push(arr[k].repoObjects[z].path);
									//$scope.showDropdown = true;
									
								}	
								/*$timeout(function() {
							        console.log('update with timeout fired')
							        console.log("------$scope.oitsude------------",$scope.supervisorList);
							        console.log("----",$scope.supervisorList.length)
							        for(var i=0;i<$scope.supervisorList.length;i++){
							        	var name = $scope.supervisorList[i].split('/')
							            console.log("name 3--",name[name.length - 1]);
							        	console.log("name[4].split('.')[0]----",name[name.length - 1].split('.')[0]);
							        	$scope.listOfReports.push(name[name.length - 1].split('.')[0]);
							        }
							    }, 3000);*/
							}
							console.log("------$scope.adminList----------",$scope.adminList);
							console.log("------$scope.supervisorList----------",$scope.supervisorList);
						console.log("kk avlue---",k);
						}
					}
					
		 			
		 		}
		 	}
		});
	
	$timeout(function() {
		angular.copy($scope.adminList, $scope. finalAdminList);
		angular.copy($scope.supervisorList, $scope. finalSupervisorList);
		
	},1000);
	
/*	$timeout(function() {
        console.log('update with timeout fired')
        console.log("------$scope.oitsude------------",$scope.reportsList);
        console.log("----",$scope.reportsList.length)
        for(var i=0;i<$scope.reportsList.length;i++){
        	var name = $scope.reportsList[i].split('/')
            console.log("name 3--",name[name.length - 1]);
        	console.log("name[4].split('.')[0]----",name[name.length - 1].split('.')[0]);
        	$scope.listOfReports.push(name[name.length - 1].split('.')[0]);
        }
    }, 3000);*/
};
$scope.init();
}]);
