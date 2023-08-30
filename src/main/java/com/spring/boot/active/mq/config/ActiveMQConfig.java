package com.spring.boot.active.mq.config;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class ActiveMQConfig {

  @Value("${spring.activemq.broker-url}")
  private String brokerUrl;

  @Value("${spring.activemq.user}")
  private String username;

  @Value("${spring.activemq.password}")
  private String password;

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setConcurrency("1-3");
    return factory;

  }

  private ConnectionFactory connectionFactory() {

    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(brokerUrl);
    activeMQConnectionFactory.setUserName(username);
    activeMQConnectionFactory.setPassword(password);
    return activeMQConnectionFactory;

  }

}
