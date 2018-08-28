package com.testapi.restapi.config

import com.testapi.restapi.repository.UserRepository
import com.testapi.restapi.service.ExternalUserService
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

class IntegrationTestConfiguration {

    private DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

    @Bean
    UserRepository userRepository() {
        detachedMockFactory.Mock(UserRepository)
    }

    @Bean
    ExternalUserService externalUserService() {
        detachedMockFactory.Mock(ExternalUserService)
    }

}
