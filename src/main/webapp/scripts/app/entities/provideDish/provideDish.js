'use strict';

angular.module('citygardenWeb1App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('provideDish', {
                parent: 'entity',
                url: '/provideDishs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.provideDish.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/provideDish/provideDishs.html',
                        controller: 'ProvideDishController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('provideDish');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('provideDish.detail', {
                parent: 'entity',
                url: '/provideDish/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.provideDish.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/provideDish/provideDish-detail.html',
                        controller: 'ProvideDishDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('provideDish');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'ProvideDish', function($stateParams, ProvideDish) {
                        return ProvideDish.get({id : $stateParams.id});
                    }]
                }
            })
            .state('provideDish.new', {
                parent: 'provideMerchant',
                url: 'provideDish/{id}/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/provideDish/provideDish-dialog.html',
                        controller: 'ProvideDishDialogController',
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
            .state('provideDish.edit', {
                parent: 'provideDish',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/provideDish/provideDish-dialog.html',
                        controller: 'ProvideDishDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ProvideDish', function(ProvideDish) {
                                return ProvideDish.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('provideDish', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('provideDish.delete', {
                parent: 'provideDish',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/provideDish/provideDish-delete-dialog.html',
                        controller: 'ProvideDishDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ProvideDish', function(ProvideDish) {
                                return ProvideDish.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('provideDish', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
