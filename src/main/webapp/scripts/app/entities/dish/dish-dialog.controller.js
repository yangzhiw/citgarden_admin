'use strict';

angular.module('citygardenWeb1App').controller('DishDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Dish',
        function($scope, $stateParams, $uibModalInstance, entity, Dish) {

        $scope.dish = entity;
        $scope.load = function(id) {
            Dish.get({id : id}, function(result) {
                $scope.dish = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('citygardenWeb1App:dishUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.dish.provideMerchantId = $stateParams.id;
            $scope.isSaving = true;
            if ($scope.dish.id != null) {
                Dish.update($scope.dish, onSaveSuccess, onSaveError);
            } else {
                Dish.save($scope.dish, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
