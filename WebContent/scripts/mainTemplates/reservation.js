/**
 * Created by cruel on 7/29/2015.
 */
(function (){
	
	angular.module("restaurant").controller("makeReservationController",makeReservationControllerFn);

	makeReservationControllerFn.$inject = ["$http","$interval"];
	
    function makeReservationControllerFn($http,$interval){
        var makeReservationVm = this;
        makeReservationVm.select ="true";
        console.log("Hi this is Make Reservation");
        console.dir(makeReservationVm);
        makeReservationVm.submitForm = function (isFormValid){
        makeReservationVm.reserve.createdBy = "user";
        
        	 if(isFormValid){

                 var formData = JSON.stringify(makeReservationVm.reserve);
                 $http({
                    method:"POST",
                     url:"api/reservation/makeReservation",
                     data:formData
                 }).success(function(data){
                     if(data.status == "success"){
                    	 makeReservationVm.authFail = false;
                    	 makeReservationVm.successMessage = data.message;
                    	 makeReservationVm.cnfCode = "Your confirmation code is : "+data.payload.cnfCode;
                    	 makeReservationVm.table_name = "Table reserved for you : "+data.payload.table_name;
                    	 
                     }else if(data.status == "failure"){
                    	 makeReservationVm.authFail = true;
                    	 makeReservationVm.failMessage = data.message;
                     }else{
                    	 makeReservationVm.authFail = true;
                    	 makeReservationVm.failMessage = data.message;
                     }
                 }).error(function(error){
                     console.log(error);
                 });
             }
        	 
        	 console.dir(makeReservationVm);
        	
        }
        
        
        
    }
    
    angular.module("restaurant").controller("editReservationController",editReservationControllerFn);
    
    editReservationControllerFn.$inject = ["$http","$interval"];

    function editReservationControllerFn($http,$interval){
        var editReservationVm = this;
        editReservationVm.select ="true";
        console.log("Hi this is edit Reservation");
        
        editReservationVm.editReserveDetails = true;
        editReservationVm.editReservePage = false;
        
        editReservationVm.submitForm = function(isFormValid){
        	editReservationVm.details.createdBy = "user";
        	
        	if(isFormValid){
        		
        		var email = editReservationVm.details.email;
        		var cnfCode = editReservationVm.details.cnfCode;
                $http({
                   method:"GET",
                    url:"api/reservation/editReservation/"+email+"/"+cnfCode
                }).success(function(data){
                    if(data.status == "success"){
                    	editReservationVm.editReserveDetails = false;
                        editReservationVm.editReservePage = true;
                        
                        var time = data.payload.time_from.split(":");
                        editReservationVm.details.date = new Date(data.payload.date);
                        editReservationVm.details.time = new Date(0,0,0,time[0],time[1],time[2]);
                        editReservationVm.details.size = data.payload.no_of_people;
                        editReservationVm.details.special_arrangement = data.payload.special_arrangement;
                   	 
                    }else if(data.status == "failure"){
                    	editReservationVm.authfail = true;
                    	editReservationVm.message = data.message;
                    }else{
                    	editReservationVm.authfail = true;
                    	editReservationVm.message = data.message;
                    }
                }).error(function(error){
                    console.log(error);
                });
        		
        		
        	}
        	
        	
        }
        
        
        editReservationVm.editsubmitForm = function(isFormValid){
        	
        	
        	if(isFormValid){
        		
        		var formData = JSON.stringify(editReservationVm.details);
        		 $http({
                     method:"POST",
                      url:"api/reservation/editNewReservation",
                      data:formData
                  }).success(function(data){
                      if(data.status == "success"){
                    	  editReservationVm.authfail2 = true;
                    	  editReservationVm.message2 = data.message;
                    	  editReservationVm.confimationMessage = "Your new confirmation code :"+data.payload.cnfCode;
                      }else if(data.status == "failure"){
                    	  editReservationVm.authfail3 = true;
                    	  editReservationVm.message3 = data.message;
                      }else{
                    	  editReservationVm.authfail3 = true;
                    	  editReservationVm.message3 = data.message;
                      }
                  }).error(function(error){
                      console.log(error);
                  });
        	}
        }
    }
    
    	
    
    angular.module("restaurant").controller("cancelReservationController",cancelReservationControllerFn);

    cancelReservationControllerFn.$inject = ["$http","$interval"];
    
    function cancelReservationControllerFn($http,$interval){
        var cancelReservationVm = this;
        cancelReservationVm.select ="true";
        console.log("Hi this is cancel Reservation");
    
    
    
    cancelReservationVm.submitForm = function(isFormValid){
    	
    	if(isFormValid){
    		
    		var email = cancelReservationVm.details.email;
    		var cnfCode = cancelReservationVm.details.cnfCode;
    		
            $http({
               method:"GET",
                url:"api/reservation/cancelReservation/"+email+"/"+cnfCode
            }).success(function(data){
                if(data.status == "success"){
                	cancelReservationVm.authfail = true;
                	cancelReservationVm.message = data.message;

                }else if(data.status == "failure"){
                	cancelReservationVm.authfail = true;
                	cancelReservationVm.message = data.message;
                }else{
                	cancelReservationVm.authfail = true;
                	cancelReservationVm.message = data.message;
                }
            }).error(function(error){
                console.log(error);
            });
    		
    		
    	}
    	
    	
    }
    
    }
})();