'use strict';

angular.module('citygardenWeb1App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dish', {
                parent: 'entity',
                url: '/dishs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.dish.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dish/dishs.html',
                        controller: 'DishController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('dish');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('dish.detail', {
                parent: 'entity',
                url: '/dish/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.dish.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dish/dish-detail.html',
                        controller: 'DishDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('dish');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Dish', function($stateParams, Dish) {
                        return Dish.get({id : $stateParams.id});
                    }]
                }
            })
            .state('dish.new', {
                parent: 'dish',
                url: 'dish/{id}/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/dish/dish-dialog.html',
                        controller: 'DishDialogController',
                        size: '',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('provideMerchant.detail', {id:$stateParams.id}, { reload: true });
                    }, function() {
                        $state.go('provideMerchant.detail', {id:$stateParams.id}, { reload: true });
                    })
                }]
            })
            .state('dish.edit', {
                parent: 'dish',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/dish/dish-dialog.html',
                        controller: 'DishDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Dish', function(Dish) {
                                return Dish.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dish', null, { reload: true });
                    }, function() {
                        $state.go('provideMerchant.detail', {id:$stateParams.id}, { reload: true });
                    })
                }]
            })
            .state('dish.delete', {
                parent: 'dish',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/dish/dish-delete-dialog.html',
                        controller: 'DishDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Dish', function(Dish) {
                                return Dish.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dish', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
