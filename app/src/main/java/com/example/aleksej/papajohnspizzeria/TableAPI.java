package com.example.aleksej.papajohnspizzeria;

import java.util.ArrayList;

public class TableAPI {

    public static ArrayList<Table> getTables(){
        ArrayList<Table> tables = new ArrayList<>();
        tables.add(new Table(1,SpinnerUtils.SMALL,SpinnerUtils.NON_SMOKING,4));
        tables.add(new Table(2,SpinnerUtils.BIG,SpinnerUtils.NON_SMOKING,6));
        tables.add(new Table(3,SpinnerUtils.BIG,SpinnerUtils.SMOKING,7));
        tables.add(new Table(4,SpinnerUtils.SMALL,SpinnerUtils.SMOKING,2));
        tables.add(new Table(5, SpinnerUtils.BIG,SpinnerUtils.NON_SMOKING,8));
        tables.add(new Table(6,SpinnerUtils.SMALL,SpinnerUtils.NON_SMOKING,4));
        tables.add(new Table(7,SpinnerUtils.BIG,SpinnerUtils.NON_SMOKING,6));
        tables.add(new Table(8,SpinnerUtils.SMALL,SpinnerUtils.SMOKING,4));
        tables.add(new Table(9,SpinnerUtils.SMALL,SpinnerUtils.NON_SMOKING,3));
        tables.add(new Table(10,SpinnerUtils.SMALL,SpinnerUtils.SMOKING,2));
        tables.add(new Table(11,SpinnerUtils.BIG,SpinnerUtils.NON_SMOKING,10));
        tables.add(new Table(12,SpinnerUtils.SMALL,SpinnerUtils.NON_SMOKING,4));
        tables.add(new Table(13,SpinnerUtils.SMALL,SpinnerUtils.SMOKING,4));

        return tables;
    }
}
