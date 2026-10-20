package jeremy.grimont.strava_run.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", errorMessage);

        return ResponseEntity.badRequest().body(errorBody);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpClientErrorException.NotFound ex) {
        String errorMessage = ex.getMessage();

        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", errorMessage);

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(WorkoutNotFoundException.class)
    public ResponseEntity<Object> handleWorkoutNotFound(WorkoutNotFoundException ex) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }
}
