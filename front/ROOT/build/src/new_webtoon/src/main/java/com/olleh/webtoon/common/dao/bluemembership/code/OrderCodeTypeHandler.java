package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;

@MappedTypes( OrderCode.class )
public class OrderCodeTypeHandler implements TypeHandler<OrderCode>
{

	@Override
	public OrderCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return OrderCode.valueOf( resultSet.getString( name ) );
	}


	@Override
	public OrderCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public OrderCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return OrderCode.valueOf( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, OrderCode value, JdbcType jdbcType ) throws SQLException
	{
		statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<OrderCode, String>
	{
		public String convert( OrderCode code )
		{
			if( code == null ) return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, OrderCode>
	{
		public OrderCode convert( String text )
		{
			if( text == null ) return null;
			return OrderCode.valueOf( text );

		}
	}
}
