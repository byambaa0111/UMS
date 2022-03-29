package com.ums.base

import grails.util.Holders


abstract class Person extends BaseDomain {
    String firstName
    String lastName
    Date   birthday
    String gender
    String nationality
    String register
    String familyName
    /*contacts*/
    String phoneNumber
    String email
    String website
    String address
    String bio
   /* static mapping = {
        website defaultValue: "http://www.mnun.edu.mn";
    }
*/
    static constraints = {

        firstName    blank:false,      nullable:false
        lastName     blank:false,      nullable:false
        gender       inList:    Holders.config.person.gender.list
        register     blank:false,      nullable:false, unique:false
        familyName   blank:true,      nullable:true
        birthday     nullable:false
        nationality  inList: Holders.config.person.nationality.list
        phoneNumber  blank:false,    nullable:false
        email        blank:true,      nullable:true, email:true
        website      blank:true,       nullable:true, url:true
        address      blank:true,      nullable:true, widget:'textarea'
        bio          blank:true,       nullable:true , widget:'textarea'

    }



}
