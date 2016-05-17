'use strict';

angular.module('citygardenWeb1App').controller('RePertoryManagerDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'RePertoryManager',
        function($scope, $stateParams, $uibModalInstance, entity, RePertoryManager) {

        $scope.rePertoryManager = entity;
        $scope.load = function(id) {
            RePertoryManager.get({id : id}, function(result) {
                $scope.rePertoryManager = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('citygardenWeb1App:rePertoryManagerUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.rePertoryManager.id != null) {
                RePertoryManager.update($scope.rePertoryManager, onSaveSuccess, onSaveError);
            } else {
                RePertoryManager.save($scope.rePertoryManager, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
