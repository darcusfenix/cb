grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',                          access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/error',                     access: ['permitAll']],
        [pattern: '/index',                     access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/index.gsp',                 access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/shutdown',                  access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/assets/**',                 access: ['permitAll']],
        [pattern: '/**/js/**',                  access: ['permitAll']],
        [pattern: '/**/css/**',                 access: ['permitAll']],
        [pattern: '/**/images/**',              access: ['permitAll']],
        [pattern: '/**/favicon.ico',            access: ['permitAll']],
        [pattern: '/login',                     access: ['permitAll']],
        [pattern: '/login/**',                  access: ['permitAll']],
        [pattern: '/logout',                    access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/logout/**',                 access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/static',                    access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/static/**',                 access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']],
        [pattern: '/angularjs-app/views/**',    access: ['ROLE_SUPER_ADMIN', 'ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET']]
]

grails.plugin.springsecurity.securityConfigType = "Annotation"