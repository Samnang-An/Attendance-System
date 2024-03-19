package com.ea.group.four.attendancesystem.integration.jms;

public interface JMSSender {

	public void sendJMSMessage (String text, String destination);

}
