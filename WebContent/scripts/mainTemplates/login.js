/**
 * Created by cruel on 7/29/2015.
 */

(function (){
    
	
	
	angular.module("restaurant").controller("LoginController",LoginControllerFn);
	
    LoginControllerFn.$inject = ["$http","$location","OwnerInfo","$interval"];
    
    function LoginControllerFn($http,$location,OwnerInfo,$interval){
        var loginVm = this;
        console.log("Hi this is Login");
        loginVm.authFail = false;
        loginVm.message = "";
        loginVm.submitForm = function(isFormValid){

            if(isFormValid){
                var formData = JSON.stringify(loginVm.user);
                $http({
                   method:"POST",
                    url:"api/ownerDetails/auth",
                    data:formData
                }).success(function(data){
                    if(data.status == "success"){
                    	OwnerInfo.owner = data.payload;
                    	console.dir(OwnerInfo.owner);
                        $location.path("/tab1");
                    }else if(data.status == "failure"){
                        loginVm.authFail = true;
                        loginVm.message = data.message;
                    }else{
                    	loginVm.authFail = true;
                        loginVm.message = data.message;
                    }
                }).error(function(error){
                    console.log(error);
                });
            }
        }
        
        $interval(function(){
        	loginVm.authFail = false;
       	 
        },20000);
        
    }
    
})();