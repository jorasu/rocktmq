package com.springboot.demo;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@Component
public class UserProducer {
    /**
     * 生产者组名
     */
    @Value("${suning.rocketmq.producerGroup}")
    private String producerGroup;

    /**
     * NameServer地址
     */
    @Value("${suning.rocketmq.namesrvaddr}")
    private String namesrvAddr;
    /**
     *  @PostConstruct:它的作用是相当于servlet的Init功能，在对象的构造器执行完了,就会立马调用该方法
     */
   @PostConstruct
    public void producer(){
        DefaultMQProducer producer=new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
            for(int i=0;i<100;i++){
                UserContent userContent=new UserContent(String.valueOf(i),"abc"+i);
                String jsonstr= JSON.toJSONString(userContent);
                System.out.println("发送消息："+jsonstr);
                Message message = new Message("user-topic", "user-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));                SendResult result=producer.send(message);
                System.err.println("发送响应：MsgId:"+result.getMsgId()+",发送状态："+result.getSendStatus());
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }
}
