package com.frame.db;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version V1.0
 * @Description: 组装Sql
 * @company:
 * @date 2012-4-14 下午2:24:58
 */
public class DBSql {

    /**
     * @param @param  info
     * @param @param  sql
     * @param @return
     * @return String
     * @throws
     * @Title: assemblySqlByString
     * @Description: 组装Sql
     */
    public static String assemblySqlByString(String info, String sql) {
        StringBuilder newSql = new StringBuilder();
        String[] sqlString = sql.split("\\?");
        newSql.append(sqlString[0]);
        newSql.append("'");
        newSql.append(info);
        newSql.append("'");
        newSql.append(";");
        return newSql.toString();
    }

    /**
     * @param @param  list
     * @param @param  sql
     * @param @return
     * @return String
     * @throws
     * @Title: assemblySqlByList
     * @Description: 组装Sql
     */
    public static String assemblySqlByList(List<Map<String, String>> list,
                                           String sql) {
        StringBuilder newSql = new StringBuilder();
        String[] sqlString = sql.split("\\?");
        newSql.append(sqlString[0]);
        int i = 0;
        for (Map<String, String> map : list) {
            if (i != 0) {
                newSql.append(" UNION ALL SELECT ");
            }
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String value = map.get(iterator.next());
                newSql.append("'");
                newSql.append(value);
                newSql.append("',");
                i++;
                value = null;
            }
            newSql.deleteCharAt(newSql.length() - 1);
            newSql.append("\n");
        }
        newSql.append(";");
        return newSql.toString();
    }

    /**
     * @param @param  map
     * @param @param  sql
     * @param @return
     * @return String
     * @throws
     * @Title: assemblySqlByMap
     * @Description: 组装Sql
     */
    public static String assemblySqlByMap(Map<String, String> map, String sql) {
        StringBuilder newSql = new StringBuilder();
        String[] sqlString = sql.split("\\?");
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String value = map.get(iterator.next());
            newSql.append(sqlString[i]);
            newSql.append("'");
            newSql.append(value);
            newSql.append("'");
            i++;
            value = null;
        }
//		newSql.append(sqlString[i]);
        newSql.append(";\n");
        return newSql.toString();
    }

    /**
     * @param @param  list
     * @param @return
     * @return Object[]
     * @throws
     * @Title: changeList
     * @Description: 将List转换成数??
     */
    public static Object[] changeList(List<Object> list) {
        if (list != null && list.size() > 0) {
            return list.toArray(new Object[list.size()]);
        } else {
            return null;
        }
    }

}
