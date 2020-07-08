package com.cjlgb.design.common.mybatis.handler;

import com.cjlgb.design.common.mybatis.service.NumberEnum;
import lombok.AllArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author WFT
 * @date 2020/6/16
 * description: 数字枚举类型处理器
 */
@AllArgsConstructor
public class NumberEnumTypeHandler<E extends Enum<E> & NumberEnum> extends BaseTypeHandler<E> {

    private final Class<E> clazz;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return valueOf(this.clazz,rs.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return valueOf(this.clazz,rs.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return valueOf(this.clazz,cs.getInt(columnIndex));
    }

    private static <E extends Enum<?> & NumberEnum> E valueOf(Class<E> clazz, int value) {
        E[] constants = clazz.getEnumConstants();
        for (E e : constants) {
            if (e.getValue() == value){
                return e;
            }
        }
        return null;
    }

}
