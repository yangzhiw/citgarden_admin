'use strict';

angular.module('citygardenWeb1App')
    .controller('OrderController', function ($scope, $state, Order, ParseLinks) {

        $scope.orders = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;

        $scope.deliveryWay = [
            '配送',
            '亲取'
        ];

        $scope.orderStatus = [
            "",
            "未付款",
            "未发货",
            "未收货",
            "订单完成"
        ]


        $scope.loadAll = function() {
            Order.query({page: $scope.page - 1, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.orders = result;
                console.log(result);
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.order = {
                id: null
            };
        };
    });
