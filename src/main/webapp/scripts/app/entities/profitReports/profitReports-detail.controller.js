'use strict';

angular.module('citygardenWeb1App')
    .controller('ProfitReportsDetailController', function ($scope, $rootScope, $stateParams, entity, ProfitReports) {
        $scope.profitReports = entity;
        $scope.load = function (id) {
            ProfitReports.get({id: id}, function(result) {
                $scope.profitReports = result;
            });
        };
        var unsubscribe = $rootScope.$on('citygardenWeb1App:profitReportsUpdate', function(event, result) {
            $scope.profitReports = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
