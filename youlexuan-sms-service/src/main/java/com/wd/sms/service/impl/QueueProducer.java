package com.wd.sms.service.impl;

//测试类测试短信发送

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class QueueProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueTextDestination;

    //发送消息
    public void sendTextMessage(String text){
        jmsTemplate.send(queueTextDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setString("mobile","13299121125");
                mapMessage.setString("sign_name","优就业");
                mapMessage.setString("template_code","SMS_167532466");
                mapMessage.setString("parm","{\"code\":\"12345\"}");
                return mapMessage;
            }
        });
    }
}