package com.example.pd.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;


public final class ObjectUtils
{

    private ObjectUtils()
    {
        throw new UnsupportedOperationException();
    }

    public static boolean isNotEmpty( final Object obj )
    {
        return !isEmpty( obj );
    }

    @SuppressWarnings ( "rawtypes" )
    public static boolean isEmpty( final Object obj )
    {
        if ( obj == null )
        {
            return true;
        }

        if ( obj instanceof Optional )
        {
            return ! ( (Optional) obj ).isPresent();
        }
        if ( obj instanceof CharSequence )
        {
            return ( (CharSequence) obj ).length() == 0;
        }
        if ( obj.getClass().isArray() )
        {
            return Array.getLength( obj ) == 0;
        }
        if ( obj instanceof Collection )
        {
            return ( (Collection) obj ).isEmpty();
        }
        if ( obj instanceof Map )
        {
            return ( (Map) obj ).isEmpty();
        }

        return false;
    }
}
