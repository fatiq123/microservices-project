package com.user.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserError {

    private String errorMessage;
    private List<String> errorDetails;

}
