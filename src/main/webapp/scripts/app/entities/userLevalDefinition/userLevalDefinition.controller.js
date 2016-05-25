'use strict';

angular.module('citygardenWeb1App')
    .controller('UserLevalDefinitionController', function ($scope, $state, UserLevalDefinition) {

        $scope.userLevalDefinitions = [];
        $scope.loadAll = function() {
            UserLevalDefinition.query(function(result) {
               $scope.userLevalDefinitions = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.userLevalDefinition = {
                id: null
            };
        };
    });
