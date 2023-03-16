angular.module('msstandart').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders')
            .then(function (response) {
                $scope.Orders = response.data
            });
    }
    $scope.loadOrders();
});