package com.ivansoft.alexa.skills.sismos;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.ivansoft.alexa.skills.sismos.model.AmazonDynamoEarthquakeModel;
import com.ivansoft.alexa.skills.sismos.service.AmazonDynamoService;

import java.util.Optional;

public class EndPointHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        AmazonDynamoService amazonDynamoService = new AmazonDynamoService();
        AmazonDynamoEarthquakeModel amazonDynamoEarthquakeModel = amazonDynamoService.getData();

        return handlerInput.getResponseBuilder().withSpeech( amazonDynamoEarthquakeModel.getEarthquakes() ).build();
    }
}
