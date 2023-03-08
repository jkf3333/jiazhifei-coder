package com.jiazhifei.coder.core.sql;

/**
 * @author jkf
 * @date 2023-03-07 23:20:00
 * @description
 */
public class SqlUtil {
    public String onlySql() {
//        try {
//
////            Class.forName(DRIVE_CLASS);
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(
//                    String.format(URL, this.ip, this.port, this.db),
//                    this.name,
//                    this.password);
//            String sql = "show create table " + this.table + ";";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            return "";
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
        return "";

    }

    /**
     * 从url中，提取MysqlConfigBuilder
     * url参数：jdbc:mysql://localhost:3306/jykj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true
     *
     * @param url url链接
     * @return MysqlConfigBuilder
     */
    public static void dbConfigFromUrl(MysqlConfigBuilder builder, String url) {
        url = url.substring(13);
        int idxQuestionMark = url.indexOf("?");
        url = url.substring(0, idxQuestionMark);
        int idxColon = url.indexOf(":");
        builder.ip(url.substring(0, idxColon));
        int idxBias = url.indexOf("/");
        builder.db(url.substring(idxBias + 1));
        builder.port(url.substring(idxColon + 1, idxBias));
    }
}
