package com.example.pd;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AutowireHelper implements ApplicationContextAware
{
    private static final AutowireHelper INSTANCE = new AutowireHelper();
    private static ApplicationContext applicationContext;

    private AutowireHelper()
    {
    }

    public static void autowire( Object classToAutowire, Object... beansToAutowireInClass )
    {
        for ( Object bean : beansToAutowireInClass )
        {
            if ( bean == null )
            {
                applicationContext.getAutowireCapableBeanFactory().autowireBean( classToAutowire );
                return;
            }
        }
    }

    @Override
    public void setApplicationContext( final ApplicationContext applicationContext )
    {
        setContext( applicationContext );
    }

    public static AutowireHelper getInstance()
    {
        return INSTANCE;
    }

    public static void setContext( final ApplicationContext applicationContext )
    {
        AutowireHelper.applicationContext = applicationContext;
    }
}
