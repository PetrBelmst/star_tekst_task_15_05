package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationFailedException extends RuntimeException {
    String message  = "smth goes wrong during creation";
}
