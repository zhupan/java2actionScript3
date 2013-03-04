package com.derbysoft.java2as3;

class ASProperty {

    private String modifier;
    private String name;
    private String type;
    private boolean enumPresentation;

    public boolean isEnumPresentation() {
        return enumPresentation;
    }

    public void setEnumPresentation(boolean enumPresentation) {
        this.enumPresentation = enumPresentation;
    }

    public String getModifier() {
        return modifier;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        if (isEnumPresentation()) {
            builder.append("static" + Separator.SPACE);
        }
        builder.append(getModifier());
        builder.append(Separator.SPACE);
        builder.append(getName());
        builder.append(Separator.COLON);
        builder.append(getType());
        if (isEnumPresentation()) {
            builder.append(" = \"");
            builder.append(getName());
            builder.append("\"");
        }
        builder.append(Separator.SEMICOLON);
        return builder.toString();
    }


}
