app.controller("EmbedCtrlDhina", [ '$scope', '$location', '$window','$log','$rootScope','$timeout',
                    function($scope, $location, $window,$log,$rootScope,$timeout) {
	
	console.log("---in embed");
	//$scope. listOfReports = ["shanthi","sasi","dhina"];
	
	$scope.selected= function(val){
		console.log("val---",val);
		console.log($scope.reportsList[val],"saiku"+val );
		$rootScope.displayChart($scope.reportsList[val],"saiku"+val);
		
	}
	console.log("in embed controller-----",$rootScope.userData);
	
	$scope.init = function(){
		
		//console.log("in embed controller-----",$rootScope.userData.authorities[0].authority);
	//if($rootScope.userData.authorities[0].authority == "ROLE_ADMIN"){
		if("ROLE_ADMIN"=="ROLE_ADMIN"){
			console.log("if loop dhina ")
	$rootScope.myClient = new SaikuClient({
	    server:"http://localhost:8080/saiku",
	    path: "/rest/saiku/embed",
	    user: "admin",
	    password: "admin"
	});
		//}else if($rootScope.userData.authorities[0].authority =="ROLE_USER"){
		}else if("ROLE_USER" =="ROLE_USER"){
			$rootScope.myClient = new SaikuClient({
			    server:"http://localhost:8080/saiku",
			    path: "/rest/saiku/embed",
			    user: "user",
			    password: "Dhinakar1"
			});
		}else{
			console.log("-----------------in embed else part execuetd");
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
	$rootScope.myClient.execute({
		file: "Examples/pie.saiku",
		htmlObject: "#saiku",
		render: "table",
		params: {
			family: "testparameter"
		}
	});
	$rootScope.myClient.execute({
		file: "Examples/Heatgrid.saiku",
		htmlObject: "#saiku2",
		render: "chart",
		mode: "heatgrid",
		chartDefinition: {
			width: 900,
			colors: ['grey','red','blue'],
			extensionPoints: {
				xAxisLabel_textAngle: - Math.PI/3,
				panel_fillStyle: "#EAEAEA"
			}
		},
		zoom: true
	}); 
	
	
$scope. listOfReports = [];
$scope. reportsList = [];
$scope.finalData=[];
		//$.get( "http://localhost:8080/saiku/rest/saiku/api/repository?type=saiku,sdb", function( data ) {
	$.get("dhinaQuery.json",function(data){
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
						console.log("i am inside "+data[i].repoObjects.length)
						console.log("i am inside----- "+angular.toJson(data[i].repoObjects))
						var arr = [];
						arr = (data[i].repoObjects);
						console.log("-----arr length---",arr.length);
						for (var k=0;k<arr.length;k++){
							console.log("repo name ==>"+arr[k].name);
							if(arr[k].name=="home:dhina"){
								// retrieve all reports under home:admin folder
								console.log("repo ==>"+JSON.stringify(arr[k].repoObjects[0].repoObjects));
								// arr[k] is the home:admin folder. iterate all the files
								for(var z=0;z<arr[k].repoObjects.length;z++){
									// this is now the list of files and folders under homes/home:admin
									if(arr[k].repoObjects[z]){
										var folderObjects =new Array(arr[k].repoObjects[z].repoObjects);
										console.log(arr[k].repoObjects[z].repoObjects.length)
										for(var x=0;x<arr[k].repoObjects[z].repoObjects.length;x++){
											//console.log(x)
											if(arr[k].repoObjects[z].repoObjects[x]){
												console.log("-----",arr[k].repoObjects[z].repoObjects[x].path)
												$scope.reportsList.push(arr[k].repoObjects[z].repoObjects[x].path);
												//$scope.finalData.push(arr[k].repoObjects[z].repoObjects[x].path,"saiku"+x);
												//displayChart(arr[k].repoObjects[z].repoObjects[x].path,"saiku"+x);
											}
										//	console.log("------$scope.listOfReports----------",$scope.listOfReports);
										}
									}
										
								}						
								
								
							}else if(arr[k].name=="home:dhina"){
								// retrieve all reports under home:admin folder
								console.log("dhina k----",arr[k].name+"----k---"+k);
								//console.log("repo dhina==>"+JSON.stringify(arr[k].repoObjects[0]);
								// arr[k] is the home:admin folder. iterate all the files
								for(var z=0;z<arr[k].repoObjects.length;z++){
									// this is now the list of files and folders under homes/home:admin
									if(arr[k].repoObjects[z]){
										var folderObjects =new Array(arr[k].repoObjects[z].repoObjects);
										console.log(arr[k].repoObjects[z].repoObjects.length)
										for(var x=0;x<arr[k].repoObjects[z].repoObjects.length;x++){
											//console.log(x)
											if(arr[k].repoObjects[z].repoObjects[x]){
												console.log("--dhina---",arr[k].repoObjects[z].repoObjects[x].path)
												$scope.reportsList.push(arr[k].repoObjects[z].repoObjects[x].path);
												//$scope.finalData.push(arr[k].repoObjects[z].repoObjects[x].path,"saiku"+x);
												//displayChart(arr[k].repoObjects[z].repoObjects[x].path,"saiku"+x);
											}
										//	console.log("------$scope.listOfReports----------",$scope.listOfReports);
										}
									}
										
								}						
								
								
							}
							console.log("------$scope.listOfReports----------",$scope.reportsList);
						console.log("kkk----",k);
						}
					}
					
		 			
		 		}
		 	}
		});
	
	$timeout(function() {
        console.log('update with timeout fired')
        console.log("------$scope.oitsude------------",$scope.reportsList);
        console.log("----",$scope.reportsList.length)
        for(var i=0;i<$scope.reportsList.length;i++){
        	var name = $scope.reportsList[i].split('/')
            console.log("name 3--",name[4]);
        	console.log("name[4].split('.')[0]----",name[4].split('.')[0]);
        	$scope.listOfReports.push(name[4].split('.')[0]);
        }
    }, 1000);
};
$scope.init();
}]);
