angular.module('msstandart').controller('orderViewPrintController', function ($localStorage, $scope) {
    const contextPath = 'http://localhost:8080/api/v1/orders/';

    $scope.order = $localStorage.order;
    $scope.orderPreview = $localStorage.orderPreview;

    $scope.printOrder = function () {
        window.print();
    }

});