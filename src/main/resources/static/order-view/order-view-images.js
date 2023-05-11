angular.module('msstandart').controller('orderViewImagesController', function ($localStorage, $scope, $http) {
    const contextPath = 'http://localhost:8080/api/v1/orders/images/';

    $scope.loadImages  = function () {
        $http.get(contextPath + $localStorage.orderId)
            .then(function (response) {
                $scope.orderImages = response.data;
            }, function errorCallback(response) {
            });
    }

    $scope.loadImages();
});