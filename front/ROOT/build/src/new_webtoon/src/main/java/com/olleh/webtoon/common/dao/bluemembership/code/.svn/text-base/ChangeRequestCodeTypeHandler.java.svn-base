package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;

@MappedTypes( ChangeRequestCode.class )
public class ChangeRequestCodeTypeHandler implements TypeHandler<ChangeRequestCode>
{

	@Override
	public ChangeRequestCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return ChangeRequestCode.valueOf( resultSet.getString( name ) );
	}


	@Override
	public ChangeRequestCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public ChangeRequestCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return ChangeRequestCode.valueOf( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, ChangeRequestCode value, JdbcType jdbcType ) throws SQLException
	{
		statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<ChangeRequestCode, String>
	{
		public String convert( ChangeRequestCode code )
		{
			if( code == null ) return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, ChangeRequestCode>
	{
		public ChangeRequestCode convert( String text )
		{
			if( text == null ) return null;
			return ChangeRequestCode.valueOf( text );

		}
	}
}
