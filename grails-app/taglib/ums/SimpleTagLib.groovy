package ums

import grails.util.Holders

import java.time.LocalDateTime

class SimpleTagLib {
    def emoticon = { attrs, body ->
        out << body() << attrs.happy == 'true' ? " :-)" : " :-("
    }
    /*====================жижиг кард дотроо хувьтай */
    def boxColor ={attrs,body->
        out << render (template: "/common/boxColor", model:[title: attrs.title, value:attrs.value ,objectName:attrs.objectName, objectValue:attrs.objectValue,color:attrs.color,body:body()])
    }
    /*====================жижиг кард дотроо хувьтай */
    def boxCard ={attrs,body->
      out << render (template: "/common/boxCard", model:[value:attrs.value ,objectName:attrs.objectName, objectValue:attrs.objectValue,color:attrs.color])
    }
    /*===========badge буюу техт мэдээлэлийн гадуурах хүрээ */
    def badge = { attrs, body ->
        /*<span class="badge badge-primary m-b-5">Primary</span>
        <span class="badge badge-secondary m-b-5">Secondary</span>
        <span class="badge badge-success m-b-5">Success</span>
        <span class="badge badge-danger m-b-5">Danger</span>
        <span class="badge badge-warning m-b-5">Warning</span>
        <span class="badge badge-info m-b-5">Info</span>
        <span class="badge badge-light m-b-5">Light</span>
        <span class="badge badge-dark m-b-5">Dark</span>
        */
        out << body() << "<span class='badge badge-" + attrs.type + " m-b-5'>" + attrs.value + "</span>"
    }
    /*====================toggle tag*/
    def toggleRadio = { attrs, body ->
        out << render(template: "/common/toggleRadio", model: [toggleList: attrs.toggleList, name: attrs.name, methodName: attrs.methodName, title: attrs.title])
    }
    def schoolYear = { attrs, body ->
        LocalDateTime now = LocalDateTime.now();
        out << select(name: "year", from: 2012..2024, value: now.year, class: 'form-control select2 w-100',onchange:attrs.methodName)
    }
    /*====================checkbox list tag*/
    def toggleCheck = { attrs, body ->
        out << render(template: "/common/toggleCheck", model: [toggleList: attrs.toggleList, name: attrs.name, methodName: attrs.methodName, title: attrs.title])
    }
    /*====================checkbox list tag vertical layout*/
    def radioHorizontal = { attrs, body ->
        out << render(template: "/common/radioHorizontal", model: [toggleList: attrs.toggleList, name: attrs.name, methodName: attrs.methodName, title: attrs.title])
    }
    /*====================checkbox list tag vertical layout*/
    def checkBlueList = { attrs, body ->
        out << render(template: "/common/checkBoxList", model: [toggleList: attrs.toggleList, name: attrs.name, methodName: attrs.methodName, title: attrs.title])
    }
    /*====================checkbox list horizontal layout */
    def checkHorizontal = { attrs, body ->
        out << render(template: "/common/checkHorizontal", model: [toggleList: attrs.toggleList, name: attrs.name, methodName: attrs.methodName, title: attrs.title])
    }
    /*=========================selectWithGroupCheck==========*/
    def selectCheckFaculty = { attrs, body ->
        out << render(template: "/common/selectCheckFaculty", model: [multiple: attrs.multiple, name: attrs.name])
    }
    def select2Faculty = { attrs, body ->
        out << render(template: "/common/select2Faculty", model: [multiple: attrs.multiple])
    }
    def treeViewCoursesInPlan = { attrs, body ->
        out << render(template: "/common/treeViewCoursesInPlan", model: [courseInPlanlist: attrs.courseInPlanlist])
    }
    def repeat = { attrs, body ->
        def var = attrs.var ? attrs.var : "num"
        attrs.times?.toInteger().times { num ->
            out << body((var): num)
        }
    }

    //  Checkbox list that can be used as a more user-friendly alternative to
    // a multiselect list box

    def checkBoxList = { attrs, body ->

        def from = attrs.from
        def value = attrs.value
        def cname = attrs.name
        def isChecked, ht, wd, style, html
        Map idsMap = new HashMap();
        println("[IN CHECKBOX lIST]" + from)
        println("[IN CHECKBOX lIST]" + value)
        // sets the style to override height and/or width if either of them
        //  // is specified, else the default from the CSS is taken
        style = "style='"
        if (attrs.height)

            style += "height:${attrs.height};"

        if (attrs.width)

            style += "width:${attrs.width};"

        if (style.length() == "style='".length())

            style = "" else style += "'" // closing single quote

        html = "<ul class='CheckBoxList' " + style + ">"
        List ss;

        out << html
        value.each { val ->
            idsMap.put(val."${attrs.optionKey}", val)
        }
        from.each { obj ->

            // if we wanted to select the checkbox using a click anywhere on the label (also hover effect)
            //  // but grails does not recognize index suffix in the name as an array:
            // cname = "${attrs.name}[${idx++}]"
            //  and put this inside the li:
            //System.out.println("[optionKey]"+obj."${attrs.optionKey}");

            //System.out.println("[value]"+value?.contains(obj."${attrs.optionKey}"));
            isChecked = idsMap.containsKey(obj."${attrs.optionKey}");

            out << "<li> <label for='${cname}'></label>" << checkBox(name: cname, value: obj."${attrs.optionKey}", checked: isChecked) << "- ${obj}" << "</li>"
        }

        out << "</ul>"

    }
    def checkBoxListTree = { attrs, body ->

        def from = attrs.from
        def value = attrs.value
        //System.out.println("in tag"+attrs.value);
        def cname = attrs.name
        def isChecked, ht, wd, style, html

        Map idsMap = new HashMap();

        println("[IN CHECKBOX lIST]" + from)
        println("[IN CHECKBOX lIST]" + value)

        // sets the style to override height and/or width if either of them
        //  // is specified, else the default from the CSS is taken
        style = "style='"
        if (attrs.height)

            style += "height:${attrs.height};"

        if (attrs.width)

            style += "width:${attrs.width};"

        if (style.length() == "style='".length())

            style = "" else style += "'" // closing single quote

        html = "<ul class='CheckBoxList' " + style + ">"

        out << html
        value.each { val ->
            idsMap.put(val."${attrs.optionKey}", val)
        }
        from.each { obj ->
            //System.out.print("[ obj ]"+obj.subMenuList.size());

            isChecked = idsMap.containsKey(obj."${attrs.optionKey}");

            out << "<li> <label for='${cname}'></label>" << checkBox(name: cname, value: obj."${attrs.optionKey}", checked: isChecked) << "- ${obj}" << "</li>"

            out << "<ul class='CheckBoxList' " + style + ">"
            for (int j = 0; j < obj.subMenuList?.size(); j++) {
                def subobj = obj.subMenuList[j];

                isChecked = idsMap.containsKey(subobj."${attrs.optionKey}");

                out << "<li> <label for='${cname}'></label>" << checkBox(name: cname, value: subobj."${attrs.optionKey}", checked: isChecked) << "- ${subobj}" << "</li>"

            }
            out << "</ul>"
        }

        out << "</ul>"

    }
}
