'use strict';

angular.module('citygardenWeb1App')
	.controller('RePertoryManagerDeleteController', function($scope, $uibModalInstance, entity, RePertoryManager) {

        $scope.rePertoryManager = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            RePertoryManager.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
