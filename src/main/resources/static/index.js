(function () {
    angular
        .module('msstandart', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/catalog', {
                templateUrl: 'catalog/catalog.html',
                controller: 'catalogController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/info', {
                templateUrl: 'info/info.html',
                controller: 'infoController'
            })
            .when('/order-create', {
                templateUrl: 'order-create/order-create.html',
                controller: 'orderCreateController'
            })
            .when('/order-view', {
                templateUrl: 'order-view/order-view.html',
                controller: 'orderViewController'
            })
            .when('/order-view-print', {
                templateUrl: 'order-view/order-view-print.html',
                controller: 'orderViewPrintController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }



    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webUser.token;
        }
    }
})();


angular.module('msstandart').controller('indexController', function ($scope, $rootScope, $http, $location, $localStorage) {



    if ($localStorage.webUser) {
        try {
            let jwt = $localStorage.webUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                alert("Token is expired!!!");
                delete $localStorage.webUser;
                $http.defaults.headers.common.Authorization = '';
                $location.path('/');
            }
        } catch (e) {
        }
    }

    if ($localStorage.webUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webUser.token;
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8080/auth', $scope.user)
            .then(function successCallback(response) {
                console.log(response);
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webUser = {token: response.data.token};
                    let jwt = $localStorage.webUser.token;
                    let payload = JSON.parse(atob(jwt.split('.')[1]));
                    $localStorage.username = payload.sub;
                    $localStorage.userRoles = payload.roles;
                    // console.log("role = " + $localStorage.userRoles);

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                    updateDropDown()
                }
            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.webUser;
        $localStorage.username = null;
        $localStorage.userRoles = null;
        $http.defaults.headers.common.Authorization = '';
    };


    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isUserRoles = function (role) {
        let isRole = false;
        if($localStorage.webUser) {
            $localStorage.userRoles.forEach(function (entry) {
                if (role === entry) {
                    isRole = true;
                }
            });
        }
        return isRole;
    };

    function updateDropDown() {
        if ($localStorage.webUser) {
            $scope.username = $localStorage.username;
        }
    }

    updateDropDown()
});