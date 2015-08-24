/**
 * Created by cruel on 7/29/2015.
 */
(function (){
  

	angular.module("restaurant").controller("OwnerDashboardController",OwnerDashboardControllerFn);

    function OwnerDashboardControllerFn(){
        var ownerDashboardVm = this;
        ownerDashboardVm.select ="true";
        console.log("Hi this is Owner Dashboard");
    }

    angular.module("restaurant").controller("OwnerProfileController",OwnerProfileControllerFn);
    
    OwnerProfileControllerFn.$inject = ["OwnerInfo","$interval","$http"];
    function OwnerProfileControllerFn(OwnerInfo,$interval,$http){
        var ownerProfileVm = this;
        ownerProfileVm.select = "true";
        ownerProfileVm.user = OwnerInfo.owner;
        console.log(ownerProfileVm.user);
        
        
        ownerProfileVm.submitForm = function (isFormValid){
        	
        	
        	 if(isFormValid){
                 var formData = JSON.stringify(ownerProfileVm.user);
                 $http({
                    method:"POST",
                     url:"api/ownerDetails/updateProfile",
                     data:formData
                 }).success(function(data){
                     if(data.status == "success"){
                    	 ownerProfileVm.status = true;
                    	 ownerProfileVm.message = data.message;
                     	ownerProfileVm.user = data.payload;
                     }else if(data.status == "failure"){
                    	 ownerProfileVm.status = true;
                    	 ownerProfileVm.message = data.message;
                     }else{
                    	 ownerProfileVm.status = true;
                    	 ownerProfileVm.message = data.message;
                     }
                 }).error(function(error){
                     console.log(error);
                 });
                 
                 $interval(function(){
                	 ownerProfileVm.status = false;
                	 
                 },20000);
             }
        }
        
        ownerProfileVm.passWordUpdate = function (isFormValid){
        	
        	
       	 if(isFormValid){
       		 
       		 if(ownerProfileVm.user.oldPassword != ownerProfileVm.user.password){
       			ownerProfileVm.status2 =true;
       			ownerProfileVm.message2= "Old password does not match";
       		 }else if(ownerProfileVm.user.password == ownerProfileVm.user.newPassword){
       			ownerProfileVm.status2 =true;
       			ownerProfileVm.message2= "Old and new password are same";
       		 }else{
       			 
       			 var obj = {
	       				emp_id : ownerProfileVm.user.emp_id,
	       				password:ownerProfileVm.user.newPassword
       				}
       			 
       			 var formData = JSON.stringify(obj);
       			 
       			 
       			$http({
                    method:"POST",
                     url:"api/ownerDetails/changePassword",
                     data:formData
                 }).success(function(data){
                     if(data.status == "success"){
                    	 ownerProfileVm.status2 = true;
                    	 ownerProfileVm.message2 = data.message;
                     	ownerProfileVm.user.password = ownerProfileVm.user.newPassword;
                     }else if(data.status == "failure"){
                    	 ownerProfileVm.status2 = true;
                    	 ownerProfileVm.message2 = data.message;
                     }else{
                    	 ownerProfileVm.status2 = true;
                    	 ownerProfileVm.message2 = data.message;
                     }
                 }).error(function(error){
                     console.log(error);
                 });
                 
       		 }
       		 
       		 $interval(function(){
            	 ownerProfileVm.status2 = false;
            	 
             },20000);
       		 
                
            }
       }
    }

    angular.module("restaurant").controller("OwnerReservationController",OwnerReservationControllerFn);

    
    OwnerReservationControllerFn.$inject = ["$http","$interval","OwnerInfo","$location"];
    
    function OwnerReservationControllerFn($http,$interval,OwnerInfo,$location){
        var ownerReserveVm = this;
        ownerReserveVm.select = "true";
        console.log("Hi this is Owner Reservation");
        
                $http({
                   method:"GET",
                    url:"api/reservation/getAllReservation"
                }).success(function(data){
                    if(data.status == "success"){
                    	ownerReserveVm.data = JSON.parse(data.payload);                        
                       
                    }else if(data.status == "failure"){
                    }else{

                    }
                }).error(function(error){
                    console.log(error);
                });
        		
                ownerReserveVm.details = {};
                
                ownerReserveVm.getReservation = function(reserve_id,email){
                	
                	var cnfCode = "CNF"+reserve_id;
                	 ownerReserveVm.details.createdBy = "owner";
                	 
                	$http({
                        method:"GET",
                        url:"api/reservation/editReservation/"+email+"/"+cnfCode
                     }).success(function(data){
                         if(data.status == "success"){
                        	 
                             var time = data.payload.time_from.split(":");
                             ownerReserveVm.details.date = new Date(data.payload.date);
                             ownerReserveVm.details.time = new Date(0,0,0,time[0],time[1],time[2]);
                             ownerReserveVm.details.size = data.payload.no_of_people;
                             ownerReserveVm.details.special_arrangement = data.payload.special_arrangement;
                             ownerReserveVm.details.email = data.payload.email;
                             ownerReserveVm.details.name = data.payload.name;
                             ownerReserveVm.details.phone = data.payload.phone;
                             ownerReserveVm.details.email = data.payload.email;
                             ownerReserveVm.details.reserve_id = data.payload.reserve_id;
                             ownerReserveVm.details.cnfCode = "CNF"+data.payload.reserve_id;
                             ownerReserveVm.overlay = true;
                             
                         }else if(data.status == "failure"){
                        	 ownerReserveVm.authfail = true;
                        	 ownerReserveVm.message = data.message;
                         }else{
                        	 ownerReserveVm.authfail = true;
                        	 ownerReserveVm.message = data.message;
                         }
                     }).error(function(error){
                         console.log(error);
                     });
                }
                
                
                
                ownerReserveVm.overlayClose = function(){
                	ownerReserveVm.overlay = false;
                	ownerReserveVm.authfail = false;
                }
                
                ownerReserveVm.editReservation = function(){
                	
                	
                	
                	var formData = JSON.stringify(ownerReserveVm.details);
           		 $http({
                        method:"POST",
                         url:"api/reservation/editNewReservation",
                         data:formData
                     }).success(function(data){
                         if(data.status == "success"){
                          ownerReserveVm.authFail = true;
                          ownerReserveVm.authFail2 = true;
                          ownerReserveVm.message = data.message;
                          ownerReserveVm.confirmationMessage = "Your new confirmation code :"+data.payload.cnfCode;
                         }else if(data.status == "failure"){
                        	 ownerReserveVm.authfail = true;
                        	 ownerReserveVm.message = data.message;
                         }else{
                        	 ownerReserveVm.authfail = true;
                        	 ownerReserveVm.message = data.message;
                         }
                     }).error(function(error){
                         console.log(error);
                     });
                }
                
                
                ownerReserveVm.cancelReservation = function(){
                		
                	var email = ownerReserveVm.details.email;
            		var cnfCode = ownerReserveVm.details.cnfCode;
            		
                    $http({
                       method:"GET",
                        url:"api/reservation/cancelReservation/"+email+"/"+cnfCode
                    }).success(function(data){
                        if(data.status == "success"){
                        	ownerReserveVm.authFail = true;
                        	ownerReserveVm.message = data.message;

                        }else if(data.status == "failure"){
                        	ownerReserveVm.authFail = true;
                        	ownerReserveVm.message = data.message;
                        }else{
                        	ownerReserveVm.authFail = true;
                        	ownerReserveVm.message = data.message;
                        }
                    }).error(function(error){
                        console.log(error);
                    });
                }
                
                
    }

    angular.module("restaurant").controller("OwnerSeatingController",OwnerSeatingControllerFn);

    
    OwnerSeatingControllerFn.$inject = ["$http","$interval"]
    function OwnerSeatingControllerFn($http,$interval){
        var ownerSeatingVm = this;
        ownerSeatingVm.select = "true";
        ownerSeatingVm.overlay = false;
        console.log("Hi this is Owner Seating");
        
        
        ownerSeatingVm.submitEntry = function(){
        	var date = ownerSeatingVm.entry.date;
        	var time = ownerSeatingVm.entry.time;
        	console.log(date,time);
        	$http({
                method:"POST",
                 url:"api/reservation/getSeatings",
                data : {"reserve_date":ownerSeatingVm.entry.date,"time_from":ownerSeatingVm.entry.time}
             }).success(function(data){
                 if(data.status == "success"){
                	 ownerSeatingVm.data = JSON.parse(data.payload);                        
                 }else if(data.status == "failure"){
                	 
                 }else{

                 }
             }).error(function(error){
                 console.log(error);
             });
        	
        	
        }
        
        ownerSeatingVm.selectSeat = function(seat){
        	ownerSeatingVm.seat= {};
        	delete seat.$$hashKey;
        	ownerSeatingVm.overlay = true;
        	if(seat.reserve_id !=0){
        		
	        	seat.reserve_date = new Date(seat.reserve_date);
	        	seat.cnfCode = "CNF"+seat.reserve_id;
	        	var time = seat.time_from.split(":");
	        	seat.time_from = new Date(0,0,0,time[0],time[1],time[2]);
        	}
        	
        	seat.created_by = "owner";
        	ownerSeatingVm.seat = seat;
        }
        
        ownerSeatingVm.editReservation = function(){
        	
        	var formdata = JSON.stringify(ownerSeatingVm.seat);
        	$http({
                method:"POST",
                 url:"api/reservation/makeSeatings",
                data : formdata
             }).success(function(data){
            	  if(data.status == "success"){
                      ownerSeatingVm.authFail = true;
                      ownerSeatingVm.authFail2 = true;
                      ownerSeatingVm.message = data.message;
                      ownerSeatingVm.confirmationMessage = "Your new confirmation code :"+data.payload.cnfCode;
                     }else if(data.status == "failure"){
                    	 ownerSeatingVm.authfail = true;
                    	 ownerSeatingVm.message = data.message;
                     }else{
                    	 ownerSeatingVm.authfail = true;
                    	 ownerSeatingVm.message = data.message;
                     }
             }).error(function(error){
                 console.log(error);
             });
        	
        	
        }
        
       
        ownerSeatingVm.cancelReservation = function(){
    		
        	var email = ownerSeatingVm.seat.email;
    		var cnfCode = ownerSeatingVm.seat.cnfCode;
    		
            $http({
               method:"GET",
                url:"api/reservation/cancelReservation/"+email+"/"+cnfCode
            }).success(function(data){
                if(data.status == "success"){
                	ownerSeatingVm.authFail = true;
                	ownerSeatingVm.message = data.message;

                }else if(data.status == "failure"){
                	ownerSeatingVm.authFail = true;
                	ownerSeatingVm.message = data.message;
                }else{
                	ownerSeatingVm.authFail = true;
                	ownerSeatingVm.message = data.message;
                }
            }).error(function(error){
                console.log(error);
            });
        }
        
        
        ownerSeatingVm.overlayClose = function(){
        	ownerSeatingVm.overlay = false;
        	ownerSeatingVm.authFail = false;
            ownerSeatingVm.authFail2 = false;
            ownerSeatingVm.seat = {};
        }
        
        
    }

    
    angular.module("restaurant").controller("OwnerContactController",OwnerContactControllerFn);

    OwnerContactControllerFn.$inject = ["$http"];
    
    function OwnerContactControllerFn($http){
        var ownerContactVm = this;
        ownerContactVm.select ="true";
        console.log("Hi this is Owner contact");
        
        $http({
            method:"GET",
             url:"api/reservation/getContacts"
         }).success(function(data){
             if(data.status == "success"){
             	
             	ownerContactVm.people = JSON.parse(data.payload);

             }else if(data.status == "failure"){
             	
            	 ownerContactVm.message = data.message;
             }else{
             	
            	 ownerContactVm.message = data.message;
             }
         }).error(function(error){
             console.log(error);
         });
    }
})();