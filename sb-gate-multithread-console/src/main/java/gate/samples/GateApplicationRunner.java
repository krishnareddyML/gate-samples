package gate.samples;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import gate.AnnotationSet;
import gate.Document;
import gate.Factory;
import gate.util.DocumentProcessor;

@Service
public class GateApplicationRunner {

	@Autowired
	DocumentProcessor documentProcessor;
	
	@Autowired
	JmsTemplate jmsTemplate;
    
	@Scheduled(fixedDelay=10000) // Delay in MilliSeconds
	public void createInputDocumentsQueue() {

		//Get InputDocument from your repository
		InputDocument inputDocument=new InputDocument();
		inputDocument.setDocumentId(1);
		inputDocument.setDocumentContent("Sample document text");
		
		jmsTemplate.convertAndSend("inputDocumentQueue",inputDocument);
		jmsTemplate.convertAndSend("inputDocumentQueue",inputDocument);
		jmsTemplate.convertAndSend("inputDocumentQueue",inputDocument);
		
		
		
	}
	
	//  It is better if we have concurrency number equal to application.xgapp max pool size (check in gate-beans.xml)  
	@JmsListener(destination="inputDocumentQueue", concurrency="3")
	public void processDocument(InputDocument inputDocument) throws Exception{

		Document gateDocument= Factory.newDocument(inputDocument.getDocumentContent());
		documentProcessor.processDocument(gateDocument);
		AnnotationSet annotationSet=gateDocument.getAnnotations();

		System.out.println("Current Thread Processing:"+Thread.currentThread().getName());
		
		// Do other required stuff with Annotation Set results
		System.out.println("Annotation SET:"+annotationSet.toString());
		
		try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
			System.out.println("Exception raised in Thread:"+ex);
		}
		
	}
	
	
}
