package barbarahliskov.cambialibros;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by danie on 14/05/2017.
 */

public class XML_Parser {

    static ArrayList<String> parseaResultadoBusqueda(String XML) {
        /* Quitamos los saltos de linea */
        XML = XML.replace("\n", "");
        /* Parseamos la cabecera de la búsqueda */
        ArrayList<String> parametros = new ArrayList<String>();
        /* Quitamos la cabecera */
        String parseado = XML.substring(XML.indexOf(">") + 1);
        /* Quitamos la parte de <busqueda nick="Barbara96"> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos la parte de <libreria> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        while (parseado.substring(0, 7).equals("<libro>")) {
            /* Quitamos el <libro> */
            parseado = parseado.substring(7);
            /* Quitamos el <id_libro> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <titulo> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <autor> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <usuario> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <localizacion> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <fav> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);

            /* Quitamos el </libro> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
        }

        return parametros;

    }

    static ArrayList<String> parseaResultadoFavsUsers(String XML) {
        /* Quitamos los saltos de linea */
        XML = XML.replace("\n", "");
        /* Parseamos la cabecera de la búsqueda */
        ArrayList<String> parametros = new ArrayList<String>();
        /* Quitamos la cabecera */
        String parseado = XML.substring(XML.indexOf(">") + 1);
        /* Quitamos la parte de <busqueda nick="Barbara96"> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos la parte de <gente> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        while (parseado.substring(0, 9).equals("<usuario>")) {
            /* Quitamos el <usuario> */
            parseado = parseado.substring(9);
            /* Quitamos el <nick> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <nombre> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <apellidos> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <valoracion> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <favorito> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);

            /* Quitamos el </usuario> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
        }
        return parametros;

    }

    static ArrayList<String> parseaResultadoUser(String XML) {
        /* Quitamos los saltos de linea */
        XML = XML.replace("\n", "");
        /* Parseamos la cabecera de la búsqueda */
        ArrayList<String> parametros = new ArrayList<String>();
        /* Quitamos la cabecera */
        String parseado = XML.substring(XML.indexOf(">") + 1);
        /* Quitamos la parte de <busqueda nick="Barbara96"> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos la parte de <usuario> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos el <nick> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        parametros.add(parseado.substring(0, parseado.indexOf("<")));
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos el <nombre> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        parametros.add(parseado.substring(0, parseado.indexOf("<")));
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos el <apellidos> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        parametros.add(parseado.substring(0, parseado.indexOf("<")));
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos el <valoracion> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        parametros.add(parseado.substring(0, parseado.indexOf("<")));
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        /* Quitamos el <favorito> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        parametros.add(parseado.substring(0, parseado.indexOf("<")));
        parseado = parseado.substring(parseado.indexOf(">") + 1);
        return parametros;
    }


    public static ArrayList<String> parseaResultadoHistorial(String XML) {
        /* Quitamos los saltos de linea */
        XML = XML.replace("\n", "");
        XML = XML.replace("\r", "");
        XML = XML.replace("\t", "");
        /* Parseamos la cabecera de la búsqueda */
        ArrayList<String> parametros = new ArrayList<String>();
        /* Quitamos la cabecera */
        String parseado = XML.substring(XML.indexOf(">") + 1);
        /* Quitamos la parte de <busqueda nick="Barbara96"> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);

        while (parseado.length()>13 && parseado.substring(0, 13).equals("<transaccion>")) {
            /* Quitamos el <libro> */
            parseado = parseado.substring(13);
            /* Quitamos el <id_libro> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <titulo> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <autor> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el <usuario> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            parametros.add(parseado.substring(0, parseado.indexOf("<")));
            parseado = parseado.substring(parseado.indexOf(">") + 1);
            /* Quitamos el </libro> */
            parseado = parseado.substring(parseado.indexOf(">") + 1);
        }

        return parametros;
    }

    public static boolean parseaLogin(String XML) {
        /* Quitamos los saltos de linea */
        XML = XML.replace("\n", "");
        XML = XML.replace("\r", "");
        XML = XML.replace("\t", "");
        /* Parseamos la cabecera de la búsqueda */
        ArrayList<String> parametros = new ArrayList<String>();
        /* Quitamos la cabecera */
        String parseado = XML.substring(XML.indexOf(">") + 1);
        /* Quitamos la parte de <login nick="Barbara96"> */
        parseado = parseado.substring(parseado.indexOf(">") + 1);

        if(parseado.substring(0,2).equals("ok")){
            return true;
        }
        else {
            return false;
        }
    }
}