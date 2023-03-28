angular.module('msstandart').controller('ordersViewController', function ($localStorage, $scope, $http) {
    const contextPath = 'http://localhost:8080/api/v1/orders/';

    $scope.loadOrder = function () {
        $http.get(contextPath + $localStorage.orderId)
            .then(function (response) {
                $scope.order = response.data
                console.log($scope.order)
            });
    }

    $scope.loadOrder();
});