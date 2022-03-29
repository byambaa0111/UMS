package com.ums.helper

import com.ums.system.SysGroup
import grails.gorm.transactions.Transactional

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Transactional
class  HelperService {

    def getSysGroup(String groupname) {

        if (SysGroup.findByGroupName(groupname.toUpperCase())) {

            return SysGroup.findByGroupName(groupname.toUpperCase())

        } else {

            def sysGroup = new SysGroup()
            sysGroup.active = true;
            sysGroup.groupName = groupname.toUpperCase();
            sysGroup.groupDesc = groupname.toUpperCase();
            sysGroup.save();
        };

        return SysGroup.findByGroupName(groupname.toUpperCase())

    }
    def static strToMd5(String input){

        String res = "";

        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input.getBytes());
            byte[] md5 = algorithm.digest();
            String tmp = "";
            for (int i = 0; i < md5.length; i++) {
                tmp = (Integer.toHexString(0xFF & md5[i]));
                if (tmp.length() == 1) {
                    res += "0" + tmp;
                } else {
                    res += tmp;
                }

            }

        } catch (NoSuchAlgorithmException ex) { return null; }

        return res;
    }
    def serviceMethod() {

    }
}
