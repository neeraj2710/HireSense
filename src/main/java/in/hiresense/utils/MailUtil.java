package in.hiresense.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

    public static void sendTextEmail(String recipientEmailAddresses, String subject, String body) throws MessagingException {
        System.out.println(recipientEmailAddresses+subject+body);
        Message message = new MimeMessage(MailConfig.getSession());
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipientEmailAddresses));
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
        System.out.println("mail Sent successfully");
    }

    public static void sendApplicationConfirmation(String name, String toEmail, String jobTitle,String company) throws MessagingException {
        String subject = "✔️Application Submitted: "+jobTitle;
        String body = "Dear " + name + ",\n\n"
                + "Your application has been successfully submitted for the position of "
                + jobTitle + " at " + company + ".\n"
                + "We have received your details and will review your profile shortly.\n\n"
                + "Thank you for applying.\n"
                + "Best regards,\n"
                + "Team Elevare";
        sendTextEmail(toEmail, subject, body);
    }

    public static void sendNewApplicationNotificationToEmployer(String name, String toEmail, String applicantName,String jobTitle) throws MessagingException {
        String subject = "🔔 New Application Received: " + jobTitle;

        String body = "Dear " + name + ",\n\n"
                + "You have received a new job application.\n\n"
                + "Applicant Name: " + applicantName + "\n"
                + "Applied Position: " + jobTitle + "\n\n"
                + "Please review the candidate's details at your earliest convenience.\n\n"
                + "Best regards,\n"
                + "Team Elevare";
        sendTextEmail(toEmail, subject, body);
    }

    public static void main(String[] args) {
        try {
            sendTextEmail("neerajwadhwaney2003@gmail.com", "Test 2", "Test 2");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
