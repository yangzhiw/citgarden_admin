'use strict';

angular.module('citygardenWeb1App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('profitReports', {
                parent: 'entity',
                url: '/profitReportss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.profitReports.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/profitReports/profitReportss.html',
                        controller: 'ProfitReportsController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('profitReports');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('profitReports.detail', {
                parent: 'entity',
                url: '/profitReports/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'citygardenWeb1App.profitReports.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/profitReports/profitReports-detail.html',
                        controller: 'ProfitReportsDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('profitReports');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'ProfitReports', function($stateParams, ProfitReports) {
                        return ProfitReports.get({id : $stateParams.id});
                    }]
                }
            })
            .state('profitReports.new', {
                parent: 'profitReports',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/profitReports/profitReports-dialog.html',
                        controller: 'ProfitReportsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('profitReports', null, { reload: true });
                    }, function() {
                        $state.go('profitReports');
                    })
                }]
            })
            .state('profitReports.edit', {
                parent: 'profitReports',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/profitReports/profitReports-dialog.html',
                        controller: 'ProfitReportsDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ProfitReports', function(ProfitReports) {
                                return ProfitReports.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('profitReports', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('profitReports.delete', {
                parent: 'profitReports',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/profitReports/profitReports-delete-dialog.html',
                        controller: 'ProfitReportsDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ProfitReports', function(ProfitReports) {
                                return ProfitReports.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('profitReports', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
