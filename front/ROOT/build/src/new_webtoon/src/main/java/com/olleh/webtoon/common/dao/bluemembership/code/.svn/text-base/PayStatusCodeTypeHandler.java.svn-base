package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;

@MappedTypes( PayStatusCode.class )
public class PayStatusCodeTypeHandler implements TypeHandler<PayStatusCode>
{

	@Override
	public PayStatusCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return PayStatusCode.valueOf( resultSet.getString( name ) );
	}


	@Override
	public PayStatusCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public PayStatusCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return PayStatusCode.valueOf( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, PayStatusCode value, JdbcType jdbcType ) throws SQLException
	{
		statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<PayStatusCode, String>
	{
		public String convert( PayStatusCode code )
		{
			if( code == null ) return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, PayStatusCode>
	{
		public PayStatusCode convert( String text )
		{
			if( text == null ) return null;
			return PayStatusCode.valueOf( text );

		}
	}
}
