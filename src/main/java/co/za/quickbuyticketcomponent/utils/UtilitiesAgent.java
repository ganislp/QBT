package co.za.quickbuyticketcomponent.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.springframework.stereotype.Component;

@Component
public class UtilitiesAgent {


	
	public long getTimeStampInMillis()
	{
		return System.currentTimeMillis();
	}
	
	public String getExceptionMessage(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public String getCurrentTimeStamp() {
		String currnetDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		currnetDate = currnetDate.replaceAll(" ", "T");
		return currnetDate;
	}
	
	public String getGUID()
	{
		return java.util.UUID.randomUUID().toString();
	}
	
	/*public void sendWelcomeEmail(Company company) throws Exception
	{
		*//*UserInfo userInfo = new UserInfo();
		userInfo.setActivationCode(Messages.EMPTY);
		userInfo.setCompanyName(company.getCompanyname());
		userInfo.setEmailAddress(company.getCompanyemail());
		userInfo.setFirstName(Messages.EMPTY);
		userInfo.setMobileno(company.getBusinessmobilecode() + company.getBusinessmobilenumber());
		userInfo.setLanguage(Messages.ENGLISH);
		userInfo.setLastName(Messages.EMPTY);
		userInfo.setListId(Messages.EMPTY);
		userInfo.setStatus(Messages.EMPTY);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(userInfo);
		EmailServices emailServices=new EmailServices();
		emailServices.sendHtmlWelcomeEmail(userInfo);*//*
	}
	
	public void sendVerifyEmail(Company company, String activationCode) throws Exception
	{
		*//*UserInfo userInfo = new UserInfo();
		userInfo.setActivationCode(activationCode);
		userInfo.setCompanyName(company.getCompanyname());
		userInfo.setEmailAddress(company.getCompanyemail());
		userInfo.setMobileno(company.getBusinessmobilecode() + company.getBusinessmobilenumber());
		userInfo.setFirstName(Messages.EMPTY);
		userInfo.setLanguage(Messages.ENGLISH);
		userInfo.setLastName(Messages.EMPTY);
		userInfo.setListId(Messages.EMPTY);
		userInfo.setStatus(Messages.EMPTY);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(userInfo);
		EmailServices emailServices=new EmailServices();
		emailServices.sendVerifyEmail(userInfo,activationCode);*//*
	}
	
	public void sendVerifyMobile(Company company, String activationCode, JmsTemplate jmsTemplate) throws Exception
	{
		*//*UserInfo userInfo = new UserInfo();
		userInfo.setActivationCode(activationCode);
		userInfo.setCompanyName(company.getCompanyname());
		userInfo.setEmailAddress(company.getCompanyemail());
		userInfo.setMobileno(company.getBusinessmobilecode() + company.getBusinessmobilenumber());
		userInfo.setFirstName(Messages.EMPTY);
		userInfo.setLanguage(Messages.ENGLISH);
		userInfo.setLastName(Messages.EMPTY);
		userInfo.setListId(Messages.EMPTY);
		userInfo.setStatus(Messages.EMPTY);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(userInfo);
		jmsTemplate.convertAndSend(Messages.SMS_VERIFYMOBILE_REQ, jsonInString);*//*
	}*/
	
	/*public void sendControllerDataRequest(String jsonInString, JmsTemplate jmsTemplate)
	{
		jmsTemplate.convertAndSend(Messages.LOG_CONTROLLERDATA_REQ, jsonInString);
	}
	
	public void sendControllerDataResponse(String jsonInString, JmsTemplate jmsTemplate)
	{
		jmsTemplate.convertAndSend(Messages.LOG_CONTROLLERDATA_RES, jsonInString);
	}
	
	public void sendServiceDataRequest(String jsonInString, JmsTemplate jmsTemplate)
	{
		jmsTemplate.convertAndSend(Messages.LOG_SERVICEDATA_REQ, jsonInString);
	}
	
	public void sendServiceDataResponse(String jsonInString, JmsTemplate jmsTemplate)
	{
		jmsTemplate.convertAndSend(Messages.LOG_SERVICEDATA_RES, jsonInString);
	}*/
}
