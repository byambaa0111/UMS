package com.ums.hr

import com.ums.base.BaseDomain
import grails.util.Holders


class FLanguages extends BaseDomain{

    String language
    String reading
    String writing
    String speaking
    String translating
    String teaching
    String descriptions

    static belongsTo = [teacher:Teacher]

    static constraints = {

        language inList: Holders.config.getProperty("flanguages.languages.list",List);
        reading inList:     ['Өндөр', 'Дунд','Бага']
        writing inList:     ['Өндөр', 'Дунд','Бага']
        speaking inList:    ['Өндөр', 'Дунд','Бага']
        translating inList: ['Өндөр', 'Дунд','Бага']
        teaching inList:    ['Өндөр', 'Дунд','Бага']
        descriptions blank: false, nullable: true, widget: 'textarea'

    }
}
