'use strict';

angular.module('citygardenWeb1App')
    .controller('ProvideMerchantDetailController', function ($scope, $rootScope, $stateParams,$state, entity, ProvideMerchant,Upload) {
        $scope.provideMerchant = entity;
        console.log(entity);
        $scope.load = function (id) {
            ProvideMerchant.get({id: id}, function(result) {
                $scope.provideMerchant = result;
            });
        };

        $scope.upload = function (file) {
            Upload.upload({
                url: 'api/providePhoto',
                data: { file: file, pid: $stateParams.id}
            }).then(function (resp) {
                console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
            }, function (resp) {
                console.log('Error status: ' + resp.status);
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
            });

            $state.go('provideMerchant.detail',{id:$stateParams.id},{reload : true});
        };

        var unsubscribe = $rootScope.$on('citygardenWeb1App:provideMerchantUpdate', function(event, result) {
            $scope.provideMerchant = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
