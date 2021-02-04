/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.edu.util;

import java.util.HashMap;
import java.util.Map;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Lucas
 */
public abstract class DialogUtil {

    private static Map<String, Object> options() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("modal", true);
        options.put("closable", false);
        options.put("width", 400);
        options.put("height", 300);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        return options;

    }

    public static void openDialog(String outcome) {
        PrimeFaces.current().dialog().openDynamic(outcome, options(), null);
    }

    public static void closeDialogAndReturn(Object object) {
        PrimeFaces.current().dialog().closeDynamic(object);
    }

}
