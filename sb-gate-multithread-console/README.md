# This sample application is about how to configure gate application with spring boot and run in multi threaded environment (Console Application)

## Steps to generate gate application from GATE Developer

1. Load required resources in Gate Developer GUI Application
2. Right click on Application and Export to Gate Cloud
3. It will generate Zip file, extract zip to any temporary folder
4. Copy application.xgapp file to resources/gate-files of this project
5. Copy Dictionary and Jape files to     resources/gate-files/application-resources/{dictionary/Jape}

## Running Application

1. Configure GATE related application in project as specified in above step
2. Change logic for getting input documents to analysis in  GateApplication class under **createInputDocumentsQueue** method
3. Change fixedDelay property according to your need, currently it will add new documents to queue for every 10 Seconds (property in Milliseconds)
4. Run the application from command prompt

  