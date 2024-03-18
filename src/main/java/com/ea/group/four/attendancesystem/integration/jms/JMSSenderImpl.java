package com.ea.group.four.attendancesystem.integration.jms;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JMSSenderImpl implements JMSSender{

	@Autowired
	private JmsTemplate jmsTemplate;
	public void sendJMSMessage (String text, String destination){
		log.info("JMSSender: sending JMS message =" + text);
		jmsTemplate.convertAndSend(destination, text);
	}

}
