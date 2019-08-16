package com.example.pd;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class AbstractIdentifiableEntity
{
    @Id
    // @GeneratedValue ( generator = "UUID" )
    @GenericGenerator ( name = "uuid", strategy = "uuid2" )
    @Column ( name = "id", updatable = false, nullable = false )
    @Type ( type = "uuid-char" )
    private UUID id;

    protected AbstractIdentifiableEntity()
    {
        super();
    }
    
    public void setId( UUID id )
    {
        this.id = id;
    }

    public UUID getId()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        return result;
    }

    @Override
    public boolean equals( final Object obj )
    {
        if ( this == obj )
        {
            return true;
        }

        if ( obj == null )
        {
            return false;
        }

        if ( !this.getClass().isAssignableFrom( obj.getClass() ) )
        {
            return false;
        }

        final AbstractIdentifiableEntity other = (AbstractIdentifiableEntity) obj;
        if ( getId() == null )
        {
            if ( other.getId() != null )
            {
                return false;
            }
        } else if ( !getId().equals( other.getId() ) )
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return toStringForClass( getClass() );
    }

    private String toStringForClass( final Class<? extends AbstractIdentifiableEntity> cls )
    {
        return cls.getSimpleName() + " [id=" + id + "]";
    }
}
