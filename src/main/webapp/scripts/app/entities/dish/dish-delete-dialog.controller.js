'use strict';

angular.module('citygardenWeb1App')
	.controller('DishDeleteController', function($scope, $uibModalInstance, entity, Dish) {

        $scope.dish = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Dish.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
