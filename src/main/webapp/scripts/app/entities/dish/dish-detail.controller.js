'use strict';

angular.module('citygardenWeb1App')
    .controller('DishDetailController', function ($scope, $rootScope, $stateParams, $state,entity, Dish,Upload) {
        $scope.dish = entity;
        $scope.load = function (id) {
            Dish.get({id: id}, function(result) {
                $scope.dish = result;
            });
        };
        var unsubscribe = $rootScope.$on('citygardenWeb1App:dishUpdate', function(event, result) {
            $scope.dish = result;
        });
        $scope.$on('$destroy', unsubscribe);


        $scope.upload = function (file) {
            Upload.upload({
                url: 'api/dishPhoto',
                data: { file: file, did: $stateParams.id}
            }).then(function (resp) {
                console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
            }, function (resp) {
                console.log('Error status: ' + resp.status);
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
            });

            $state.go('dish.detail',{id:$stateParams.id},{reload : true});
        };
    });
