'use strict';

angular.module('citygardenWeb1App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('rePertoryManager', {
                parent: 'entity',
                url: '/rePertoryManagers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.rePertoryManager.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/rePertoryManager/rePertoryManagers.html',
                        controller: 'RePertoryManagerController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('rePertoryManager');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('rePertoryManager.detail', {
                parent: 'entity',
                url: '/rePertoryManager/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.rePertoryManager.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/rePertoryManager/rePertoryManager-detail.html',
                        controller: 'RePertoryManagerDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('rePertoryManager');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'RePertoryManager', function($stateParams, RePertoryManager) {
                        return RePertoryManager.get({id : $stateParams.id});
                    }]
                }
            })
            .state('rePertoryManager.new', {
                parent: 'rePertoryManager',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/rePertoryManager/rePertoryManager-dialog.html',
                        controller: 'RePertoryManagerDialogController',
                        size: '',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('rePertoryManager', null, { reload: true });
                    }, function() {
                        $state.go('rePertoryManager');
                    })
                }]
            })
            .state('rePertoryManager.edit', {
                parent: 'rePertoryManager',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/rePertoryManager/rePertoryManager-dialog.html',
                        controller: 'RePertoryManagerDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['RePertoryManager', function(RePertoryManager) {
                                return RePertoryManager.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('rePertoryManager', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('rePertoryManager.delete', {
                parent: 'rePertoryManager',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/rePertoryManager/rePertoryManager-delete-dialog.html',
                        controller: 'RePertoryManagerDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['RePertoryManager', function(RePertoryManager) {
                                return RePertoryManager.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('rePertoryManager', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
