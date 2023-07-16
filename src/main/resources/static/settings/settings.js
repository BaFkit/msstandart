angular.module('msstandart').controller('settingsController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/api/v1/options';

    $scope.loadPrices = function () {
        $http.get(contextPath + '/prices')
            .then(function (response) {
                $scope.prices = response.data;
            }, function errorCallback(response) {
                alert('Ошибка в загрузке цены');
            });
    }

    $scope.changePrices = function () {
        $http.put(contextPath + '/prices', $scope.prices)
            .then(function(response) {
                $scope.loadPrices();
            }, function errorCallback(response) {
                alert('Ошибка в смене цены');
            });
    }

    $scope.loadPrices();
});