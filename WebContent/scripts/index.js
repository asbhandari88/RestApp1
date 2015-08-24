/**
 * Created by cruel on 7/29/2015.
 */


(function(){
    angular
        .module('restaurant',['ngRoute','ngMessages','angularUtils.directives.dirPagination'])
        .config(moduleConfig);
    
    angular.module('restaurant').controller('IndexController',IndexControllerFn);
    
    
    function IndexControllerFn(){
        var indexVm = this;
        console.log("Hi this is index");
    }

    
    angular.module("restaurant").factory('OwnerInfo',OwnerInfoFactory);
    
    function OwnerInfoFactory(){
    	var self = {};
    	self.name="This is factory";
    	self.owner = {};
    	self.overlay = false;
    	self.template ="";
    	return self;
    }

    moduleConfig.$inject = ['$routeProvider'];

    function moduleConfig($routeProvider){
        $routeProvider
            .when("/login", {
                templateUrl :"./templates/mainTemplates/login.html",
                controller:"LoginController",
                controllerAs:"loginVm"
            })
            .when("/home", {
                templateUrl :"./templates/mainTemplates/home.html",
                controller:"HomeController",
                controllerAs:"homeVm"
            })
            .when("/makeReservation", {
                templateUrl :"./templates/mainTemplates/reservation.html",
                controller:"makeReservationController",
                controllerAs:"makeReservationVm"
            })
            .when("/editReservation", {
                templateUrl :"./templates/mainTemplates/reservation.html",
                controller:"editReservationController",
                controllerAs:"editReservationVm"
            })
            .when("/cancelReservation", {
                templateUrl :"./templates/mainTemplates/reservation.html",
                controller:"cancelReservationController",
                controllerAs:"cancelReservationVm"
            })
            .when("/ownerDashboard", {
                templateUrl :"./templates/mainTemplates/ownerTemplates/ownerDashboard.html",
                controller:"OwnerDashboardController",
                controllerAs:"ownerDashboardVm"
            })
            .when("/tab1", {
                templateUrl :"./templates/mainTemplates/ownerTemplates/ownerDashboard.html",
                controller:"OwnerProfileController",
                controllerAs:"ownerProfileVm"
            })
            .when("/tab2", {
                templateUrl :"./templates/mainTemplates/ownerTemplates/ownerDashboard.html",
                controller:"OwnerReservationController",
                controllerAs:"ownerReserveVm"
            })
            .when("/tab3", {
                templateUrl :"./templates/mainTemplates/ownerTemplates/ownerDashboard.html",
                controller:"OwnerSeatingController",
                controllerAs:"ownerSeatingVm"
            })
            .when("/tab4", {
                templateUrl :"./templates/mainTemplates/ownerTemplates/ownerDashboard.html",
                controller:"OwnerContactController",
                controllerAs:"ownerContactVm"
            })
            .otherwise({
                redirectTo:"/home"
            });
    }
})();
