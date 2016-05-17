'use strict';

angular.module('citygardenWeb1App')
    .controller('RePertoryManagerDetailController', function ($scope, $rootScope, $stateParams, entity, RePertoryManager) {
        $scope.rePertoryManager = entity;
        $scope.load = function (id) {
            RePertoryManager.get({id: id}, function(result) {
                $scope.rePertoryManager = result;
            });
        };
        var unsubscribe = $rootScope.$on('citygardenWeb1App:rePertoryManagerUpdate', function(event, result) {
            $scope.rePertoryManager = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
