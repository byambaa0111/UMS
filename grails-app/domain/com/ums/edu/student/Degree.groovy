package com.ums.edu.student



class Degree {

    String degree

    static constraints = {

        degree(nullable: false)

    }
    String toString(){

        return this.degree;

    }
}
