package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;

@MappedTypes( BillMethodCode.class )
public class BillMethodCodeTypeHandler implements TypeHandler<BillMethodCode>
{

	@Override
	public BillMethodCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return BillMethodCode.valueOf( resultSet.getString( name ) );
	}


	@Override
	public BillMethodCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public BillMethodCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return BillMethodCode.valueOf( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, BillMethodCode value, JdbcType jdbcType ) throws SQLException
	{
		statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<BillMethodCode, String>
	{
		public String convert( BillMethodCode code )
		{
			if( code == null ) return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, BillMethodCode>
	{
		public BillMethodCode convert( String text )
		{
			if( text == null ) return null;
			return BillMethodCode.valueOf( text );

		}
	}
}
