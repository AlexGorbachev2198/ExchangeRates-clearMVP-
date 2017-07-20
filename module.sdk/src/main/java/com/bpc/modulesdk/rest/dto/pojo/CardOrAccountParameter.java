package com.bpc.modulesdk.rest.dto.pojo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StructureParameter.class, name = CardOrAccountParameter.STRUCTURE),
        @JsonSubTypes.Type(value = StringParameter.class, name = CardOrAccountParameter.STRING),
        @JsonSubTypes.Type(value = BooleanParameter.class, name = CardOrAccountParameter.BOOLEAN),
        @JsonSubTypes.Type(value = MoneyParameter.class, name = CardOrAccountParameter.MONEY),
        @JsonSubTypes.Type(value = DatetimeParameter.class, name = CardOrAccountParameter.DATETIME)})
public abstract class CardOrAccountParameter implements Serializable {

    public static final String STRUCTURE = "structure";
    public static final String STRING = "string";
    public static final String BOOLEAN = "boolean";
    public static final String MONEY = "money";
    public static final String DATETIME = "datetime";

    protected String id = "";
    protected String stereotype = "";
    protected String label = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStereotype() {
        return stereotype;
    }

    public void setStereotype(String stereotype) {
        this.stereotype = stereotype;
    }

    public abstract String getType();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}