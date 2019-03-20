package gate.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.util.persistence.PersistenceManager;

public class GateApplicationRunner 
{
	
	private CorpusController corpusController;
	
	
	private void initializeGate() {
		
		try {			
			// Initializing Gate 
			Gate.init();
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public void loadGateApplication() {
		
		try {
			
			/* This code not working from jar, need to find a solution that both work in ide and jar
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("gate-files/GateApplication.xgapp").getFile());
			*/
			
			// Currently using raw location of the file, but it is not correct way to use in production  
			File file = new File("/home/eclipse-workspace/mvn-gate-app/src/main/resources/gate-files/GateApplication.xgapp");
			
			
            corpusController = (CorpusController)PersistenceManager.loadObjectFromFile(file);
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void buildDocumentCorpusAndRun() {
		try {
		Corpus corpus= Factory.newCorpus("testCorpus");
		
		 // Get Input Documents
		Document document= Factory.newDocument("sample document text"); 
		corpus.add(document);
		corpusController.setCorpus(corpus);
		corpusController.execute();
		
		// Iterate over corpus logic skipping
		
		System.out.println("Document Annotations:"+document.getAnnotations().get("ExgazAppelt").toString());
		
		
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
    public static void main( String[] args )
    {
    	GateApplicationRunner gateApplicationRunner= new GateApplicationRunner();
    	
    	gateApplicationRunner.initializeGate();
    	gateApplicationRunner.loadGateApplication();
    	gateApplicationRunner.buildDocumentCorpusAndRun();
    	
    }
}
