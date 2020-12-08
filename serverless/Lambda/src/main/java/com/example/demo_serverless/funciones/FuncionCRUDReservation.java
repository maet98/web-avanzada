package com.example.demo_serverless.funciones;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.example.demo_serverless.model.Reservation;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.*;

public class FuncionCRUDReservation implements RequestStreamHandler {

    private FuncionesDynamoDbReservation funcionesDynamoDbReservation = new FuncionesDynamoDbReservation();

    private Gson gson = new Gson();

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        //Objetos para el control de la salida.
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String cuerpoRecibido = null;
        JSONObject responseJson = new JSONObject();
        String salida = "";
        Reservation reservation = null;
        //
        try {
            //Parseando el objeto.
            JSONObject evento = (JSONObject) parser.parse(reader);

            //Ver la salida por la consola sobre la trama enviada por el APIGateway
            context.getLogger().log(""+evento.toJSONString());

            //Recuperando el metodo de acceso de la llamada del API.
            if(evento.get("requestContext")==null){
                throw new IllegalArgumentException("No respesta el API de entrada");
            }
            String metodoHttp = ((JSONObject)((JSONObject)evento.get("requestContext")).get("http")).get("method").toString();

            //Realizando la operacion
            switch (metodoHttp){
                case "GET":
                    Boolean pasado = false;
                    if(!evento.get("rawQueryString").toString().equals("")){
                        String query = ((JSONObject)evento.get("queryStringParameters")).get("pasado").toString();
                        if(query.equals("true")){
                            pasado = true;
                        }
                    }
                    FuncionesDynamoDbReservation.ListarReservationResponse listarReservationResponse = funcionesDynamoDbReservation.listarReservations(null, context,pasado);
                    salida = gson.toJson(listarReservationResponse);
                    break;
                case "POST":
                case "PUT":
                    reservation = getReservationBodyJson(evento);
                    funcionesDynamoDbReservation.insertarReservationTable(reservation, context);
                    salida = gson.toJson(reservation);
                    break;
                case "DELETE":
                    reservation = getReservationBodyJson(evento);
                    funcionesDynamoDbReservation.eliminarReservation(reservation, context);
                    salida = gson.toJson(responseJson);
                    break;
            }

            //La información enviada por el metodo Post o Put estará disponible en la propiedad body:
            if(evento.get("body")!=null){
                cuerpoRecibido = evento.get("body").toString();
            }

            //Respuesta en el formato esperado:
            JSONObject responseBody = new JSONObject();
            responseBody.put("data", JsonParser.parseString(salida));
            responseBody.put("entrada", cuerpoRecibido);

            JSONObject headerJson = new JSONObject();
            headerJson.put("mi-header", "Mi propio header");

            responseJson.put("statusCode", 200);
            responseJson.put("headers", headerJson);
            responseJson.put("body", responseBody.toString());

        }catch (Exception ex){
            responseJson.put("statusCode", 400);
            responseJson.put("exception", ex);
        }

        //Salida
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();
    }


    private Reservation getReservationBodyJson(JSONObject json) throws IllegalArgumentException{
        if(json.get("body")==null){
            throw new IllegalArgumentException("No envio el cuerpo en la trama.");
        }
        Reservation reservation =gson.fromJson(json.get("body").toString(), Reservation.class);
        return reservation;
    }
}
