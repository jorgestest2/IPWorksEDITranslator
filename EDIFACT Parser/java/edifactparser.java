/*
 * IPWorks EDI Translator 2022 Java Edition - Sample Project
 *
 * This sample project demonstrates the usage of IPWorks EDI Translator in a 
 * simple, straightforward way. This is not intended to be a complete 
 * application. Error handling and other checks are simplified for clarity.
 *
 * Copyright (c) 2023 /n software inc. www.nsoftware.com
 */

import java.io.*;


import ipworkseditranslator.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class edifactparser extends ConsoleDemo {
  public static void main(String[] args) {
    Edifactreader edifactreader1 = new Edifactreader();

    try {
    	
      System.out.println("********************************************************************");
      System.out.println("* This demo shows how to use the EDIFACTReader component to parse  *");
      System.out.println("* an EDI document. A sample EDI document is provided as well.      *");
      System.out.println("********************************************************************");
    	
      edifactreader1.addEdifactreaderEventListener(new ipworkseditranslator.DefaultEdifactreaderEventListener(){
    	  public void endFunctionalGroup(EdifactreaderEndFunctionalGroupEvent e){
    		  System.out.println("EndFunctionalGroup: " + e.tag);
    	  }
    	  public void endInterchange(EdifactreaderEndInterchangeEvent e){
    		  System.out.println("EndInterchange: " + e.tag);
    	  }
    	  public void endLoop(EdifactreaderEndLoopEvent e){
    		  System.out.println("EndLoop");
    	  }
    	  public void endTransaction(EdifactreaderEndTransactionEvent e){
    		  System.out.println("EndTransaction: " + e.tag);
    	  }
    	  public void error(EdifactreaderErrorEvent e){
    		  System.out.println("ERROR: " + e.errorCode + ":" + e.description);
    	  }
    	  public void resolveSchema(EdifactreaderResolveSchemaEvent e){
    		  System.out.println("ResolveSchema: " + e.transactionCode);
    	  }
    	  public void segment(EdifactreaderSegmentEvent e){
    		  System.out.println("Segment: " + e.name);
    	  }
    	  public void startFunctionalGroup(EdifactreaderStartFunctionalGroupEvent e){
    		  System.out.println("StartFunctionalGroup: " + e.tag);
    	  }
    	  public void startInterchange(EdifactreaderStartInterchangeEvent e){
    		  System.out.println("StartInterchange: " + e.tag);
    	  }
    	  public void startLoop(EdifactreaderStartLoopEvent e){
    		  System.out.println("StartLoop: " + e.name);
    	  }
    	  public void startTransaction(EdifactreaderStartTransactionEvent e){
    		  System.out.println("StartTransaction: " + e.tag);
    	  }
    	  public void warning(EdifactreaderWarningEvent e){
    		  System.out.println("WARNING: " + e.warnCode + ": " + e.message);
    	  }
      });
      
      edifactreader1.config("Encoding=iso-8859-1");
      
      if (ConsoleDemo.prompt("Do you want to load a schema file","?","y/n").toLowerCase().equals("y")){
    	  edifactreader1.loadSchema(ConsoleDemo.prompt("Specify schema file path", ":", "./RSSBus_D97A_INVOIC.json"));
      }
      
                                                            
     
     //This demo provides information about the parsed document through the events.
     //To navigate the document using the XPath property first set:
     //edifactreader1.config("ResolveXPathOnSet=true");
     
      edifactreader1.setInputFile(prompt("Specify EDI File To Parse",":","./INVOIC.edi"));
      edifactreader1.parse();
      
    } catch (IPWorksEDITranslatorException ex) {
      System.out.println("IPWorksEDIFACT exception thrown: " + ex.getCode() + " [" + ex.getMessage() + "].");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}

class ConsoleDemo {
  private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

  static String input() {
    try {
      return bf.readLine();
    } catch (IOException ioe) {
      return "";
    }
  }
  static char read() {
    return input().charAt(0);
  }

  static String prompt(String label) {
    return prompt(label, ":");
  }
  static String prompt(String label, String punctuation) {
    System.out.print(label + punctuation + " ");
    return input();
  }

  static String prompt(String label, String punctuation, String defaultVal)
  {
	System.out.print(label + " [" + defaultVal + "] " + punctuation + " ");
	String response = input();
	if(response.equals(""))
		return defaultVal;
	else
		return response;
  }

  static char ask(String label) {
    return ask(label, "?");
  }
  static char ask(String label, String punctuation) {
    return ask(label, punctuation, "(y/n)");
  }
  static char ask(String label, String punctuation, String answers) {
    System.out.print(label + punctuation + " " + answers + " ");
    return Character.toLowerCase(read());
  }

  static void displayError(Exception e) {
    System.out.print("Error");
    if (e instanceof IPWorksEDITranslatorException) {
      System.out.print(" (" + ((IPWorksEDITranslatorException) e).getCode() + ")");
    }
    System.out.println(": " + e.getMessage());
    e.printStackTrace();
  }
}




