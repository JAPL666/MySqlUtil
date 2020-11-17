package com.warma;

import java.util.ArrayList;

public class Warma {
    public static String createTable(ArrayList<String> str, ArrayList<String> id){
        StringBuilder result=new StringBuilder();

        for (int j = 0; j < str.size(); j++) {
            boolean bool = false;
            String value=str.get(j);
            StringBuilder buffer = new StringBuilder();
            char[] chars = value.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                String s = String.valueOf(chars[i]).toUpperCase();
                if (i == 0) {
                    buffer.append(s);
                } else if (chars[i] == '_') {
                    bool = true;
                } else {
                    if (bool) {
                        buffer.append(s);
                        bool = false;
                    } else {
                        buffer.append(chars[i]);
                    }
                }
            }
            String s = get(value, buffer.toString(), id.get(j));
            result.append(s).append("\n\n");
        }
        return result.toString();
    }
    public static String get(String name1,String name2,String id){
        return "        <table tableName=\""+name1+"\"  domainObjectName=\""+name2+"\" enableCountByExample=\"false\" enableUpdateByExample=\"false\"\n" +
                "               enableDeleteByExample=\"false\" enableSelectByExample=\"false\" selectByExampleQueryId=\"false\">\n" +
                "            <generatedKey column=\""+id+"\" sqlStatement=\"MySql\" identity=\"true\"/>\n" +
                "        </table>";
    }
}
