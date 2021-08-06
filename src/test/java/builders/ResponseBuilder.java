package builders;

import com.example.demo.models.ErrorCodeEnum;
import com.example.demo.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    ResponseModel responseModel = new ResponseModel();
    RequestBuilder requestBuilder = new RequestBuilder();
    HttpStatus httpStatus;
    ResponseEntity<ResponseModel> response;


    public ResponseEntity<ResponseModel> getSuccessResponseRegister(){
        responseModel.details = requestBuilder.getUserDetails();
        responseModel.responseCode = ErrorCodeEnum.SUCCESS_CREATION.errorCode;
        responseModel.responseMessage = ErrorCodeEnum.SUCCESS_CREATION.errorMessage;
        httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(responseModel, httpStatus);
    }
}
