angular.module('msstandart').controller('personnelController', function ($scope, $http, $localStorage) {

    const contextPath = 'http://localhost:8080/api/v1/users';

    $scope.loadUsers = function () {
        $http({
            url: contextPath,
            method: 'GET',
        })
            .then(function (response) {
                $scope.users = response.data;
            });
    }

    $scope.selectUser = function (userId) {
        $scope.userId = userId;

    }

    $scope.changePassword = function () {
        $http({
            url: contextPath + "/change/pass",
            method: 'POST',
            params: {
                id: $scope.userId,
                password: $scope.password
            }
        })
            .then(function (response) {
                $scope.password = null;
                alert('Пароль изменен успешно');
            }, function errorCallback(response) {
                alert('Ошибка в смене пароля');
            });
    }

    $scope.loadUsers();
});