package com.example.serverless.funciones;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.example.serverless.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FuncionesDynamoDbReservation {

    private String access;
    private String secret;

    private AmazonDynamoDB ddb;
    private DynamoDBMapper mapper;

    public FuncionesDynamoDbReservation() {
        access = System.getenv("AWS_ACCESS");
        secret = System.getenv("AWS_SECRET");
        ddb = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials())).withRegion(Regions.US_EAST_1).build();
        mapper= new DynamoDBMapper(ddb, DynamoDBMapperConfig.DEFAULT);
        CreateTableRequest tableRequest = mapper.generateCreateTableRequest(Reservation.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        if (TableUtils.createTableIfNotExists(ddb, tableRequest)) {
            System.out.println("Table created");
        }
    }

    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                access, secret);
    }

    public ReservationResponse insertarReservationTable(Reservation reservation, Context context){
        if(reservation.getId() == 0 || reservation.getNombre().isEmpty()){
            throw new RuntimeException("Datos enviados no son validos");
        }

        try {
            mapper.save(reservation);
        }catch (Exception e){
            return new ReservationResponse(true, e.getMessage(), null);
        }

        return new ReservationResponse(false, null, reservation);
    }

    /**
     * Metodo para retornar el listado de todos los elementos de la tablas
     * @param filtro
     * @param context
     * @return
     */
    public ListarReservationResponse listarReservations(FiltroListaReservation filtro, Context context) {
        List<Reservation> reservations = new ArrayList<>();

        ScanRequest scanRequest = new ScanRequest().withTableName("Reservation");
        ScanResult result = null;

        do {// La consulta vía ScanRequest solo retorna 1 MB de datos por iteracion,
            //debemos iterar.

            if (result != null) {
                scanRequest.setExclusiveStartKey(result.getLastEvaluatedKey());
            }
            result = ddb.scan(scanRequest);
            List<Map<String, AttributeValue>> rows = result.getItems();

            // Iterando todos los elementos
            for (Map<String, AttributeValue> mapReservation : rows) {
                System.out.println(""+mapReservation);
                //
                AttributeValue IdAtributo = mapReservation.get("id");
                AttributeValue nombreAtributo = mapReservation.get("nombre");
                AttributeValue laboratorioAtributo = mapReservation.get("laboratorio");
                AttributeValue carreraAtributo = mapReservation.get("carrera");
                //
                Reservation tmp = new Reservation();
                tmp.setId(Integer.valueOf(IdAtributo.getN()));
                if(nombreAtributo!=null){
                    tmp.setNombre(nombreAtributo.getS());
                }
                if(laboratorioAtributo!=null){
                    tmp.setLaboratorio(laboratorioAtributo.getS());
                }
                if(carreraAtributo!=null){
                    tmp.setCarrera(carreraAtributo.getS());
                }
                reservations.add(tmp);
            }

        } while (result.getLastEvaluatedKey() != null);

        return new ListarReservationResponse(false, "", reservations);
    }

    /**
     * Función para eliminar un reservations
     * @param reservation
     * @param context
     * @return
     */
    public ReservationResponse eliminarReservation(Reservation reservation, Context context){
        DynamoDB dynamoDB = new DynamoDB(ddb);

        Table table = dynamoDB.getTable("Reservation");

        DeleteItemOutcome outcome = table.deleteItem("id", reservation.getId());
        return new ReservationResponse(false, null, reservation);
    }

    /**
     *
     */
    static class ListarReservationResponse{
        boolean error;
        String mensajeError;
        List<Reservation> reservations;

        public ListarReservationResponse() {
        }

        public ListarReservationResponse(boolean error, String mensajeError, List<Reservation> reservations) {
            this.error = error;
            this.mensajeError = mensajeError;
            this.reservations = reservations;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getMensajeError() {
            return mensajeError;
        }

        public void setMensajeError(String mensajeError) {
            this.mensajeError = mensajeError;
        }

        public List<Reservation> getReservations() {
            return reservations;
        }

        public void setReservations(List<Reservation> Reservations) {
            this.reservations = Reservations;
        }

        @Override
        public String toString() {
            return "ListarReservationResponse{" +
                    "error=" + error +
                    ", mensajeError='" + mensajeError + '\'' +
                    ", reservations=" + reservations +
                    '}';
        }
    }

    /**
     *  Encapsulación del objeto de respuesta.
     */
    static class ReservationResponse{
        boolean error;
        String mensajeError;
        Reservation reservation;

        public ReservationResponse(){

        }

        public ReservationResponse(boolean error, String mensajeError, Reservation reservation) {
            this.error = error;
            this.mensajeError = mensajeError;
            this.reservation = reservation;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getMensajeError() {
            return mensajeError;
        }

        public void setMensajeError(String mensajeError) {
            this.mensajeError = mensajeError;
        }

        public Reservation getReservation() {
            return reservation;
        }

        public void setReservation(Reservation reservation) {
            this.reservation = reservation;
        }

        @Override
        public String toString() {
            return "ReservationResponse{" +
                    "error=" + error +
                    ", mensajeError='" + mensajeError + '\'' +
                    ", reservation=" + reservation +
                    '}';
        }
    }

    static class FiltroListaReservation{
        String filtro;

        public String getFiltro() {
            return filtro;
        }

        public void setFiltro(String filtro) {
            this.filtro = filtro;
        }
    }

}