package com.example.pd.enumeration;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus
{
    NEW(1, "New", "Pick up to process"), REPROCESS(2, "Reprocess", "Pick up to reprocess"), INPROGRESS(3, "Inprogress",
            "Record in Progress"), COMPLETED(4, "Completed", "The order had completed successfully"), FAILED(5,
                    "Failed", "The order had attempt to run but fail due to api failure");

    private Integer id;
    private String value;
    private String desc;

    OrderStatus( Integer id, String value, String desc )
    {
        this.id = id;
        this.value = value;
        this.desc = desc;
    }

    public static List<OrderStatus> getAll()
    {
        return Arrays.asList( OrderStatus.values() );
    }

    public Integer getId()
    {
        return id;
    }

    public String getValue()
    {
        return value;
    }

    public String getDesc()
    {
        return desc;
    }
}
