'use strict';

angular.module('citygardenWeb1App')
    .controller('ProvideDishDetailController', function ($scope, $rootScope, $stateParams, entity, ProvideDish) {
        $scope.provideDish = entity;
        $scope.load = function (id) {
            ProvideDish.get({id: id}, function(result) {
                $scope.provideDish = result;
            });
        };
        var unsubscribe = $rootScope.$on('citygardenWeb1App:provideDishUpdate', function(event, result) {
            $scope.provideDish = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
