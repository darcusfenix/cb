grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',                          access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/error',                     access: ['permitAll']],
        [pattern: '/index',                     access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/index.gsp',                 access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/shutdown',                  access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/assets/**',                 access: ['permitAll']],
        [pattern: '/**/js/**',                  access: ['permitAll']],
        [pattern: '/**/css/**',                 access: ['permitAll']],
        [pattern: '/**/images/**',              access: ['permitAll']],
        [pattern: '/**/favicon.ico',            access: ['permitAll']],
        [pattern: '/login',                     access: ['permitAll']],
        [pattern: '/login/**',                  access: ['permitAll']],
        [pattern: '/logout',                    access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/logout/**',                 access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/static',                    access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/static/**',                 access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']],
        [pattern: '/angularjs-app/views/**',    access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN']]
]

grails.plugin.springsecurity.securityConfigType = "Annotation"