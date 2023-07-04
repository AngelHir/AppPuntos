package com.software.componente.app

class Mensaje {
    String codigo
    List parametros
    Locale local

    String defaultMessage


    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List getParametros() {
        return parametros;
    }

    public void setParametros(List parametros) {
        this.parametros = parametros;
    }

    public Locale getLocal() {
        return local;
    }

    public void setLocal(Locale local) {
        this.local = local;
    }
}
