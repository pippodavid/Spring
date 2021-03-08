package com.david.service.impl;

import com.david.service.IAccountService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class AccountServiceImpl_2 implements IAccountService {
    private String[] strs;
    private List<String> strList;
    private Set<String> strSet;
    private Map<String, String> strMap;
    private Properties myProps;

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public void setStrSet(Set<String> strSet) {
        this.strSet = strSet;
    }

    public void setStrMap(Map<String, String> strMap) {
        this.strMap = strMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    public void saveAccount() {
        System.out.println(Arrays.toString(strs));
        System.out.println(strList);
        System.out.println(strMap);
        System.out.println(strSet);
        System.out.println(myProps);
    }
}
