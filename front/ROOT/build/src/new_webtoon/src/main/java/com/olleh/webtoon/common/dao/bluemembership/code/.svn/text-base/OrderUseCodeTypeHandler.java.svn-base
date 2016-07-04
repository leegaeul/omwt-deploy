package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;

@MappedTypes( OrderUseCode.class )
public class OrderUseCodeTypeHandler implements TypeHandler<OrderUseCode>
{

	@Override
	public OrderUseCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return OrderUseCode.valueOf( resultSet.getString( name ) );
	}


	@Override
	public OrderUseCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public OrderUseCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return OrderUseCode.valueOf( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, OrderUseCode value, JdbcType jdbcType ) throws SQLException
	{
		statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<OrderUseCode, String>
	{
		public String convert( OrderUseCode code )
		{
			if( code == null ) return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, OrderUseCode>
	{
		public OrderUseCode convert( String text )
		{
			if( text == null ) return null;
			return OrderUseCode.valueOf( text );

		}
	}
}
