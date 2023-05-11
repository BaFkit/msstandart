(function () {
    angular
        .module('msstandart', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
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
            .when('/order-view-images', {
                templateUrl: 'order-view/order-view-images.html',
                controller: 'orderViewImagesController'
            })
            .when('/personnel', {
                templateUrl: 'personnel/personnel.html',
                controller: 'personnelController'
            })
            .when('/settings', {
                templateUrl: 'settings/settings.html',
                controller: 'settingsController'
            })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
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

    const contextPath = 'http://localhost:8080/';

    if ($localStorage.webUser) {
        try {
            let jwt = $localStorage.webUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
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
        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webUser = {token: response.data.token};
                    let jwt = $localStorage.webUser.token;
                    let payload = JSON.parse(atob(jwt.split('.')[1]));
                    $localStorage.username = payload.sub;
                    $localStorage.userRoles = payload.roles;

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/home');
                    $rootScope.loadProfile();
                }
            }, function errorCallback(response) {
                alert(response.data.message);
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
        $localStorage.firstName = null;
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

    $scope.tryToCreatePost = function () {
        $scope.post.author = $scope.profile.firstName;
        $http.post(contextPath + 'api/v1/posts', $scope.post)
            .then(function successCallback(response) {
                $scope.post = null;
                $rootScope.loadPosts();
            },function errorCallback(response) {
                alert('Ошибка в создании сообщения');
            });
    }


    $rootScope.loadProfile = function () {
        if ($localStorage.webUser) {
            $http.get(contextPath + 'api/v1/users/profile')
                .then(function (response) {
                    $localStorage.profile = response.data;
                    $scope.profile = $localStorage.profile
                });
        }
    }

    $rootScope.loadProfile()
});