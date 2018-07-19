package com.example.relationship.onetomanyAppDemo.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonDeserialize(using=DateAndTimeDeserialize.class)

public class DateAndTimeDeserialize extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser paramJsonParser,
                            DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            // Handle exception here
        }
        return paramDeserializationContext.parseDate(str);
    }
}