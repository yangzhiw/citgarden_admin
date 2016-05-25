'use strict';

angular.module('citygardenWeb1App').controller('UserLevalDefinitionDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserLevalDefinition',
        function($scope, $stateParams, $uibModalInstance, entity, UserLevalDefinition) {

        $scope.userLevalDefinition = entity;
        $scope.load = function(id) {
            UserLevalDefinition.get({id : id}, function(result) {
                $scope.userLevalDefinition = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('citygardenWeb1App:userLevalDefinitionUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.userLevalDefinition.id != null) {
                UserLevalDefinition.update($scope.userLevalDefinition, onSaveSuccess, onSaveError);
            } else {
                UserLevalDefinition.save($scope.userLevalDefinition, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
