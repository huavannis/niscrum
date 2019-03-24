package com.hvnis.niscrum.exception.handler;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;

/**
 * @author hvnis
 */
@Builder
@Getter
public class ExceptionData implements Serializable {

    private static final long serialVersionUID = 3223657687633360435L;

    private Date timestamp;

    private int status;

    private String error;

    private String message;
    
    private String path;

}
