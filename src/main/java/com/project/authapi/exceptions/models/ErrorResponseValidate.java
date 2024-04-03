package com.project.authapi.exceptions.models;

import java.util.List;

public class ErrorResponseValidate extends ErrorResponse {
  private List<String> fieldErrors;

  public ErrorResponseValidate(int statusCode, String message, List<String> fieldErrors) {
    super(statusCode, message);
    this.fieldErrors = fieldErrors;
  }

  public List<String> getFields() {
    return fieldErrors;
  }

  public void setFields(List<String> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

}
