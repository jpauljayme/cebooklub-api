package dev.mayhm.cebooklubapi.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    private HttpStatus status;

    private T data;

}
