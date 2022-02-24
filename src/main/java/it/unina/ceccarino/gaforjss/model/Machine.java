/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

/**
 * Sono definite le 15 macchine totali,alcune di queste sono della stessa
 * tipologia, infatti le macchine A,B,C ,J e I sono 2,anzichè una sola.
 *
 * @author sommovir
 */
public enum Machine {
    M1("M1", "M2"),
    M2("M2", "M1"),
    M3("M3", "M4"),
    M4("M4", "M3"),
    M5("M5", "M6"),
    M6("M6", "M5"),
    M7("M7", null),
    M8("M8", null),
    M9("M9", null),
    M10("M10", null),
    M11("M11", null),
    M12("M12", "M13"),
    M13("M13", "M12"),
    M14("M14", "M15"),
    M15("M15", "M14");

    private Machine(String code, String alternativeCode) {
        this.alternativeCode = alternativeCode;
        this.code = code;
    }

    private String alternativeCode;
    private String code;

    public String getAlternativeCode() {
        return alternativeCode;
    }

    public String getCode() {
        return code;
    }

    public static Machine of(String code) {
        return switch (code) {
            case "M1" ->
                Machine.M1;
            case "M2" ->
                Machine.M2;
            case "M3" ->
                Machine.M3;
            case "M4" ->
                Machine.M4;
            case "M5" ->
                Machine.M5;
            case "M6" ->
                Machine.M6;
            case "M7" ->
                Machine.M7;
            case "M8" ->
                Machine.M8;
            case "M9" ->
                Machine.M9;
            case "M10" ->
                Machine.M10;
            case "M11" ->
                Machine.M11;
            case "M12" ->
                Machine.M12;
            case "M13" ->
                Machine.M13;
            case "M14" ->
                Machine.M14;
            case "M15" ->
                Machine.M15;
            default ->
                null;
        };
    }

}
