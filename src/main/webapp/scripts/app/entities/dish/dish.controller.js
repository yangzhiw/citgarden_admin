'use strict';

angular.module('citygardenWeb1App')
    .controller('DishController', function ($scope, $state, Dish) {

        $scope.dishs = [];
        $scope.loadAll = function() {
            Dish.query(function(result) {
               $scope.dishs = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.dish = {
                id: null
            };
        };
    });
