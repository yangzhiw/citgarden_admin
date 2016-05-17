'use strict';

angular.module('citygardenWeb1App')
	.controller('ProvideMerchantDeleteController', function($scope, $uibModalInstance, entity, ProvideMerchant) {

        $scope.provideMerchant = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ProvideMerchant.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
