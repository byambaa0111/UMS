package helper

import javax.servlet.http.HttpServletRequest

/**
 * Created with IntelliJ IDEA.
 * User: byambaa
 * Date: 5/10/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
final class Convert {


    public static long toLong(String e, long defVal = 0) {
        try {
            return e.toLong()
        }
        catch (ex) {
            return defVal
        }
    }
    static String getColor(int i = 0){

        def colors = [ '#0000ff','#be936a','#BEBEBE','#0064CD','#FFFF00','#FF00FF','#ff0000','#00ff00'];

        return colors[i].toString()

    }
    static String getCurrentUrl(HttpServletRequest request){

        StringBuilder sb = new StringBuilder()

        sb << request.getRequestURL().substring(0,request.getRequestURL().indexOf("/", 7))

        sb << request.getAttribute("javax.servlet.forward.request_uri")

        if(request.getAttribute("javax.servlet.forward.query_string")){

            sb << "?"

            sb << request.getAttribute("javax.servlet.forward.query_string")
        }

        return sb.toString();
    }

    public static int toInt(String e, int defVal = 0) {
        try {

            return e[0].toInteger()
        }
        catch (ex) {
            return defVal
        }
    }
}
