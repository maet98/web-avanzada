package edu.pucmm.Service;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailSender {

    @Value("${SENDGRID_API_KEY}")
    private String apiKey;



    public void sendEmail(String fromEmail, String toEmail, String Subject, String Content) throws IOException {
        System.out.println(System.getenv("SENDGRID_API_KEY"));
        System.out.println(apiKey);
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", Content);
        Mail mail = new Mail(from, Subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
