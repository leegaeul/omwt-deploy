package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;

@MappedTypes( PrdCode.class )
public class PrdCodeTypeHandler implements TypeHandler<PrdCode>
{

	@Override
	public PrdCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return PrdCode.valueOf( resultSet.getString( name ) );
	}


	@Override
	public PrdCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public PrdCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return PrdCode.valueOf( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, PrdCode value, JdbcType jdbcType ) throws SQLException
	{
		statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<PrdCode, String>
	{
		public String convert( PrdCode code )
		{
			if( code == null ) return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, PrdCode>
	{
		public PrdCode convert( String text )
		{
			if( text == null ) return null;
			return PrdCode.valueOf( text );

		}
	}
}
