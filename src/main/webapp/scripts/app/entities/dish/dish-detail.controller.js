'use strict';

angular.module('citygardenWeb1App')
    .controller('DishDetailController', function ($scope, $rootScope, $stateParams, entity, Dish) {
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

    });
