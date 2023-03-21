angular.module('msstandart').controller('infoController', function ($scope, $http, $localStorage) {

    if ($localStorage.webUser) {
        $scope.username = $localStorage.username
    }

});