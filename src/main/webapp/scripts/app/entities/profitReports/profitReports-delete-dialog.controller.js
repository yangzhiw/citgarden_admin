'use strict';

angular.module('citygardenWeb1App')
	.controller('ProfitReportsDeleteController', function($scope, $uibModalInstance, entity, ProfitReports) {

        $scope.profitReports = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ProfitReports.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
