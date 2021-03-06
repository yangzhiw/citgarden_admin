'use strict';

angular.module('citygardenWeb1App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('order', {
                parent: 'entity',
                url: '/orders',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.order.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/order/orders.html',
                        controller: 'OrderController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('order');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('order.detail', {
                parent: 'entity',
                url: '/order/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.order.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/order/order-detail.html',
                        controller: 'OrderDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('order');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Order', function($stateParams, Order) {
                        return Order.get({id : $stateParams.id});
                    }]
                }
            })
            .state('order.new', {
                parent: 'order',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/order/order-dialog.html',
                        controller: 'OrderDialogController',
                        size: '',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('order', null, { reload: true });
                    }, function() {
                        $state.go('order');
                    })
                }]
            })
            .state('order.edit', {
                parent: 'order',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/order/order-dialog.html',
                        controller: 'OrderDialogController',
                        size: 'sm',
                        resolve: {
                            entity: ['Order', function(Order) {
                                return Order.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('order', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('order.delete', {
                parent: 'order',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/order/order-delete-dialog.html',
                        controller: 'OrderDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Order', function(Order) {
                                return Order.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('order', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
