package com.spring.boot.active.mq.listener;

import java.io.IOException;
import java.io.ObjectInputStream;
import javax.jms.JMSException;
import javax.jms.Message;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.util.ByteArrayInputStream;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.spring.boot.active.mq.model.MyCustomObject;

@Component
public class AMQListener {

  @JmsListener(destination = "my-test-queue")
  public void reciveMessage(Message message) throws JMSException {

    ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage) message;

    MyCustomObject myCustomObject = deserialize(objectMessage.getContent().getData());

    MyCustomObject myCustomObject2 = objectMessage.getBody(MyCustomObject.class);

    System.out.println(myCustomObject);

  }


  private static MyCustomObject deserialize(byte[] data) {


    try {

      ByteArrayInputStream in = new ByteArrayInputStream(data);
      ObjectInputStream is = new ObjectInputStream(in);
      MyCustomObject resp = (MyCustomObject) is.readObject();
      is.close();

      return resp;

    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

    return null;

  }


}
