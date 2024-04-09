package com.arquiteturasoftware.consumer.dto;

public class CarDTO {
    private Long id;

    private String modelo;

    private String year;

    private String color;

    private String chassi;

    public CarDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", chassi='" + chassi + '\'' +
                '}';
    }
}
