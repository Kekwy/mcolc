package com.kekwy.mcolc.model;

import lombok.Data;

import java.util.List;

@Data
public class PlayerDetails {

    private String uuid;
    private String name;
    private float health;
    private int hunger;
    private Experience experience;
    private String buff; // TODO
    private Inventory inventory;

    @Data
    public static class Experience {
        private int level;
        private float progress;
    }

    @Data
    public static class Inventory {
        private List<MCItem> main;
        private List<MCItem> armor;
        private List<MCItem> offHand;
        private List<MCItem> hotBar;
    }

}
