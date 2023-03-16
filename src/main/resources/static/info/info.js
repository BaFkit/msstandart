angular.module('msstandart').controller('infoController', function ($scope, $http, $localStorage) {

    $scope.username = $localStorage.webUser.username

});