angular.module('msstandart').controller('ordersController', function ($location, $localStorage, $scope, $http) {
    const contextPath = 'http://localhost:8080/api/v1/orders';

    $scope.loadOrders = function (pageIndex = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.OrdersPage = response.data
                $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.OrdersPage.totalPages);
            });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.goToOrder = function (id) {
        $localStorage.orderId = id;
        $location.path('/order-view')
    }


    $scope.loadOrders();
});