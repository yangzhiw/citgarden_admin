'use strict';

angular.module('citygardenWeb1App')
	.controller('ProvideDishDeleteController', function($scope, $uibModalInstance, entity, ProvideDish) {

        $scope.provideDish = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ProvideDish.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
