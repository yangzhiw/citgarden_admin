'use strict';

angular.module('citygardenWeb1App')
    .controller('UserLevalDefinitionDetailController', function ($scope, $rootScope, $stateParams, entity, UserLevalDefinition) {
        $scope.userLevalDefinition = entity;
        $scope.load = function (id) {
            UserLevalDefinition.get({id: id}, function(result) {
                $scope.userLevalDefinition = result;
            });
        };
        var unsubscribe = $rootScope.$on('citygardenWeb1App:userLevalDefinitionUpdate', function(event, result) {
            $scope.userLevalDefinition = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
