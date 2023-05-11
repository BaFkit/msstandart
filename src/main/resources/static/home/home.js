angular.module('msstandart').controller('homeController', function ($scope, $http, $localStorage, $rootScope) {

    const contextPath = 'http://localhost:8080/api/v1';

    $rootScope.loadPosts = function () {
        $http.get(contextPath + '/posts')
            .then(function (response) {
                $scope.posts = response.data;
            }, function errorCallback(response) {
                alert('Ошибка в загрузке постов');
            });
    }

    $rootScope.loadPosts();
    
});
