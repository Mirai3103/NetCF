package GUI.Server.Rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandler  {
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class ErrorResponse {
        private HttpStatus httpStatus;
        private String error;
        public Map<String, String> errors;


    }
    @ResponseStatus (code = HttpStatus.BAD_REQUEST, reason = "Lỗi đầu vào")

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        var errorBuilder = ErrorResponse.builder().error("Lỗi đầu vào").httpStatus(HttpStatus.BAD_REQUEST);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().
                forEach(fieldError -> {
                    if (errors.containsKey(fieldError.getField())) {
                        errors.put(fieldError.getField(), errors.get(fieldError.getField()) + ", " + fieldError.getDefaultMessage());
                    } else {
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                });

        errorBuilder.errors(errors);
        ErrorResponse error = errorBuilder.build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus (code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Lỗi hệ thống")
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        ErrorResponse error = ErrorResponse.builder().error(ex.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
