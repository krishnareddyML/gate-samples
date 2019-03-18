package gate.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableScheduling
@ImportResource("classpath:gate-beans.xml")
public class SbGateMultithreadConsoleApplication {

	
	
	public static void main(String[] args) {
		
	//final ConfigurableApplicationContext context = SpringApplication.run(SbGateMultithreadConsoleApplication.class, args);
    //final GateApplicationRunner gateApplicationRunner = context.getBean(GateApplicationRunner.class);
    
    SpringApplication.run(SbGateMultithreadConsoleApplication.class, args);
	        try {
	        	// Currently Application will start based on Scheduled Logic  on GateApplication class file
	        	//gateApplicationRunner.createInputDocumentsQueue(args);
	        }catch(Exception e) {
	        	e.printStackTrace();	        	
	        }
	}
	
	 @Bean // Serialize message content to json using TextMessage
	    public MessageConverter jacksonJmsMessageConverter() {
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        converter.setTargetType(MessageType.TEXT);
	        converter.setTypeIdPropertyName("_type");
	        return converter;
	    }
	
	

}
