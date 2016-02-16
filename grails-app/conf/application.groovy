grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/index.gsp',      access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/shutdown',       access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/login',          access: ['permitAll']],
        [pattern: '/login/**',       access: ['permitAll']],
        [pattern: '/logout',         access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/logout/**',      access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/static',             access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/static/**',          access: ['ROLE_SUPER_ADMIN']],
        [pattern: '/angularjs-app/views/**',          access: ['ROLE_SUPER_ADMIN']]
]

grails.plugin.springsecurity.securityConfigType = "Annotation"