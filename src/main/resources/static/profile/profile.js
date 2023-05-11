angular.module('msstandart').controller('profileController', function ($scope, $http, $localStorage, $rootScope) {

    const contextPath = 'http://localhost:8080/api/v1/users';

    if ($localStorage.webUser) {
        $scope.profile = $localStorage.profile
    }

    $scope.changeFirstName = function () {
        $http({
            url: contextPath + "/change/firstName",
            method: 'PUT',
            params: {
                firstName: $scope.profile.firstName
            }
        })
            .then(function (response) {
                $rootScope.loadProfile();
            }, function errorCallback(response) {
                alert('Ошибка в смене имени');
            });
    }
});