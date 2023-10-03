package br.com.briciosvieira.APISpringBoot.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Date timestamp;
    private String massagem;
    private String detals;

    public ExceptionResponse(Date timestamp, String massagem, String detals) {
        this.timestamp = timestamp;
        this.massagem = massagem;
        this.detals = detals;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMassagem() {
        return massagem;
    }

    public void setMassagem(String massagem) {
        this.massagem = massagem;
    }

    public String getDetals() {
        return detals;
    }

    public void setDetals(String detals) {
        this.detals = detals;
    }
}
