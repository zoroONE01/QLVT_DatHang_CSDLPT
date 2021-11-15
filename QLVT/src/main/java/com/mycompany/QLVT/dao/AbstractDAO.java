/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Mapper.RowMapper;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class AbstractDAO<T> implements GenericDAO<T> {

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DBConnectUtil.getConnection();
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            setParameter(statement, parameters);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;

        } catch (SQLException ex) {
            System.out.println("SQL error  " + ex.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    DBConnectUtil.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Close connection exception " + e.getMessage());
            }
        }
    }

    public <T> List<T> queryProcedure(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareCall(sql);

            setParameter(statement, parameters);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;

        } catch (SQLException ex) {
            System.out.println("SQL error  " + ex.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    DBConnectUtil.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Close connection exception" + e.getMessage());
            }
        }
    }
    // lấy kết quả true false trả về từ procedure
    public int queryReturnOfProcedure(String sql, Object... parameters) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareCall(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            if(resultSet.next())
            {  
                return resultSet.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("SQL error  " + ex.getMessage());
            return 0;
        } finally {
            try {
                if (connection != null) {
                    DBConnectUtil.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Close connection exception" + e.getMessage());
            }
        }
        return 0;
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {

            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;

                if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Float) {
                    statement.setFloat(index, (Float) parameter);
                } else if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);

                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                } else if (parameter instanceof java.sql.Date) {

                    statement.setDate(index, (java.sql.Date) parameter);
                } else if (parameter instanceof Double) {
                    statement.setDouble(index, (Double) parameter);
                } else {
                    statement.setNull(index, Types.NULL);
                }
                // đừng để dữ liệu null ==>xử lí đầu vào trước khi truy vấn                           
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareCall(sql);
            setParameter(statement, parameters);
            int affectRow = statement.executeUpdate();
             System.out.println("update effectedRow: " + affectRow);
            if (affectRow > 0)
            {
                connection.commit();
                return affectRow;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    System.out.println(e.getMessage());    
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    DBConnectUtil.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }
    //thuc thi procedure update va bat loi procedure
    public int updateProcedure(String sql, Object... parameters) {
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareCall(sql);
            setParameter(statement, parameters);
             boolean rs=statement.execute();
             if(rs==false)
             {   
              //  connection.commit();
                 return statement.getUpdateCount();
             }

        } catch (SQLException e) {
            if (connection != null) {
               // try 
                {
                    System.out.println(e.getMessage());
                    System.out.println(e.getCause());
                    //connection.rollback();
                }
                //catch (SQLException e1) 
                {
                    //e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    DBConnectUtil.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int insert(String sql, Object... parameters) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareCall(sql);
            setParameter(statement, parameters);//gan tham so truy van
            int affectRow = statement.executeUpdate();
          //  System.out.println("insert effectedRow: " + affectRow);
            //resultSet = statement.executeQuery();
            if (affectRow>0) {
                connection.commit();
                return 1;
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    DBConnectUtil.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

}
