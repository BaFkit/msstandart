angular.module('msstandart').controller('orderCreateController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/api/v1/orders';

    $scope.tryCreateNewOrder = function () {
        $http.post(contextPath, $scope.newOrder)
            .then(function successCallback(response) {
                alert('Заказ создан ');
                $scope.newOrder = null;
                window.location = '/';
            }, function errorCallback(response) {
                console.log($scope.newOrder);
                alert('Ошибка в создании заказа');
            });
    }

    $scope.pressedButton = function () {
        if ($scope.isPressed) {
            $scope.isPressed = false;
        } else {
            $scope.isPressed = true;
        }
    };

    $scope.isPressedButton = function () {
        if ($scope.isPressed) {
            return true;
        } else {
            return false;
        }
    }

    $scope.isNotStandard = function () {
        if ($scope.newOrder.stoneFigure !== "Не стандарт") {
            return true;
        } else {
            return false;
        }
    }

    $scope.calculation = function () {
       return  $scope.newOrder.orderCost = ($scope.newOrder.stoneCost + $scope.newOrder.workCost + $scope.newOrder.installationCost);
    }
});