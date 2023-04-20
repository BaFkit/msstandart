angular.module('msstandart').controller('orderViewController', function ($localStorage, $scope,  $rootScope, $http) {
    const contextPath = 'http://localhost:8080/api/v1/orders/';

    $scope.loadOrder = function () {
        $http.get(contextPath + $localStorage.orderId)
            .then(function (response) {
                $scope.order = response.data
                console.log($scope.order)
            });
    }

    $scope.changeOrderStatus = function (status) {
        $http.get(contextPath + "status/change/" + $localStorage.orderId + "/" + status)
            .then(function (response) {
                window.location.reload();
            });
    }

    $scope.loadOrder();
});