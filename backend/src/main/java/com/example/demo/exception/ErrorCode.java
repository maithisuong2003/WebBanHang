package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(1001, "User not found",HttpStatus.NOT_FOUND),
    USER_INACTIVE(1002, "User is inactive",HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1003, "Email already exists",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1004, "Username already exists",HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1005, "Role not found",HttpStatus.NOT_FOUND),
    USER_ALREADY_INACTIVE(1006, "User is already inactive",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1011, "Unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(107, "You do not have permission",HttpStatus.FORBIDDEN),
    PERMISSION_NOT_EXITED(1020, "Permission Not Exited", HttpStatus.NOT_FOUND),
    ROLE_NOT_EXITED(1008, "Role Not Found", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1009, "Invalid Token", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCHED(1012, "Incorrect Password", HttpStatus.BAD_REQUEST),
    SIGNER_NOT_MATCHED(1005, "Signer Not Found", HttpStatus.NOT_FOUND),
    SEND_MAIL_FAILED(1006, "Failed to Send Email", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_NOT_EXITED(1007, "Email Not Found", HttpStatus.NOT_FOUND),
    INVALID_KEY(1009, "Invalid Key", HttpStatus.BAD_REQUEST),
    VERIFICATION_ACCOUNT_INCORRECT_CODE(1010, "Incorrect Verification Code", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(1013, "Cart Not Found", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_FOUND(1014, "Cart Item Not Found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(1015, "Product Not Found", HttpStatus.NOT_FOUND),
    PROMOTION_NOT_FOUND(1016, "Promotion Not Found", HttpStatus.NOT_FOUND),
    INVALID_TIME(1017, "Invalid Time", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH(1018, "Password Not Match", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXITED(1019, "Product Not Exited", HttpStatus.NOT_FOUND),
    PRODUCER_NOT_EXITED(1021, "Producer Not Exited", HttpStatus.NOT_FOUND),
    SIZE_COLOR_PRODUCT_NOT_FOUND(1022, "Size Color Product Not Found", HttpStatus.NOT_FOUND),
    RATE_NOT_EXITED(1023, "Rate Not Exited", HttpStatus.NOT_FOUND),
    RATE_EDIT_LIMIT(1024, "Rate Edit Limit", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(1003, "Tài khoản không tồn tại",HttpStatus.BAD_REQUEST),

    PROMOTION_ALREADY_SAVED(1003, "Tài khoản không tồn tại",HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1020,"Category không tồn tại" ,HttpStatus.BAD_REQUEST ),
    SUPPLIER_NOT_FOUND(1020,"Suplier không tồn tại" ,HttpStatus.BAD_REQUEST ),
    PRODUCER_NOT_FOUND(1020,"Producer không tồn tại" ,HttpStatus.BAD_REQUEST );


    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
