package helper

/**
 * Created with IntelliJ IDEA.
 * User: byambaa
 * Date: 5/10/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */


import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 7/18/11
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
class MoneyToText {


    private static String strbuf1 = "aaaaaa??ceeeeiii?єi?noo?їooo?ouuuuy?yAAAAAA??CEEEEIII?ЄI?NOO?ЇOOO?OUUUUY??";
    private static String strbuf2 = "абвгдеёжзийклмно??прсту??фхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНО??ПРСТУ??ФХЦЧШЩЪЫЬЭЮЯ";

    public static String toUnicode(String s) {
        for (int i = 0; i < s.length(); i++) {
            int index = strbuf1.indexOf(s[i])
            if (index >= 0) {
                if ((i + 1) < s.length())
                    s = s.substring(0, i) + strbuf2[index] + s.substring( (i + 1).toInteger() )
                else
                    s = s.substring(0, i) + strbuf2[index]
            }
        }
        return s;
    }

    /*
    * 1'111'111'111 - Нэг тэрбум нэг зуун арван нэгэн сая нэг зуун арван нэгэн мянга нэг зуун арван нэг
    *
    */
    private static String[] base1 = ["тэг", "нэг", "хоёр", "гурав", "дөрөв", "тав", "зургаа", "долоо", "найм", "ес"];
    private static String[] base1a = ["тэг", "нэгэн", "хоёр", "гурван", "дөрвөн", "таван", "зургаан", "долоон", "найман", "есөн"];

    private static String[] base2 = ["зуу", "арав", "хорь", "гуч", "дөч", "тавь", "жар", "дал", "ная", "ер"];
    private static String[] base2a = ["зуун", "арван", "хорин", "гучин", "д?чин", "тавин", "жаран", "далан", "наян", "ерэн"];

    private static String[] baseX = ["", "мянга", "сая", "тэрбум", "их наяд", "гунамал", "их ингүүмэл", "ялгаруулагч", "их ?в?р дээр", "хязгаар ?зэгдэл", "их шалтгааны з?йл", "эрхэт", "их сайтар х?рэгсэн", "живэх тоосон"];
    private static String[] baseXa = ["", "мянган", "сая", "тэрбум", "их наяд", "гунамал", "их инг??мэл", "ялгаруулагч", "их ?в?р дээр", "хязгаар ?зэгдэл", "их шалтгааны з?йл", "эрхэт", "их сайтар х?рэгсэн", "живэх тоосон"];


    private static String normalize(String s) {
        char[] buf = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'];

        def isContains = {char ch ->
            boolean ret = false
            buf.each {char item ->
                if (item == ch) {
                    ret = true
                    return ret
                }
            }
            return ret
        }
        String ret = "";
        for (char ch in s) {
            if (isContains(ch))
                ret += ch;
        }
        return ret;
    }

    private static String[] tolevel(String s, Boolean rev = true) {
        String tmp = "";
        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (rev) {
                tmp = s[s.length() - 1 - i] + tmp;
            }
            else {
                tmp += s[i];
            }
            if (((i + 1) % 3 == 0)) {
                ret.add(tmp);
                tmp = "";
            }
        }
        if (tmp.length() > 0)
            ret.add(tmp);
        return ret.toArray()
    }

    private static String CONCAT_STR = " ";

    def static addToList = { List<String> list, String item -> if (item.trim().length() > 0)
            list.add(item);
        return true;
    };

    private static String num2str(String s, Boolean cont = false, Boolean loop = false) {
        String ret = ""

        List<String> list

        long num = s.toLong() //Convert.toLong(s, -1)

        if (num >= 0) {
            if (num > 0) {
                s = num.toString()
                switch (s.length()) {

                    case 1:
                        if (cont) {
                            if (!loop)
                                ret = base1a[s[0].toInteger()]
                            else if (s[0].toInteger() > 1)
                                ret = base1a[s[0].toInteger()]
                            else
                                ret = base1[s[0].toInteger()]
                        }
                        else
                            ret = base1[s[0].toInteger()]
                        break;
                    case 2:
                        if (s[0].toInteger() == 0) {
                            if (cont)
                                ret = base2a[s[0].toInteger()]
                            else
                                ret = base2[s[0].toInteger()]
                        }
                        else {
                            list = new ArrayList<String>()
                            addToList(list, base2a[s[0].toInteger()])
                            addToList(list, num2str(s[1].toString(), cont))
                            ret = list.toArray().join(CONCAT_STR)
                        }
                        break;
                    case 3:
                        list = new ArrayList<String>()
                        addToList(list, num2str(s[0].toString(), s[0].toInteger() > 1))
                        addToList(list, base2a[0])
                        addToList(list, num2str(s[1].toString() + s[2].toString(), cont))
                        ret = list.toArray().join(CONCAT_STR)
                        break;
                }
            }
        }


        return ret;
    }


    private static String num2moneyText(String s, String suffix = "") {
        String ret = "";

        s = normalize(s);

        long num = s.toLong();

        if (num >= 0) {
            if (num > 0) {
                String[] level = tolevel(s)
                for (int lvl = 0; lvl < level.size(); lvl++) {
                    List<String> list = new ArrayList<String>()
                    String tmp = num2str(level[lvl], (lvl > 0) || (suffix.length() > 0), true)
                    if (tmp.length() > 0) {
                        addToList(list, tmp)
                        if (ret.trim().length() == 0 && suffix.length() > 0)
                            addToList(list, baseXa[lvl])
                        else
                            addToList(list, baseX[lvl])
                    }
                    addToList(list, ret);
                    ret = list.toArray().join(CONCAT_STR)
                }
                if (suffix.length() > 0)
                    ret = ret + CONCAT_STR + suffix
            }
            else {
                ret = base1[0]
                if (suffix.length() > 0)
                    ret = ret + CONCAT_STR + suffix
            }
        }
        else {
            throw new Exception("Тоо оруулна уу?")
        }

        return ret;
    }


    public static String toMoneyText(Double d, String suffix1="", String suffix2="")
    {
        DecimalFormat df = new DecimalFormat("##0.00");

        String s = df.format(d);
        String[] arr = s.split('\\.')
        String buhel = arr[0]
        String butarhai = "00"

        if (arr.length>1)
            butarhai = arr[1]
        println("============="+buhel+suffix1)
        return num2moneyText(buhel, suffix1) + CONCAT_STR + butarhai + CONCAT_STR + suffix2
    }


}
