angular.module('msstandart').controller('orderCreateController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/api/v1';

    $scope.tryCreateNewOrder = function () {
        $http.post(contextPath + '/orders', $scope.newOrder)
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

    $scope.isNotStandard = function (notStandard) {
        return notStandard !== "Не стандарт";
    }

    $scope.calculationMonumentCost = function () {
        return $scope.newOrder.stoneCost =  $scope.newOrder.monumentCost +  $scope.newOrder.stoneKitCost + $scope.newOrder.stoneFigureCost;
    }
    $scope.calculationWorksOnMonumentCost = function () {
        return $scope.newOrder.workCost =
            $scope.newOrder.nameOnMonumentCost +
            $scope.newOrder.nameOnMonumentCostSecond +
            $scope.newOrder.nameOnMonumentCostThird +
            $scope.newOrder.dateOnMonumentCost +
            $scope.newOrder.dateOnMonumentCostSecond +
            $scope.newOrder.dateOnMonumentCostThird +
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
        if(letters == undefined) {
            $scope.newOrder.nameOnMonumentCost = 0;
            return;
        }
        letters = letters.match(/[a-zA-Zа-яА-Я]/g);
        if(letters == null) {
            $scope.newOrder.nameOnMonumentCost = 0;
        } else {
            lettersLength = letters.length;
            $scope.newOrder.nameOnMonumentCost = lettersLength * $scope.prices.letterNameCost;
            return lettersLength;
        }
    }
    $scope.countLettersNameOnMonumentSecond = function (letters) {
        let lettersLength;
        if(letters == undefined) {
            $scope.newOrder.nameOnMonumentCostSecond = 0;
            return;
        }
        letters = letters.match(/[a-zA-Zа-яА-Я]/g);
        if(letters == null) {
            $scope.newOrder.nameOnMonumentCostSecond = 0;
        } else {
            lettersLength = letters.length;
            $scope.newOrder.nameOnMonumentCostSecond = lettersLength * $scope.prices.letterNameCost;
            return lettersLength;
        }
    }
    $scope.countLettersNameOnMonumentThird = function (letters) {
        let lettersLength;
        if(letters == undefined) {
            $scope.newOrder.nameOnMonumentCostThird = 0;
            return;
        }
        letters = letters.match(/[a-zA-Zа-яА-Я]/g);
        if(letters == null) {
            $scope.newOrder.nameOnMonumentCostThird = 0;
        } else {
            lettersLength = letters.length;
            $scope.newOrder.nameOnMonumentCostThird = lettersLength * $scope.prices.letterNameCost;
            return lettersLength;
        }
    }
    $scope.countLettersEpitaph = function (letters) {
        let lettersLength;
        if(letters == undefined) {
            $scope.newOrder.epitaphCost = 0;
            return;
        }
        letters = letters.match(/[a-zA-Zа-яА-Я]/g);
        if(letters == null) {
            $scope.newOrder.epitaphCost = 0;
        } else {
            lettersLength = letters.length;
            $scope.newOrder.epitaphCost = lettersLength * $scope.prices.letterEpitaphCost;
            return lettersLength;
        }
    }
    $scope.countDigits = function (digits) {
        let digitsLength;
        if(digits == undefined) {
            $scope.newOrder.dateOnMonumentCost = 0;
            return;
        }
        digits = digits.match(/[0-9]/g);
        if(digits == null) {
            $scope.newOrder.dateOnMonumentCost = 0;
        } else {
            digitsLength = digits.length;
            $scope.newOrder.dateOnMonumentCost = digitsLength * $scope.prices.digitCost;
            return digitsLength;
        }
    }
    $scope.countDigitsSecond = function (digits) {
        let digitsLength;
        if(digits == undefined) {
            $scope.newOrder.dateOnMonumentCostSecond = 0;
            return;
        }
        digits = digits.match(/[0-9]/g);
        if(digits == null) {
            $scope.newOrder.dateOnMonumentCostSecond = 0;
        } else {
            digitsLength = digits.length;
            $scope.newOrder.dateOnMonumentCostSecond = digitsLength * $scope.prices.digitCost;
            return digitsLength;
        }
    }
    $scope.countDigitsThird = function (digits) {
        let digitsLength;
        if(digits == undefined) {
            $scope.newOrder.dateOnMonumentCostThird = 0;
            return;
        }
        digits = digits.match(/[0-9]/g);
        if(digits == null) {
            $scope.newOrder.dateOnMonumentCostThird = 0;
        } else {
            digitsLength = digits.length;
            $scope.newOrder.dateOnMonumentCostThird = digitsLength * $scope.prices.digitCost;
            return digitsLength;
        }
    }

    $scope.loadPrices = function () {
        $http.get(contextPath + '/options/prices')
            .then(function (response) {
                $scope.prices = response.data;
            }, function errorCallback(response) {
                alert('Ошибка в загрузке цен');
            });
    }

    $scope.loadPrices();
});