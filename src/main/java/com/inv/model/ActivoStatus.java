package com.inv.model;

public enum ActivoStatus {
    CREATE("Create"),
    ACTIVO("Activo"),
    BAJA("De Baja");

    private final String status;
    ActivoStatus(String status){
        this.status = status;
    }
}
