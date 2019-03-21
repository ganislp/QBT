package co.za.quickbuyticketcomponent.utils;


import co.za.quickbuyticketcomponent.modals.RestResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilderAgent {

	public RestResponse createSuccessResponse(String url) throws Exception
	{
		RestResponse restResponse = new RestResponse();
		UtilitiesAgent utilties = new UtilitiesAgent();
		restResponse.setMessage(Messages.SUCCESS);
		restResponse.setTimestamp(utilties.getTimeStampInMillis());
		restResponse.setError(Messages.NO_ERROR);
		restResponse.setStatus(Messages.RESPONSE_SUCCESS_CODE);
		restResponse.setException(Messages.NO_EXCEPTION);
		restResponse.setPath(url);
		return restResponse;
	}
	
	public RestResponse createFailureResponse(Exception e, String url, int httpErrorCode)
	{
		RestResponse restResponse = new RestResponse();
		UtilitiesAgent utilties = new UtilitiesAgent();
		restResponse.setMessage(utilties.getExceptionMessage(e));
		restResponse.setTimestamp(utilties.getTimeStampInMillis());
		restResponse.setError(e.getMessage());
		restResponse.setStatus(httpErrorCode);
		restResponse.setPath(url);
		restResponse.setException(e.getClass().getSimpleName());
		return restResponse;
	}
	
	public RestResponse createTrueResponse(String url) throws Exception
	{
		RestResponse restResponse = new RestResponse();
		UtilitiesAgent utilties = new UtilitiesAgent();
		restResponse.setMessage(Messages.TRUE);
		restResponse.setTimestamp(utilties.getTimeStampInMillis());
		restResponse.setError(Messages.NO_ERROR);
		restResponse.setStatus(Messages.RESPONSE_SUCCESS_CODE);
		restResponse.setException(Messages.NO_EXCEPTION);
		restResponse.setPath(url);
		return restResponse;
	}
	
	public RestResponse createFalseResponse(String url) throws Exception
	{
		RestResponse restResponse = new RestResponse();
		UtilitiesAgent utilties = new UtilitiesAgent();
		restResponse.setMessage(Messages.FALSE);
		restResponse.setTimestamp(utilties.getTimeStampInMillis());
		restResponse.setError(Messages.NO_ERROR);
		restResponse.setStatus(Messages.RESPONSE_SUCCESS_CODE);
		restResponse.setException(Messages.NO_EXCEPTION);
		restResponse.setPath(url);
		return restResponse;
	}
}
//{
//    "timestamp": 1505821960590,
//    "status": 500,
//    "error": "Internal Server Error",
//    "exception": "java.lang.IllegalStateException",
//    "message": "Ambiguous handler methods mapped for HTTP path 'http://localhost:8085/i4gorigin.portal/topics/6': {public com.i4g.accounts.modals.Topic com.i4g.accounts.controllers.TopicController.getTopic(java.lang.String), public java.util.List com.i4g.accounts.controllers.TopicController.getTopicByName(java.lang.String)}",
//    "path": "/i4gorigin.portal/topics/6"
//}