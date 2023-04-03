package com.rajsabari.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ValueRealmModel extends RealmObject {

    @PrimaryKey
    private int id = 0;
   private String value = "";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
