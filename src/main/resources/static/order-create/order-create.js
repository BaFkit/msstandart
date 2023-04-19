angular.module('msstandart').controller('orderCreateController', function ($scope, $http, $localStorage) {
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

    $scope.isNotStandardFigure = function () {
        if ($scope.newOrder.stoneFigure !== "Не стандарт") {
            return true;
        } else {
            return false;
        }
    }
    $scope.isNotStandardSize = function () {
        if ($scope.newOrder.stoneSize !== "Не стандарт") {
            return true;
        } else {
            return false;
        }
    }


    $scope.calculationMonumentCost = function () {
        return $scope.newOrder.stoneCost =  $scope.newOrder.monumentCost +  $scope.newOrder.stoneKitCost;
    }
    $scope.calculationWorksOnMonumentCost = function () {
        return $scope.newOrder.workCost =
            $scope.newOrder.nameOnMonumentCost +
            $scope.newOrder.dateOnMonumentCost +
            $scope.newOrder.holesInStandCost +
            $scope.newOrder.portraitCost +
            $scope.newOrder.portraitFasteningCost +
            $scope.newOrder.tileCost +
            $scope.newOrder.tileFasteningCost +
            $scope.newOrder.crucifixCost +
            $scope.newOrder.flowersCost +
            $scope.newOrder.pictureOneCost +
            $scope.newOrder.pictureTwoCost +
            $scope.newOrder.pictureThreeCost +
            $scope.newOrder.frameCost +
            $scope.newOrder.epitaphCost +
            $scope.newOrder.otherWorksOnMonumentCost;
    }
    $scope.calculationInstallationCost = function () {
        return $scope.newOrder.installationCost = $scope.newOrder.monumentInstallationCost + $scope.newOrder.otherInstallationCost;
    }

    $scope.calculationTotal = function () {
       return  $scope.newOrder.orderCost = $scope.newOrder.stoneCost + $scope.newOrder.workCost + $scope.newOrder.installationCost;
    }

    $scope.countLettersNameOnMonument = function (letters) {
        let lettersLength;
        if (letters !== "") {
            lettersLength = letters.match(/[a-zA-Zа-яА-Я]/g).length;
            $scope.newOrder.nameOnMonumentCost = lettersLength * 35;
            return lettersLength;
        }
    }
    $scope.countLettersEpitaph = function (letters) {
        let lettersLength;
        if (letters !== "") {
            lettersLength = letters.match(/[a-zA-Zа-яА-Я]/g).length;
            $scope.newOrder.epitaphCost = lettersLength * 35;
            return lettersLength;
        }
    }

});