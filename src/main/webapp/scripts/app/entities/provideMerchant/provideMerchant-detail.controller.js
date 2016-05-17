'use strict';

angular.module('citygardenWeb1App')
    .controller('ProvideMerchantDetailController', function ($scope, $rootScope, $stateParams, entity, ProvideMerchant) {
        $scope.provideMerchant = entity;
        $scope.load = function (id) {
            ProvideMerchant.get({id: id}, function(result) {
                $scope.provideMerchant = result;
            });
        };
        var unsubscribe = $rootScope.$on('citygardenWeb1App:provideMerchantUpdate', function(event, result) {
            $scope.provideMerchant = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
