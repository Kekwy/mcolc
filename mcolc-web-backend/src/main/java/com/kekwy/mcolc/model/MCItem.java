package com.kekwy.mcolc.model;

import lombok.Data;

@Data
public class MCItem {

    private String key;
    private String name;
    private int damage;
    private int maxDamage;
    private int count;

}
