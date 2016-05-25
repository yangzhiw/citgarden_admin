'use strict';

angular.module('citygardenWeb1App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('userLevalDefinition', {
                parent: 'entity',
                url: '/userLevalDefinitions',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.userLevalDefinition.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/userLevalDefinition/userLevalDefinitions.html',
                        controller: 'UserLevalDefinitionController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userLevalDefinition');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('userLevalDefinition.detail', {
                parent: 'entity',
                url: '/userLevalDefinition/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.userLevalDefinition.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/userLevalDefinition/userLevalDefinition-detail.html',
                        controller: 'UserLevalDefinitionDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userLevalDefinition');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'UserLevalDefinition', function($stateParams, UserLevalDefinition) {
                        return UserLevalDefinition.get({id : $stateParams.id});
                    }]
                }
            })
            .state('userLevalDefinition.new', {
                parent: 'userLevalDefinition',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/userLevalDefinition/userLevalDefinition-dialog.html',
                        controller: 'UserLevalDefinitionDialogController',
                        size: '',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('userLevalDefinition', null, { reload: true });
                    }, function() {
                        $state.go('userLevalDefinition');
                    })
                }]
            })
            .state('userLevalDefinition.edit', {
                parent: 'userLevalDefinition',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/userLevalDefinition/userLevalDefinition-dialog.html',
                        controller: 'UserLevalDefinitionDialogController',
                        size: '',
                        resolve: {
                            entity: ['UserLevalDefinition', function(UserLevalDefinition) {
                                return UserLevalDefinition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('userLevalDefinition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('userLevalDefinition.delete', {
                parent: 'userLevalDefinition',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/userLevalDefinition/userLevalDefinition-delete-dialog.html',
                        controller: 'UserLevalDefinitionDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['UserLevalDefinition', function(UserLevalDefinition) {
                                return UserLevalDefinition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('userLevalDefinition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
