angular.module('ingAtmsApp', ['oc.lazyLoad', 'ui.router', 'directive.loading', 'ngCookies', 'ngExDialog'])

    .config(['$ocLazyLoadProvider', function ($ocLazyLoadProvider) {
        $ocLazyLoadProvider.config({
            
        });
    }
    ])
    
    .config(['exDialogProvider', function (exDialogProvider) {
        exDialogProvider.setDefaults({
            template: './views/tpl/commonDialog.html',
            
            width: '330px',
            draggable: false,
        });
    }])
    .constant('BASEURL', '/') 
    .constant('PAGESIZE', 10)
    .constant('RESPONSECODES', {
        notfound: 404,
        badRequest: 400,
        unAuthorized: 401,
        conflict: 409
    })
    .constant('ERORES', {
        notFoundURL: 'No ATMs available for this country.',
    })
    .service('APIInterceptor', ['$injector', '$q', '$rootScope', function ($injector, $q, $rootScope) {
        var service = this;
        service.request = function (config) {
            console.log(config.url);
            console.log(config.headers['Content-Type']);
            if ((config.method === "POST" || config.method === "PATCH") && config.headers['Content-Type'] !== undefined) {
                config.headers['Content-Type'] += "; charset=UTF-8";
            }
            console.log(config.headers['Content-Type']);
            
            return config;
        };
        service.response = function (response) {

            return response;
        };
        service.responseError = function (response) {
            if (response.status === 401 || response.status === 403) {
                var loginService = $injector.get('loginService');
                var notificationFactory = $injector.get('notificationFactory');
                if (loginService.getLogedInRole() !== undefined) {

                    function logoutSuccessAction() {
                        $injector.get('$state').go('login');
                    }

                    function logoutErrorrAction(error) {
                        notificationFactory.handleError($rootScope, error);
                    }

                    loginService.logout(logoutSuccessAction, logoutErrorrAction);
                }
                $injector.get('$state').go('login');
            }
            return $q.reject(response);
        };
    }
    ])
    
    .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $httpProvider.interceptors.push('APIInterceptor');
        $urlRouterProvider.otherwise("/login");
        $stateProvider

            .state('login', {
                url: "/login",
                templateUrl: "./views/auth/login.html",
                data: {pageTitle: 'Login'},
                controller: "loginController"
            })
            .state('home', {
                url: "/home",
                templateUrl: "./views/home/home.html",
                data: {pageTitle: 'BackBase ING Atms | Home'},
                controller: "homeController",
                params: {
                    myParam: null
                },
                resolve: {
                    deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name: 'ingAtmsApp',
                            insertBefore: '#ng_load_plugins_before',
                            files: [
                                './module/services/home/homeService.js',
                                './module/controllers/home/homeController.js'
                            ]
                        });
                    }]
                }
            })
    }
    ])
    
    .run(["$rootScope", "$state", function ($rootScope, $state) {
        $rootScope.$state = $state; 
    }
    ]);