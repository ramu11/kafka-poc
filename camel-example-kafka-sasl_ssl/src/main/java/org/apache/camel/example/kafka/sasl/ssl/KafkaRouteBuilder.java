package org.apache.camel.example.kafka.sasl.ssl;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.language.SimpleExpression;
import org.apache.kafka.common.serialization.StringSerializer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KafkaRouteBuilder extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		
        //producer
        
         from("timer://foo?period={{period}}")
         .setBody(constant("Hi This is kafka example")) 
         .to("kafka:{{kafka.topic}}?brokers={{kafka.bootstrap.url}}"
                 + "&keySerializerClass=org.apache.kafka.common.serialization.StringSerializer"
                 + "&serializerClass=org.apache.kafka.common.serialization.StringSerializer" 
                 + "&securityProtocol={{security.protocol}}&saslJaasConfig={{sasl.jaas.config}}"
                 + "&saslMechanism={{sasl.mechanism}}&sslTruststoreLocation={{ssl.truststore.location}}"
                 + "&sslTruststorePassword={{ssl.truststore.password}}&sslTruststoreType={{ssl.truststore.type}}")
         .log("${body}");
         
         //consumer
         
        
        from("kafka:{{consumer.topic}}?brokers={{kafka.bootstrap.url}}&maxPollRecords={{consumer.max.poll.records}}"
                 + "&keySerializerClass=org.apache.kafka.common.serialization.StringSerializer"
                + "&serializerClass=org.apache.kafka.common.serialization.StringSerializer" 
                + "&groupId={{consumer.group}}&securityProtocol={{security.protocol}}&saslJaasConfig={{sasl.jaas.config}}"
                + "&saslMechanism={{sasl.mechanism}}&sslTruststoreLocation={{ssl.truststore.location}}"
                 + "&sslTruststorePassword={{ssl.truststore.password}}&sslTruststoreType={{ssl.truststore.type}}"
               + "&autoOffsetReset={{consumer.auto.offset.reset}}&autoCommitEnable={{consumer.auto.commit.enable}}")
         .log("${body}");
        
       
		
	}
	  

}
