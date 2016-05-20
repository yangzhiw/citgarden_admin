'use strict';

angular.module('citygardenWeb1App').controller('ProvideDishDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProvideDish',
        function($scope, $stateParams, $uibModalInstance, entity, ProvideDish) {

        $scope.provideDish = entity;
        $scope.load = function(id) {
            ProvideDish.get({id : id}, function(result) {
                $scope.provideDish = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('citygardenWeb1App:provideDishUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;

            if ($scope.provideDish.id != null) {
                ProvideDish.update($scope.provideDish, onSaveSuccess, onSaveError);
            } else {
                $scope.provideDish.provideMerchantId = $stateParams.id;
                console.log($scope.provideDish.provideMerchantId);

                   ProvideDish.save($scope.provideDish, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
