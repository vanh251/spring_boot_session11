package ra.edu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Bắt toàn bộ các lỗi hệ thống khác (lỗi 500) như chia cho 0, NullPointer, lỗi Database sập...
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex) {

        // Ghi lại toàn bộ chi tiết lỗi (Stack Trace) vào file log hoặc console
        log.error("Lỗi hệ thống xảy ra: ", ex);

        // Trả về cho Client một thông báo chung chung, lịch sự và an toàn
        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                "Đã có lỗi hệ thống xảy ra. Vui lòng thử lại sau hoặc liên hệ Admin.",
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}