package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;

@MappedTypes( JoinStatusCode.class )
public class JoinStatusCodeTypeHandler implements TypeHandler<JoinStatusCode>
{

	@Override
	public JoinStatusCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return JoinStatusCode.valueOf( resultSet.getString( name ) );
	}


	@Override
	public JoinStatusCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public JoinStatusCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return JoinStatusCode.valueOf( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, JoinStatusCode value, JdbcType jdbcType ) throws SQLException
	{
		statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<JoinStatusCode, String>
	{
		public String convert( JoinStatusCode code )
		{
			if( code == null ) return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, JoinStatusCode>
	{
		public JoinStatusCode convert( String text )
		{
			if( text == null ) return null;
			return JoinStatusCode.valueOf( text );

		}
	}
}
