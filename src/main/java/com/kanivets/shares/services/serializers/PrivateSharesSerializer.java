package com.kanivets.shares.services.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.kanivets.shares.models.Share;

import java.io.IOException;

public class PrivateSharesSerializer extends JsonSerializer<Share > {


    @Override
    public void serialize(Share share, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {

        jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", share.getId());
        jsonGen.writeStringField("comment", share.getComment());
        jsonGen.writeNumberField("totalFaceValue", share.getTotalFaceValue());
        jsonGen.writeNumberField("faceValue", share.getFaceValue());
        jsonGen.writeNumberField("locality_id", share.getPaidStateDuty());
        jsonGen.writeNumberField("authorizedFundSize", share.getAuthorizedFundSize());
        jsonGen.writeNumberField("locality_id", share.getCodeEDRPOU());
        jsonGen.writeNumberField("amount", share.getAmount());
        jsonGen.writeStringField("date", share.getReleaseDate().toString());
        jsonGen.writeEndObject();

    }
}
