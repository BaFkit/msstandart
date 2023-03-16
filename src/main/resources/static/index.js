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
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webUser.token;
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8080/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.webUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webUser) {
            return true;
        } else {
            return false;
        }
    };
});