package edu.upc.dsa.utils;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        ////////////////
        for (String field: fields) {
            if(field.compareTo(("ID")) == 0) {sb.append(field);}
            else {sb.append(", ").append(field);}
        }

        sb.append(") VALUES (");

        System.out.println(sb);

        for (String field: fields) {
            if(field.compareTo(("ID")) == 0) {sb.append("?");}
            else {sb.append(", ?");}
        }

        sb.append(")");
        System.out.println(sb);

        return sb.toString();
    }
        ////////////////
        /*sb.append("ID");
        for (String field: fields) {
            sb.append(", ").append(field);
        }

        sb.append(") VALUES (?");

        System.out.println(sb);

        for (String field: fields) {
            sb.append(", ?");
        }

        sb.append(")");

        return sb.toString();
    }*/

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryDELETE(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET (");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("ID");
        for (String field: fields) {
            sb.append(", ").append(field);
        }

        sb.append(") [WHERE (?");

        for (String field: fields) {
            sb.append(", ?");
        }

        sb.append(")]");

        return sb.toString();
    }

    public static String createQueryCHECKLOGIN(Class theClass){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ID FROM ").append(theClass.getSimpleName()).append(" ");
        sb.append("WHERE username = ?").append(" ").append("AND password = ?");

        return sb.toString();
    }

    public static String createQueryCHECKNAMEINUSE(Class theClass){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ID FROM ").append(theClass.getSimpleName()).append(" ");
        sb.append("WHERE username = ?");

        return sb.toString();
    }

    public static String findAllQuery(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        System.out.println(sb.toString());
        return sb.toString();
    }


}