package com.olleh.webtoon.common.dao.bluemembership.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.convert.converter.Converter;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;

@MappedTypes( CardCode.class )
public class CardCodeTypeHandler implements TypeHandler<CardCode>
{
	private Log	logger	= LogFactory.getLog( BMService.class );


	@Override
	public CardCode getResult( ResultSet resultSet, String name ) throws SQLException
	{
		return CardCode.toCode( resultSet.getString( name ) );
	}


	@Override
	public CardCode getResult( ResultSet resultSet, int columnIndex ) throws SQLException
	{
		return null;
	}


	@Override
	public CardCode getResult( CallableStatement statement, int position ) throws SQLException
	{
		return CardCode.toCode( statement.getString( position ) );
	}


	@Override
	public void setParameter( PreparedStatement statement, int position, CardCode value, JdbcType jdbcType ) throws SQLException
	{
//		logger.info( String.format("setParameter %d %s %s", position, value.getCode(), value.getText() ) );
		
		if( value == null )
			statement.setString( position, null );
		else
			statement.setString( position, value.getCode() );
	}


	public static class ToStringConverter implements Converter<CardCode, String>
	{
		public String convert( CardCode code )
		{
			if( code == null )
				return "";

			return code.getCode();
		}
	}

	public static class ToCodeConverter implements Converter<String, CardCode>
	{
		public CardCode convert( String text )
		{
			if( text == null )
				return null;

			for( CardCode code : CardCode.values() )
				if( code.getCode().equals( text ) )
					return code;

			return null;

		}
	}
}
