'use strict';

angular.module('citygardenWeb1App').controller('ProfitReportsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProfitReports',
        function($scope, $stateParams, $uibModalInstance, entity, ProfitReports) {

        $scope.profitReports = entity;
        $scope.load = function(id) {
            ProfitReports.get({id : id}, function(result) {
                $scope.profitReports = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('citygardenWeb1App:profitReportsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.profitReports.id != null) {
                ProfitReports.update($scope.profitReports, onSaveSuccess, onSaveError);
            } else {
                ProfitReports.save($scope.profitReports, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
