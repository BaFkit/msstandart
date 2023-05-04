angular.module('msstandart')
    .directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }])
    .service('fileUpload', ['$http', function ($http) {
        this.uploadFileToUrl = function (file, uploadUrl, isPreview) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('isPreview', isPreview);

            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
                .then(function successCallback(response) {
                    alert('Загружено успешно');
                }, function errorCallback(response) {
                    alert('Ошибка в загрузке');
                });
        }
    }])
    .controller('orderViewController', ['$localStorage', '$scope', 'fileUpload',  '$rootScope', '$http', function($localStorage, $scope, fileUpload, $rootScope, $http) {
        const contextPath = 'http://localhost:8080/api/v1/orders/';

        $scope.uploadFile = function() {

            var file = $scope.myFile;
            console.dir(file);
            var uploadUrl = contextPath + "images/upload/" + $localStorage.orderId;
            fileUpload.uploadFileToUrl(file, uploadUrl, $scope.isPreview);
        }


        $scope.loadOrder = function () {
            $http.get(contextPath + $localStorage.orderId)
                .then(function (response) {
                    $scope.order = response.data
                    console.log($scope.order)
                });
        }

        // $scope.loadPreview  = function () {
        //     $http.get(contextPath + "images/" + $localStorage.orderId + "/preview")
        //         .then(function (response) {
        //             // $scope.orderPreview = response.data
        //         });
        // }

        // $scope.loadPreview  = function () {
        //     $http({
        //         method: 'GET',
        //         url: contextPath + "images/" + $localStorage.orderId + "/preview",
        //         responseType: 'arraybuffer'
        //     }).then(function(response) {
        //         console.log(response);
        //         var str = _arrayBufferToBase64(response.data.bytes);
        //         console.log(str);
        //         // str is base64 encoded.
        //     }, function(response) {
        //         console.error('error in getting static img.');
        //     });
        // }

        // function _arrayBufferToBase64(buffer) {
        //     var binary = '';
        //     var bytes = new Uint8Array(buffer);
        //     var len = bytes.byteLength;
        //     for (var i = 0; i < len; i++) {
        //         binary += String.fromCharCode(bytes[i]);
        //     }
        //     return window.btoa(binary);
        // }

        $scope.changeOrderStatus = function (status) {
            $http.get(contextPath + "status/change/" + $localStorage.orderId + "/" + status)
                .then(function (response) {
                    window.location.reload();
                });
        }

        $scope.loadOrder();
        // $scope.loadPreview();
    }]);