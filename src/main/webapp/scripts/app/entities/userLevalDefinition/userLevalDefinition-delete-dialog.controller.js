'use strict';

angular.module('citygardenWeb1App')
	.controller('UserLevalDefinitionDeleteController', function($scope, $uibModalInstance, entity, UserLevalDefinition) {

        $scope.userLevalDefinition = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            UserLevalDefinition.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
