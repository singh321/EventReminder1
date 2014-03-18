import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.stream.FileImageInputStream;
import javax.mail.*;
import javax.mail.internet.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SendEmail_Test
{
	final String senderEmailID = proFun().getProperty("sender_EmailID");
	 final String senderPassword = proFun().getProperty("sender_Password");
	 final String emailSMTPserver = proFun().getProperty("email_SMTPserver");
	 final String emailServerPort = proFun().getProperty("email_ServerPort");
	
String receiverEmailID = null;
String emailSubject = null;
String emailBody = null;

public SendEmail_Test(String receiverEmailID, String emailSubject, String emailBody)
{
this.receiverEmailID=receiverEmailID;
this.emailSubject=emailSubject;
this.emailBody=emailBody;


Properties props = new Properties();
props.put("mail.smtp.user",senderEmailID);
props.put("mail.smtp.host", emailSMTPserver);
props.put("mail.smtp.port", emailServerPort);
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.auth", "true");
// props.put("mail.smtp.debug", "true");
props.put("mail.smtp.socketFactory.port", emailServerPort);
props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.socketFactory.fallback", "false");

SecurityManager security = System.getSecurityManager();

try
{
Authenticator auth = new SMTPAuthenticator();
Session session = Session.getInstance(props, auth);
// session.setDebug(true);

MimeMessage msg = new MimeMessage(session);
//for email body if html not used
msg.setText(emailBody);
//for email subject if not html
msg.setSubject(emailSubject);
msg.setFrom(new InternetAddress(senderEmailID));
msg.addRecipient(Message.RecipientType.TO,new InternetAddress(receiverEmailID));

Transport.send(msg);
}
catch (Exception mex)
{
mex.printStackTrace();
}


}
public class SMTPAuthenticator extends javax.mail.Authenticator
{
public PasswordAuthentication getPasswordAuthentication()
{
return new PasswordAuthentication(senderEmailID, senderPassword);
}
}

public static List<String> getmailsforbday(){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 List<String> resBirth=new ArrayList<String>();
	  
	  try{
		  
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 System.out.println(connection);
	  	 statement=(Statement) connection.createStatement();
	  	 resultset = statement.executeQuery("SELECT EMAIL FROM emp WHERE dob=curdate()");
	  	System.out.println("result set is:"+resultset);
	  	while(resultset.next()){
	  		resBirth.add(resultset.getString("email"));
	  		
	  	} 
	  }
	  catch(Exception e){
	  	System.out.println("Error:"+e.getMessage());
	  } 
	  System.out.println(resBirth);
	  return resBirth;
	}
public static List<String> getmailsforAnniversary(){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 List<String> resAnn=new ArrayList<String>();
	  
	  try{
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 statement=(Statement) connection.createStatement();
	  	 resultset = statement.executeQuery("SELECT EMAIL FROM emp WHERE anniversary=curdate()");
	  	
	  	while(resultset.next()){
	  		resAnn.add(resultset.getString("email"));
	  	} 
	  }
	  catch(Exception e){
	  	System.out.println("Error:"+e.getMessage());
	  } 
	  return resAnn;
	}

 public static List<String> getmailsALL(){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 List<String> birthAll=new ArrayList<String>();
	  
	  try{
		  
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 System.out.println(connection);
	  	 statement=(Statement) connection.createStatement();
	  	 resultset = statement.executeQuery("SELECT EMAIL FROM emp");
	  	System.out.println("result set is:"+resultset);
	  	while(resultset.next()){
	  		birthAll.add(resultset.getString("email"));
	  	} 
	  }
	  catch(Exception e){
	  	System.out.println("Error:"+e.getMessage());
	  } 
	  System.out.println(birthAll);
	  return birthAll;
	}
 
 //birthday emp name...
public static List<String> getName(List<String> empMail){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 System.out.println("inside get name");
	 List<String> birthName=new ArrayList<String>();
	  Iterator itr=empMail.iterator(); 
	while(itr.hasNext()){
	  try{ 
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 System.out.println(connection);
	  	 String name = (String) itr.next();
	  	System.out.println("mail value is:"+name);
	  	String sql="SELECT empname FROM emp WHERE email='"+name+"'";
	  	System.out.println("result of Sql is:"+sql);
	  	statement=(Statement) connection.createStatement();
	  	 resultset = statement.executeQuery(sql);
	  	System.out.println("result set is:"+resultset);
	  	while(resultset.next()){
	  		birthName.add(resultset.getString("empname"));
	  	}
	  }
	  catch(Exception e){
		  System.out.println("Exception in sql");
	  	System.out.println("Error:"+e.getMessage());
	  } 
	 }
	  System.out.println(birthName);
	  return birthName;
	}


// inset email in to run table after sending mail...
public static void insertRun(String empMail){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	
	  try{ 
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 System.out.println(connection);
	  	
	  	System.out.println("mail value is:"+empMail);
	  	String sql="INSERT into RUN (email,rundate) values('"+empMail+"', curdate())";
	  	
	  	System.out.println("result of Sql is:"+sql);
	  	PreparedStatement prep = connection.prepareStatement(sql);
	  	prep.executeUpdate();
	  	
	  	prep.close();
	  	
	  }
	  catch(Exception e){
		  System.out.println("Exception in sql");
	  	System.out.println("Error:"+e.getMessage());
	  } 
	 }

public static void updateRun(String empMail){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 
	 //List<String> birthName=new ArrayList<String>();
	 
	 
	  try{ 
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 System.out.println(connection);
	  	// String emapmail = (String) itr.next();
	  	System.out.println("mail value is:"+empMail);
	  	String sql="UPDATE run SET rundate=curdate() WHERE email='"+empMail+"'";
	  	
	  	System.out.println("result of Sql is:"+sql);
	  	PreparedStatement prep = connection.prepareStatement(sql);
	  	prep.executeUpdate();
	  	
	  	prep.close();
	  	
	  }
	  catch(Exception e){
		  System.out.println("Exception in sql");
	  	System.out.println("Error:"+e.getMessage());
	  } 
	 }

public static List<String> getmailFromRun(){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 List<String> mailRun=new ArrayList<String>();
	  
	  try{
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 statement=(Statement) connection.createStatement();
	  	 resultset = statement.executeQuery("SELECT email FROM run");
	  	
	  	while(resultset.next()){
	  		mailRun.add(resultset.getString("email"));
	  	} 
	  }
	  catch(Exception e){
	  	System.out.println("Error:"+e.getMessage());
	  } 
	  return mailRun;
	}


// get email list of all mail in run table who have today event and sent mail already.
public static List<String> getMailTodayRun(){
	 
	 Connection connection=null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 List<String> getMailTodatEventRun=new ArrayList<String>();
	  
	  try{
		  
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "pass");
	  	 System.out.println(connection);
	  	 statement=(Statement) connection.createStatement();
	  	 String sql="SELECT email FROM run WHERE rundate=curdate()";
	  	 System.out.println("SQL for today email in run:"+sql);
	  	 resultset = statement.executeQuery(sql);
	  	System.out.println("result set is:"+resultset);
	  	while(resultset.next()){
	  		getMailTodatEventRun.add(resultset.getString("email"));
	  		
	  	} 
	  }
	  catch(Exception e){
	  	System.out.println("Error:"+e.getMessage());
	  } 
	  System.out.println(getMailTodatEventRun);
	  return getMailTodatEventRun;
	}


public static  Properties proFun(){
	Properties property=new Properties();
	try{
		
		InputStream input = null;
		input=new FileInputStream("C://Users//kumabhoo//workspace//EventReminder1//src//reminder.properties");
		 property.load(input);
		
	}
	catch(IOException ex){
		ex.printStackTrace();
	}
	return property;
	 
}

public static void main(String[] args) throws Exception
{
	
	String employee_Name = null;
	
	 String HBmessage1 = proFun().getProperty("HBmessage_Body1");
	 String ANNmessage1 = proFun().getProperty("ANNmessage_Body1");
	 String HBmessage2 = proFun().getProperty("HBmessage_Body2");
	 String ANNmessage2 = proFun().getProperty("ANNmessage_Body2");
	 String ABmessage1 = proFun().getProperty("ABmessage_Body1");
	 String ABmessage2 = proFun().getProperty("ABmessage_Body2");
	 
	 List<String> reciverBEmail=getmailsforbday();
	 System.out.println("mail id for BOD:"+reciverBEmail);
	 //to get name of emp who has bday today
	 //getName(reciverBEmail);
	 
	 List<String> reciverAEmail=getmailsforAnniversary();
	 System.out.println("mail id for ANN:"+reciverAEmail);
	 
	 //to get name of emp who has Ann today
	 //getName(reciverAEmail);
	 
	 
	 List<String> reciveAllEmail=getmailsALL();
	 System.out.println(reciveAllEmail);

	 if(reciverBEmail !=null){
		 
		 List<String> todaySentMail=getMailTodayRun();
		 System.out.println("mail who has birthday today and already in run table:"+todaySentMail);
		 reciverBEmail.removeAll(todaySentMail);
		 System.out.println("after removing today birthday and mail was already sent:"+reciverBEmail);
		 
		 
		 Iterator<String> itrBname= getName(reciverBEmail).iterator();
		 while(itrBname.hasNext()){
			 employee_Name=itrBname.next();

			 String HBmessageFinal=HBmessage1+" "+employee_Name+" "+HBmessage2;
			
	 	String messageBSub = "Happy Birthday";
	 	System.out.println("message subject is for birthday:"+messageBSub);
	 	Iterator<String> itr1=reciveAllEmail.iterator();
	 	while(itr1.hasNext()){
	 		SendEmail_Test mailSender=new SendEmail_Test(itr1.next(),messageBSub,HBmessageFinal);
	 		}
		   }
		 
		 
		 // check for inserting into run table
		 List<String> currMailBRun=getmailFromRun();
		 System.out.println("current mail in run:"+currMailBRun);
		 for(int i=0;i<reciverBEmail.size();i++){
			 if(currMailBRun.contains(reciverBEmail.get(i))){
				 updateRun(reciverBEmail.get(i));
			 }
			 else{
				 insertRun(reciverBEmail.get(i));
			 }
		 }
		}
	 
	 
	 if(reciverAEmail !=null){
		 
		 List<String> todaySentMail=getMailTodayRun();
		 System.out.println("mail who has Anniversary today and already in run table:"+todaySentMail);
		 reciverAEmail.removeAll(todaySentMail);
		 System.out.println("after removing today Anniversary and mail was already sent:"+reciverBEmail);
		 
		 
		 Iterator<String> itrAname= getName(reciverAEmail).iterator();
		 while(itrAname.hasNext()){
			 employee_Name=itrAname.next();
			 String ANNmessageFinal=ANNmessage1+" "+employee_Name+" "+ANNmessage2;
			
		 
	 	String messageASub = "Happy Anniversary";
	 	System.out.println("message subject for anniversary:"+messageASub);
	 	Iterator<String> itr2=reciveAllEmail.iterator();
	 	while(itr2.hasNext()){
	 		SendEmail_Test mailSender=new SendEmail_Test(itr2.next(),messageASub,ANNmessageFinal);
	 		}
	 	}
		 //insertRun(reciverAEmail);
		 List<String> currMailARun=getmailsforAnniversary();
		 System.out.println("current mail in run:"+currMailARun);
		 for(int i=0;i<reciverAEmail.size();i++){
			 if(currMailARun.contains(reciverAEmail.get(i))){
				 updateRun(reciverAEmail.get(i));
			 }
			 else{
				 insertRun(reciverAEmail.get(i));
			 }
		 }
	 }
}

}