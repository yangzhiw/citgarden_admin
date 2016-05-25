'use strict';

angular.module('citygardenWeb1App')
    .controller('ProfitReportsController', function ($scope, $state, ProfitReports) {

        $scope.profitReportss = [];
        $scope.loadAll = function() {
            ProfitReports.query(function(result) {
               $scope.profitReportss = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.profitReports = {
                id: null
            };
        };
    });
