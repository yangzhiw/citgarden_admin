'use strict';

angular.module('citygardenWeb1App')
    .controller('ProvideDishController', function ($scope, $state, ProvideDish) {

        $scope.provideDishs = [];
        $scope.loadAll = function() {
            ProvideDish.query(function(result) {
               $scope.provideDishs = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.provideDish = {
                id: null
            };
        };
    });
