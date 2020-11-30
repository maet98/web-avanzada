package edu.pucmm.severless.Functions;

public class FunctionCrudReservation  {

//    private Gson gson = new Gson();

    public void yay(){
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//        String cuerpoRecibido = null;
//        JSONObject responseJson = new JSONObject();
//        String salida = "";
//        Reservation reservation = null;
        //
//        try {
//            //Parseando el objeto.
//            JSONObject evento = (JSONObject) parser.parse(reader);
//
//            //Ver la salida por la consola sobre la trama enviada por el APIGateway
//            context.getLogger().log(""+evento.toJSONString());
//
//            //Recuperando el metodo de acceso de la llamada del API.
//            if(evento.get("requestContext")==null){
//                throw new IllegalArgumentException("No respesta el API de entrada");
//            }
//            String metodoHttp = ((JSONObject)((JSONObject)evento.get("requestContext")).get("http")).get("method").toString();
//
//            //Realizando la operacion
//            switch (metodoHttp){
//                case "GET":
//                    FuncionesDynamoDbEstudiante.ListarEstudiantesResponse listarEstudiantesResponse = funcionesDynamoDbEstudiante.listarEstudiantes(null, context);
//                    salida = gson.toJson(listarEstudiantesResponse);
//                    break;
//                case "POST":
//                case "PUT":
//                    estudiante = getEstudianteBodyJson(evento);
//                    funcionesDynamoDbEstudiante.insertarEtudianteTabla(estudiante, context);
//                    salida = gson.toJson(estudiante);
//                    break;
//                case "DELETE":
//                    estudiante = getEstudianteBodyJson(evento);
//                    funcionesDynamoDbEstudiante.eliminarEstudiante(estudiante, context);
//                    salida = gson.toJson(estudiante);
//                    break;
//            }
//
//            //La información enviada por el metodo Post o Put estará disponible en la propiedad body:
//            if(evento.get("body")!=null){
//                cuerpoRecibido = evento.get("body").toString();
//            }
//
//            //Respuesta en el formato esperado:
//            JSONObject responseBody = new JSONObject();
//            responseBody.put("data", JsonParser.parseString(salida));
//            responseBody.put("entrada", cuerpoRecibido);
//
//            JSONObject headerJson = new JSONObject();
//            headerJson.put("mi-header", "Mi propio header");
//
//            responseJson.put("statusCode", 200);
//            responseJson.put("headers", headerJson);
//            responseJson.put("body", responseBody.toString());
//
//        }catch (Exception ex){
//            responseJson.put("statusCode", 400);
//            responseJson.put("exception", ex);
//        }
//
//        //Salida
//        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
//        writer.write(responseJson.toString());
//        writer.close();
    }

//    private Reservation getEstudianteBodyJson(JSONObject json) throws IllegalArgumentException{
//        if(json.get("body")==null){
//            throw new IllegalArgumentException("No envio el cuerpo en la trama.");
//        }
//        Reservation reservation =gson.fromJson(json.get("body").toString(), Reservation.class);
//        return reservation;
//    }
}
